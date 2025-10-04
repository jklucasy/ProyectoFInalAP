
package registrodocumentos;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class RegistroDocumentos {
    public static void main(String[] args) {
        // Inicialización del ArrayList que almacenará objetos Documento
        ArrayList<Documento> documentos = new ArrayList<>();
        int opcion;

        do {
            // Definición del menú de opciones
            String menu = """
--- MENÚ ---
1. Registrar Documento
2. Mostrar Documentos
3. Salir
""";
            // Opciones de tipos de documento definidas en la lógica original
            String[] opciones = {"Factura", "Boleta","Ticket de Consumo","Otros"};
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break; // si cancelan, salir

            // Manejo de posibles excepciones de formato de número (aunque simplificado aquí)
            opcion = Integer.parseInt(input);

            // Estructura Switch para la selección múltiple
            switch (opcion) {
                case 1 -> registrarDocumento(documentos,opciones); // Llama al método de registro

                case 2 -> mostrarDocumentos(documentos,opciones); // Llama al método de visualización

                case 3 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema..."); // Opción 3 para salir

                default -> JOptionPane.showMessageDialog(null, "Opción inválida."); // Opción por defecto
            }
        } while (opcion != 3);
    }
    
    public static void registrarDocumento(ArrayList<Documento> documentos,String[] opciones) {
        
        String tipo = (String) JOptionPane.showInputDialog( // Captura el tipo de documento
            null,
            "Seleccione el tipo de documento:",
            "Tipo de Documento",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones
        );

        if (tipo != null) {
            String numF = JOptionPane.showInputDialog("Número:");
            String cliF = JOptionPane.showInputDialog("Cliente:");
            // Se solicita el monto y se convierte a double
            double montoF = Double.parseDouble(JOptionPane.showInputDialog("Monto:")); 

            // Se crea una nueva instancia de Documento y se añade al ArrayList
            documentos.add(new Documento(tipo, numF, cliF, montoF));

            JOptionPane.showMessageDialog(null, tipo+" registrado con éxito.");
        }
    }
    
    public static void mostrarDocumentos(ArrayList<Documento> documentos,String[] opciones) {
         if (documentos.isEmpty()) { // Comprobación de lista vacía
            JOptionPane.showMessageDialog(null, "No hay documentos registrados.");
            return;
        }

        // 1. Solicitar al usuario el tipo de filtro
        String filtroSeleccionado = (String) JOptionPane.showInputDialog(
            null, 
            "Seleccione el tipo de documento a listar:", 
            "Filtro de Documentos", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opciones, // Se usa la lista de opciones predefinida
            opciones // Opción por defecto
        );

        if (filtroSeleccionado == null) {
            return; // El usuario canceló la selección
        }
        
        StringBuilder lista = new StringBuilder("--- LISTA DE DOCUMENTOS FILTRADOS (" + filtroSeleccionado + ") ---\n");
        int count = 0; // Contador para saber cuántos documentos cumplen el criterio
        double totalMonto = 0.0;
        // 2. Iterar y aplicar el filtro
        for (Documento d : documentos) {
            
            // Si el filtro seleccionado es "Mostrar Todos" O si el tipo del documento coincide 
            // con el filtro (usando el nuevo método getTipo())
            if (filtroSeleccionado.equals("Mostrar Todos") || d.getTipo().equals(filtroSeleccionado)) {
                // Si cumple el criterio, se añade a la lista llamando a d.mostrar()
                lista.append(d.mostrar()).append("\n"); 
                totalMonto += d.getMonto(); 
                count++;
            }
        }
        lista.append("----------------------------------------------------------").append("\n");
        lista.append("Monto Total: ").append(totalMonto); 
        
        // 3. Mostrar el resultado
        if (count > 0) {
             JOptionPane.showMessageDialog(null, lista.toString());
        } else {
             JOptionPane.showMessageDialog(null, "No se encontraron documentos del tipo " + filtroSeleccionado + ".");
        }
    }

}
