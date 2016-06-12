package com.study.spring.moni;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

/**
 * Spring Bean Factory
 */
public class ClassPathXMLApplicationContext {
	private List<BeanDefinition> beanDefines = new ArrayList<BeanDefinition>();
	private Map<String, Object> sigletons = new HashMap<String, Object>();

	public ClassPathXMLApplicationContext(String filename) {
		this.readXML(filename);
		this.instanceBeans();
		this.annotationInject();
		this.injectObject();
	}

	/**
	 * ͨ��ע��ʵ��ע����������
	 */
	private void annotationInject() {
		for (String beanName : sigletons.keySet()) { // ѭ�����е�Bean����
			Object bean = sigletons.get(beanName);
			if (bean != null) {
				try {
					// �������Ե�setter���Ƿ���ע��
					PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors(); 
					for (PropertyDescriptor properdesc : ps) { // ѭ����������
						Method setter = properdesc.getWriteMethod();// ��ȡ���Ե�setter����
						if (setter != null && setter.isAnnotationPresent(MyResource.class)) { // �ж�MyResourceע���Ƿ����
							MyResource resource = setter.getAnnotation(MyResource.class);
							Object injectBean = null;
							if (resource.name() != null && !"".equals(resource.name())) {
								injectBean = sigletons.get(resource.name()); // ͨ��MyResourceע���name���Ի�ȡBean
							} else { 
								injectBean = sigletons.get(properdesc.getName());
								if (injectBean == null) { // û��ָ��name����, �����������ƽ���Ѱ��
									for (String key : sigletons.keySet()) {
										// �����������ͽ���Ѱ��
										if (properdesc.getPropertyType().isAssignableFrom(sigletons.get(key).getClass())) { 
											injectBean = sigletons.get(key);
											break;
										}
									}
								}
							}
							setter.setAccessible(true); 
							setter.invoke(bean, injectBean);// �����ö���ע�뵽����
						}
					}
					
					// �����ֶ����Ƿ���ע��
					Field[] fields = bean.getClass().getDeclaredFields(); // ȡ�������������ֶ�
					for (Field field : fields) {
						if (field.isAnnotationPresent(MyResource.class)) { // �ж��ֶ����Ƿ����MyResourceע��
							MyResource resource = field.getAnnotation(MyResource.class);
							Object value = null;
							if (resource.name() != null && !"".equals(resource.name())) { // �ж��Ƿ�ָ��name����
								value = sigletons.get(resource.name());
							} else {
								value = sigletons.get(field.getName()); // û��ָ��name����,��ô�����ֶ�����Ѱ��
								if (value == null) {
									for (String key : sigletons.keySet()) {
										// �����ֶ����ͽ���Ѱ��
										if (field.getType().isAssignableFrom(sigletons.get(key).getClass())) { 
											value = sigletons.get(key);
											break;
										}
									}
								}
							}
							field.setAccessible(true);// �������private�ֶ�
							field.set(bean, value);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Ϊbean���������ע��ֵ
	 */
	private void injectObject() {
		for (BeanDefinition beanDefinition : beanDefines) {
			Object bean = sigletons.get(beanDefinition.getId());
			if (bean != null) {
				try {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
					for (PropertyDefinition propertyDefinition : beanDefinition.getPropertys()) {
						for (PropertyDescriptor properdesc : ps) {
							if (propertyDefinition.getName().equals(properdesc.getName())) {
								Method setter = properdesc.getWriteMethod(); // ��ȡ���Ե�setter����
								if (setter != null) {
									Object injectBean = null;
									if (propertyDefinition.getRef() != null && !"".equals(propertyDefinition.getRef().trim())) {
										injectBean = sigletons.get(propertyDefinition.getRef());
									} else {
										injectBean = ConvertUtils.convert(propertyDefinition.getValue(), properdesc.getPropertyType());
									}
									setter.setAccessible(true); // private method
									setter.invoke(bean, injectBean); // �����ö���ע�뵽����
								}
								break;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ���bean��ʵ����
	 */
	private void instanceBeans() {
		for (BeanDefinition beanDefinition : beanDefines) {
			try {
				if (beanDefinition.getClassName() != null && !"".equals(beanDefinition.getClassName().trim()))
					sigletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * ��ȡxml�����ļ�
	 * 
	 * @param filename
	 */
	private void readXML(String filename) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			URL xmlpath = this.getClass().getClassLoader().getResource(filename);
			document = saxReader.read(xmlpath);
			Map<String, String> nsMap = new HashMap<String, String>();
			nsMap.put("ns", "http://www.springframework.org/schema/beans");// ���������ռ�
			XPath xsub = document.createXPath("//ns:beans/ns:bean");// ����beans/bean��ѯ·��
			xsub.setNamespaceURIs(nsMap);// ���������ռ�
			List<Element> beans = xsub.selectNodes(document);// ��ȡ�ĵ�������bean�ڵ�
			for (Element element : beans) {
				String id = element.attributeValue("id");// ��ȡid����ֵ
				String clazz = element.attributeValue("class"); // ��ȡclass����ֵ
				BeanDefinition beanDefine = new BeanDefinition(id, clazz);
				XPath propertysub = element.createXPath("ns:property");
				propertysub.setNamespaceURIs(nsMap);// ���������ռ�
				List<Element> propertys = propertysub.selectNodes(element);
				for (Element property : propertys) {
					String propertyName = property.attributeValue("name");
					String propertyRef = property.attributeValue("ref");
					String propertyValue = property.attributeValue("value");
					PropertyDefinition propertyDefinition = new PropertyDefinition(propertyName, propertyRef, propertyValue);
					beanDefine.getPropertys().add(propertyDefinition);
				}
				beanDefines.add(beanDefine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡbeanʵ��
	 * 
	 * @param beanName
	 * @return
	 */
	public Object getBean(String beanName) {
		return this.sigletons.get(beanName);
	}
}