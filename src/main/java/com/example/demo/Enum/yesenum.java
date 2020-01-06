package com.example.demo.Enum;

public class yesenum
{
	public enum yesno_values
	{
		yes("yes"), no("no");

//		private yesno_values name;
		public String as;

		private String action;

		// getter method
		public String getAction()
		{
			return this.action;
		}

		// enum constructor - cannot be public or protected
		private yesno_values(String action)
		{
			this.action = action;
		}

	}
}
