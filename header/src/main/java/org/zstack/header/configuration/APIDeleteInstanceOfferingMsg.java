package org.zstack.header.configuration;

import org.zstack.header.message.APIDeleteMessage;
import org.zstack.header.message.APIParam;

public class APIDeleteInstanceOfferingMsg extends APIDeleteMessage implements InstanceOfferingMessage {
	@APIParam
	private String uuid;

	public APIDeleteInstanceOfferingMsg() {
	}
	
	public APIDeleteInstanceOfferingMsg(String uuid) {
	    super();
	    this.uuid = uuid;
    }

	public String getUuid() {
    	return uuid;
    }

	public void setUuid(String uuid) {
    	this.uuid = uuid;
    }

    @Override
    public String getInstanceOfferingUuid() {
        return uuid;
    }
}
