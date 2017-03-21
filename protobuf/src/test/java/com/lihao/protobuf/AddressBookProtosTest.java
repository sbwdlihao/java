package com.lihao.protobuf;

import org.junit.Assert;
import org.junit.Test;
import com.lihao.protobuf.AddressBookProtos.*;


public class AddressBookProtosTest {

    @Test
    public void testProto() {
        Person.Builder builder = Person.newBuilder();
        builder.setId(1);
        builder.setName("hello");
        Person person = builder.build();
        Assert.assertEquals(1, person.getId());
        Assert.assertEquals("hello", person.getName());
    }
}
