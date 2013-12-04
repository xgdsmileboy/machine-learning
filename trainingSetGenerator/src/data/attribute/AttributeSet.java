package data.attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AttributeSet 
{
	Map<String, Integer> nameIdMapping;
	ArrayList<Attribute> attributes;
	private int attrCount = 0;
	private String classAttribute;
	
	public AttributeSet()
	{
		nameIdMapping = new HashMap<String, Integer>();
		attributes = new ArrayList<Attribute>();
	}
	
	/**
	 * Print to the console a detailed outline of all the available attributes
	 * and their integer id's
	 */
	public void printAttributeSet()
	{
		System.out.println("ATTRIBUTES: ");
		for (Attribute attr : attributes)
		{
			System.out.println(attr.getName() + ", " + attr.getId());
			if (attr.getType() == Attribute.NOMINAL)
			{
				System.out.println("Nominal Values:");
				Map<String, Integer> nominalValueEntryMap = attr.getNominalValueMap();
				for (Entry<String, Integer> element : nominalValueEntryMap.entrySet())
				{
					System.out.println(element.getKey() + ", " + element.getValue());
				}
			}
			else if (attr.getType() == Attribute.CONTINUOUS)
			{
				System.out.println("Continuos");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Add an attribute to the set of attributes.
	 * 
	 * @param attrName - the name of the attribute
	 * @param attrType - the type of attribute (i.e. nominal or continuous)
	 * @param nominalValues - all nominal values if this is a nominal attribute. Set to null if 
	 * this attribute is continuous
	 */
	public void addAttribute(String attrName, Integer attrType, String[] nominalValues)
	{
		nameIdMapping.put(attrName, attrCount);
		attributes.add(new Attribute(attrName, attrCount, attrType, nominalValues));
		attrCount++;
	}
	
	/**
	 * Get an attribute by its attribute ID.
	 * 
	 * @param attrId - the attribute ID
	 * @return the attribute with this ID
	 */
	public Attribute getAttributeById(Integer attrId)
	{
		return attributes.get(attrId);
	}
	
	/**
	 * Get an attribute by its attribute name.
	 * 
	 * @param attrName - the attribute name
	 * @return the attribute with this name
	 */
	public Attribute getAttributeByName(String attrName)
	{
		Integer attrId = nameIdMapping.get(attrName);
		return attributes.get(attrId);
	}
	
	/*
	public Integer getAttributeId(String attrName)
	{
		return attributeMapping.getValue(attrName).getId();
	}*/
	
	public Integer getNominalValueId(String attrName, String attrValue)
	{
		Integer attrId = nameIdMapping.get(attrName);
		return attributes.get(attrId).getNominalValueId(attrValue);
	}
	
	public Integer getNominalValueId(Integer attrId, String attrValue)
	{
		return attributes.get(attrId).getNominalValueId(attrValue);
	}
	
	public Integer getNumAttributes()
	{
		return attributes.size();
	}
	
	public ArrayList<Attribute> getAttributes()
	{
		return attributes;
	}
	
	/**
	 * Sets the attribute that will be used as attribute set's class attribute
	 * 
	 * @param attrName
	 */
	public void setClass(String attrName)
	{
		if (this.contains(attrName))
		{
			classAttribute = attrName;
		}
		else
		{
			throw new RuntimeException("Trying to set an invalid attribute, " + attrName +
					" as the class attribute.");
		}
	}
	
	public Integer getClassAttrId()
	{
		return nameIdMapping.get(classAttribute);
	}
	
	public String getClassAttrName()
	{
		return classAttribute;
	}
	
	
	/**
	 * Determines whether a given attribute name is in the attribute set.
	 * 
	 * @param attrName The name of the attribute for which we are checking is valid
	 */
	public Boolean contains(String attrName)
	{
		return nameIdMapping.containsKey(attrName);
	}
	
	
	
}