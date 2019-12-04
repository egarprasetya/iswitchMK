package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;
import com.example.demo.Enum.queue_autopause_values;

public class QueuesModel {
	public String name;
	public String musiconhold;
	public String announce;
	public String context;
	public int timeout;
	public YesNo_Values ringinuse;				// YesNo value / Type.
	public YesNo_Values setinterfacevar;			// YesNo value / Type.
	public YesNo_Values setqueuevar;				// YesNo value / Type.
	public YesNo_Values setqueueentryvar;		// YesNo value / Type.
	public String monitor_format;
	public String membermacro;
	public String membergosub;
	public String queue_youare_next;
	public String queue_thereare;
	public String queue_callswaiting;
	public String queue_quantity1;
	public String queue_quantity2;
	public String queue_holdtime;
	public String queue_minutes;
	public String queue_minute;
	public String queue_seconds;
	public String queue_thankyou;
	public String queue_callerannouce;
	public String queue_reporthold;
	public int announce_frequency;
	public YesNo_Values announce_to_first_user;	// YesNo value / Type.
	public int min_announce_frequency;
	public int announce_round_seconds;
	public String announce_holdtime;
	public String announce_position;
	public int announce_position_limit;
	public String periodic_announce;
	public int periodic_announce_frequency;
	public String relative_periodic_announce;
	public String random_periodic_announce;
	public int retry;
	public int wrapuptime;
	public int penaltymemberslimit;
	public String autofill;
	public String monitor_type;
	public queue_autopause_values autopause;				// Queue_autopause value / Type.
	public int autopausedelay;
	public YesNo_Values autopausebusy;			// YesNo value / Type.
	public YesNo_Values autopauseunavail;		// YesNo value / Type.
	public int maxlen;
	public int servicelevel;
	public String strategy;					// queue_strategy value / Type.
	public String joinempty;
	public String leavewhenempty;
	public YesNo_Values reportholdtime;			// YesNo value / Type.
	public int memberdelay;
	public int weight;
	public YesNo_Values timeoutrestart;			// YesNo value / Type.
	public String defaultrule;
	public String timeoutpriority;
}
