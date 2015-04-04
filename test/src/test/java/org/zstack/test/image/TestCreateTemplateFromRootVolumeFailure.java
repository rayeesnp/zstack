package org.zstack.test.image;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.zstack.core.cloudbus.CloudBus;
import org.zstack.core.componentloader.ComponentLoader;
import org.zstack.core.db.DatabaseFacade;
import org.zstack.core.db.SimpleQuery;
import org.zstack.core.db.SimpleQuery.Op;
import org.zstack.header.identity.SessionInventory;
import org.zstack.header.image.Image;
import org.zstack.header.image.ImageStatus;
import org.zstack.header.image.ImageVO;
import org.zstack.header.image.ImageVO_;
import org.zstack.header.storage.backup.BackupStorage;
import org.zstack.header.storage.backup.BackupStorageInventory;
import org.zstack.header.storage.backup.BackupStorageVO;
import org.zstack.header.vm.VmInstanceInventory;
import org.zstack.test.Api;
import org.zstack.test.ApiSenderException;
import org.zstack.test.DBUtil;
import org.zstack.test.WebBeanConstructor;
import org.zstack.test.deployer.Deployer;
import org.zstack.test.storage.backup.sftp.TestSftpBackupStorageDeleteImage2;
import org.zstack.simulator.storage.primary.nfs.NfsPrimaryStorageSimulatorConfig;
import org.zstack.utils.Utils;
import org.zstack.utils.logging.CLogger;

public class TestCreateTemplateFromRootVolumeFailure {
    CLogger logger = Utils.getLogger(TestSftpBackupStorageDeleteImage2.class);
    Deployer deployer;
    Api api;
    ComponentLoader loader;
    CloudBus bus;
    DatabaseFacade dbf;
    SessionInventory session;
    NfsPrimaryStorageSimulatorConfig config;

    @Before
    public void setUp() throws Exception {
        DBUtil.reDeployDB();
        WebBeanConstructor con = new WebBeanConstructor();
        deployer = new Deployer("deployerXml/image/TestCreateTemplateFromRootVolume.xml", con);
        deployer.addSpringConfig("KVMRelated.xml");
        deployer.build();
        api = deployer.getApi();
        loader = deployer.getComponentLoader();
        bus = loader.getComponent(CloudBus.class);
        dbf = loader.getComponent(DatabaseFacade.class);
        config = loader.getComponent(NfsPrimaryStorageSimulatorConfig.class);
        session = api.loginAsAdmin();
    }
    
	@Test(expected = ApiSenderException.class)
	public void test() throws ApiSenderException {
	    VmInstanceInventory vm = deployer.vms.get("TestVm");
	    api.stopVmInstance(vm.getUuid());
	    String rootVolumeUuid = vm.getRootVolumeUuid();
	    
	    BackupStorageInventory sftp = deployer.backupStorages.get("sftp");
        BackupStorageVO bvo = dbf.findByUuid(sftp.getUuid(), BackupStorageVO.class);
	    config.createTemplateFromRootVolumeSuccess = false;
	    try {
	        api.createTemplateFromRootVolume("testImage", rootVolumeUuid, sftp.getUuid());
	    } catch (ApiSenderException e) {
            BackupStorageVO bvo1 = dbf.findByUuid(sftp.getUuid(), BackupStorageVO.class);
            Assert.assertEquals(bvo.getAvailableCapacity(), bvo1.getAvailableCapacity());

            long count = dbf.count(ImageVO.class);
            Assert.assertEquals(1, count);
            throw e;
	    }
	}

}