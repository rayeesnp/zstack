package org.zstack.header.image;

import org.zstack.header.message.APIMessage;
import org.zstack.header.message.APIParam;

/**
 * Created with IntelliJ IDEA.
 * User: frank
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIChangeImageStateMsg  extends APIMessage implements ImageMessage{
    @APIParam(resourceType = ImageVO.class)
    private String uuid;
    @APIParam(validValues = {"enable", "disable"})
    private String stateEvent;

    @Override
    public String getImageUuid() {
        return getUuid();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStateEvent() {
        return stateEvent;
    }

    public void setStateEvent(String stateEvent) {
        this.stateEvent = stateEvent;
    }
}
