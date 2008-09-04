package axis2swing.data;

import java.util.LinkedList;
import java.util.List;

public class PhaseInfo
{
	private List<Phase> inFlowPhases;
	private List<Phase> inFaultFlowPhases;
	private List<Phase> outFlowPhases;
	private List<Phase> outFaultFlowPhases;

	public void addInFlowPhase(Phase phase)
	{
		if (inFlowPhases == null)
			inFlowPhases = new LinkedList<Phase>();

		inFlowPhases.add(phase);
	}

	public void setInFlowPhases(List<Phase> inFlowPhases)
	{
		this.inFlowPhases = inFlowPhases;
	}

	public List<Phase> getInFlowPhases()
	{
		return this.inFlowPhases;
	}

	public void addInFaultFlowPhase(Phase phase)
	{
		if (inFaultFlowPhases == null)
			inFaultFlowPhases = new LinkedList<Phase>();

		inFaultFlowPhases.add(phase);
	}

	public void setInFaultFlowPhases(List<Phase> inFaultFlowPhases)
	{
		this.inFaultFlowPhases = inFaultFlowPhases;
	}

	public List<Phase> getInFaultFlowPhases()
	{
		return this.inFaultFlowPhases;
	}

	public void addOutFlowPhase(Phase phase)
	{
		if (outFlowPhases == null)
			outFlowPhases = new LinkedList<Phase>();

		outFlowPhases.add(phase);
	}

	public void setOutFlowPhases(List<Phase> outFlowPhases)
	{
		this.outFlowPhases = outFlowPhases;
	}

	public List<Phase> getOutFlowPhases()
	{
		return this.outFlowPhases;
	}

	public void addOutFaultFlowPhase(Phase phase)
	{
		if (outFaultFlowPhases == null)
			outFaultFlowPhases = new LinkedList<Phase>();

		outFaultFlowPhases.add(phase);
	}

	public void setOutFaultFlowPhases(List<Phase> outFaultFlowPhases)
	{
		this.outFaultFlowPhases = outFaultFlowPhases;
	}

	public List<Phase> getOutFaultFlowPhases()
	{
		return this.outFaultFlowPhases;
	}

}
