package org.zstack.header.network.l2;

import org.zstack.header.query.APIQueryMessage;
import org.zstack.header.query.AutoQuery;

@AutoQuery(replyClass = APIQueryL2NetworkReply.class, inventoryClass = L2NetworkInventory.class)
public class APIQueryL2NetworkMsg extends APIQueryMessage {

}
