package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DemandBeanTest {

    @Test
    public void testDefaultConstructor() {
        DemandBean demandBean = new DemandBean();
        assertNotNull(demandBean);
        assertNull(demandBean.getUserName());
        assertNull(demandBean.getProdId());
        assertEquals(0, demandBean.getDemandQty());
    }

    @Test
    public void testParameterizedConstructor() {
        String userName = "JohnDoe";
        String prodId = "ABC123";
        int demandQty = 5;

        DemandBean demandBean = new DemandBean(userName, prodId, demandQty);
        assertNotNull(demandBean);
        assertEquals(userName, demandBean.getUserName());
        assertEquals(prodId, demandBean.getProdId());
        assertEquals(demandQty, demandBean.getDemandQty());
    }

    @Test
    public void testGettersAndSetters() {
        DemandBean demandBean = new DemandBean();

        String userName = "JaneDoe";
        String prodId = "XYZ789";
        int demandQty = 10;

        demandBean.setUserName(userName);
        demandBean.setProdId(prodId);
        demandBean.setDemandQty(demandQty);

        assertEquals(userName, demandBean.getUserName());
        assertEquals(prodId, demandBean.getProdId());
        assertEquals(demandQty, demandBean.getDemandQty());
    }

    @Test
    public void testSettersWithNullValues() {
        DemandBean demandBean = new DemandBean();

        demandBean.setUserName(null);
        demandBean.setProdId(null);
        demandBean.setDemandQty(0);

        assertNull(demandBean.getUserName());
        assertNull(demandBean.getProdId());
        assertEquals(0, demandBean.getDemandQty());
    }
}