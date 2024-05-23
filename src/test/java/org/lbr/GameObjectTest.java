//package org.lbr;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//
//public class GameObjectTest {
//
//    @Test
//    public void testGameObject() {
//        GameObject gameObject = new GameObjectImpl("TestObject");
//        assertEquals("TestObject", gameObject.getName());
//        assertEquals("GameObjectImpl", gameObject.getTypeObject());
//        assertEquals("GameObjectImpl\nName: TestObject", gameObject.toString());
//
//        gameObject.setName("NewName");
//        assertEquals("NewName", gameObject.getName());
//        assertEquals("GameObjectImpl\nName: NewName", gameObject.toString());
//    }
//
//    // Concrete implementation for testing
//    private static class GameObjectImpl extends GameObject {
//        public GameObjectImpl(String name) {
//            super(name);
//        }
//    }
//}
