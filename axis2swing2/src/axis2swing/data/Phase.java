package axis2swing.data;

import java.util.LinkedList;
import java.util.List;

public class Phase
{
	private String name;
	private List<Handler> lstHandler;

	public Phase(String name)
	{
		this.name = name;
	}

	public Phase(String name, List<Handler> lstHandler)
	{
		this.name = name;
		this.lstHandler = lstHandler;
	}

	public String getName()
	{
		return this.name;
	}

	public void addHandler(Handler handler)
	{
		if (lstHandler == null)
			lstHandler = new LinkedList<Handler>();

		lstHandler.add(handler);
	}

	public List<Handler> getHandlers()
	{
		return this.lstHandler;
	}
}
