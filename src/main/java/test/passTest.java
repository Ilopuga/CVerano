package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Administrador;

class passTest {

	@Test
	void test() {
		// Contraseña sin cifrar
		String password = "mipass";
		// El hash MD5 esperado para "mipass"
		String expectedHash = "019c9fbe91025390efb1c65ce5eee5d6";
		// Llamamos al método
        String actualHash = Administrador.myMD5(password);
        // Comparamos el string que tenemos con el generado con el método.
        assertEquals(expectedHash, actualHash, "El hash MD5 generado no coincide con el esperado");
    }
}
