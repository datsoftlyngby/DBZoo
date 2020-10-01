package dbzoo.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    void passwordShouldWork() {

        byte[] salt = new byte[] {
                (byte) 0x8b,
                (byte) 0xbe,
                (byte) 0x1b,
                (byte) 0x5d,
                (byte) 0x0f,
                (byte) 0x4a,
                (byte) 0x08,
                (byte) 0xde,
                (byte) 0x8e,
                (byte) 0x92,
                (byte) 0x86,
                (byte) 0x2c,
                (byte) 0x4d,
                (byte) 0x78,
                (byte) 0x33,
                (byte) 0x9f
        };
        byte[] secret = User.calculateSecret(salt, "password");

        assertEquals(User.byteArrayToHex(secret),
                "b257c8e3a4b85796069b96500af58ea87131" +
                        "b58e4091eea774d4fa8ddd4bfed3"
                );
        assertEquals(User.byteArrayToHex(salt),
                "8bbe1b5d0f4a08de8e92862c4d78339f"
        );


    }

}