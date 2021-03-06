package org.zstack.storage.primary.nfs;

import org.zstack.header.storage.primary.APIAddPrimaryStorageMsg;
/**
 * @api
 *
 * add nfs primary storage
 *
 * @category nfs primary storage
 *
 * @since 0.1.0
 *
 * @cli
 *
 * @httpMsg
 * {
"org.zstack.storage.primary.nfs.APIAddNfsPrimaryStorageMsg": {
"url": "nfs://test",
"name": "nfs",
"description": "Test",
"zoneUuid": "666e45a59e504e3893692cb250799ec0",
"session": {
"uuid": "f0c39ad956124bf19994bd8fafaf9004"
}
}
}
 *
 * @msg
 *
 * {
"org.zstack.storage.primary.nfs.APIAddNfsPrimaryStorageMsg": {
"url": "nfs://test",
"name": "nfs",
"description": "Test",
"zoneUuid": "666e45a59e504e3893692cb250799ec0",
"session": {
"uuid": "f0c39ad956124bf19994bd8fafaf9004"
},
"timeout": 1800000,
"id": "eceed3f0994647409d5f2e7a9fccc253",
"serviceId": "api.portal"
}
}
 *
 * @result
 *
 * see :ref:`APIAddNfsPrimaryStorageEvent`
 */
public class APIAddNfsPrimaryStorageMsg extends APIAddPrimaryStorageMsg {
    public APIAddNfsPrimaryStorageMsg() {
        this.setType(NfsPrimaryStorageConstant.NFS_PRIMARY_STORAGE_TYPE);
    }

    @Override
    public String getType() {
        return NfsPrimaryStorageConstant.NFS_PRIMARY_STORAGE_TYPE;
    }
}
