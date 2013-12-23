package tree.train;

import java.util.ArrayList;

import data.attribute.Attribute;
import data.instance.Instance;
import data.instance.InstanceSet;

public class Split 
{
	/**
	 * All of the branches for this split. For splits on nominal attributes,
	 * there will be one branch per nominal value.  For continuous attributes,
	 * there will be two branches.  One branch for the all instances with a value
	 * greater than the threshold and one branch for all instances less than or 
	 * equal to the threshold value.
	 */
	private ArrayList<SplitBranch> branches;
	
	/**
	 * The attribute this Split splits instances on
	 */
	private Attribute attribute;
	
	/**
	 *	The information gain on this split
	 */
	private Double infoGain;
	
	public Split(Attribute attribute)
	{
		branches = new ArrayList<SplitBranch>();
		this.attribute = attribute;
	}
	
	public Attribute getAttribute()
	{
		return attribute;
	}
	
	public Double getInfoGain()
	{
		return infoGain;
	}
	
	public void setInfoGain(Double infoGain)
	{
		this.infoGain = infoGain;
	}
	
	public void splitInstances(InstanceSet instances)
	{
		for (Instance instance : instances.getInstanceList())
		{
			for (SplitBranch branch : this.branches)
			{
				branch.tryAddInstance(instance);
			}
		}
	}
	
	public ArrayList<SplitBranch> getSplitBranches()
	{
		return branches;
	}
	
	public void addBranch(SplitBranch newBranch)
	{
		branches.add(newBranch);
	}
	
}
