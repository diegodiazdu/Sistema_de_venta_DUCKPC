package Controlador;

import javax.swing.JOptionPane;

public class valida_rut {

    public boolean validarRut(String rut) {

        boolean validacion = false;

        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutValido = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;

            for (; rutValido != 0; rutValido /= 10) {

                s = (s + rutValido % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Error java.lang.NumberFormatException" + e.getMessage(), "Oops!", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al validar rut " + e.getMessage(), "Oops!", JOptionPane.ERROR_MESSAGE);

        }
        return validacion;
    }
}
