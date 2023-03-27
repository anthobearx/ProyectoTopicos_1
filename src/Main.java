//**
//        INSTRUCCIONES
//  Elaborar un programa en Java que resuelva el siguiente problema: Se desea una aplicación en
//  ambiente Windows que implemente un editor de texto simple, basado en un componente JTextArea.
//
//  La aplicación debe tener un menú con los submenús Archivo, Edición y Ayuda.
//  El menú Archivo debe tener las siguientes opciones:
//
//  • Nuevo: Elimina to-do el texto del componente JTextArea.
//  • Abrir: Debe mostrar un diálogo de tipo JFileChooser para seleccionar el archivo a abrir. Una
//      vez abierto el archivo su contenido se debe mostrar en el componente JTextArea.
//  • Guardar como: Debe mostrar un diálogo de tipo JFileChooser para seleccionar la carpeta y
//      el nombre del archivo donde se debe guardar el contenido del componente JTextArea.
//  • Imprimir: Esta opción debe imprimir el contenido del JTextArea en una hoja de papel.
//      (Nota: puede utilizar el método print() de la clase JTextArea).
//  • Salir: Esta opción debe terminar la aplicación
//
//  El menú Edición debe tener las siguientes opciones:
//  • Tipo de Letra: Al seleccionar esta opción se debe mostrar un cuadro de diálogo
//      personalizado que permita escoger un tipo de letra entre todas las instaladas en la
//      computadora, así como su tamaño y propiedades (negrita, cursiva) y asignarla al componente
//      JTextArea (sugerencia: usar un cuadro de diálogo de la clase JDialog).
//  • Copiar: Al seleccionar esta opción, el texto del componente JTextArea que se encuentre
//      seleccionado se debe copiar al portapapeles (Clipboard) del sistema operativo.
//  • Pegar: Al seleccionar esta opción el texto contenido en el Portapapeles (Clipboard) se debe
//      de pegar dentro del componente JTextArea en la ubicación donde se encuentre el cursor
//
//    El menú Ayuda debe tener las siguientes opciones:
//     • Acerca de: Al seleccionar esta opción se debe mostrar un cuadro de diálogo con los datos
//        del autor del programa.
//     Nota: No se aceptan trabajos idénticos ni con código descargado de páginas Web
//
//
//**//

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Creacion de ventana mediante JFrame
        MiVentanaP miVentanaP = new MiVentanaP("Proyecto topicos 1- Vega Gonzalez Jesus Antonio");
        miVentanaP.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Establecer el color de fondo de la barra de título
        //miVentanaP.getContentPane().setBackground(Color.CYAN);
        //miVentanaP.setBackground(Color.CYAN);


        //Crear panel personalizado y agregar a la ventana
        //MiVentanaP miVentanaP = new MiVentanaP();
        //miVentana.add(miVentanaP);

        //Darle tamaño y hacer visible la ventana


        miVentanaP.pack();
        //miVentana.setSize(400,300);
        miVentanaP.setBackground(new Color(255,0,0));
        miVentanaP.setForeground(new Color(200,0,0));
        miVentanaP.setVisible(true);
        //miVentanaP.setBackground(new Color(255,0,0));




    }
}
class MiVentanaP extends JFrame {
    //Declarar componentes de JTextArea
    JTextArea ta;

    //Componentes JMenuItem de JMenu Archivo
    JMenuItem menuItemNuevo;
    JMenuItem menuItemAbrir;
    JMenuItem menuItemGuardar;
    JMenuItem menuItemImprimir;
    JMenuItem menuItemSalir;

    //Componentes JMenuItem de JMenu Edicion
    JMenuItem menuItemTipoLetra;
    JMenuItem menuItemCopiar;
    JMenuItem menuItemPegar;

    //Componentes JMenuItem de JMenu Ayuda
    JMenuItem menuItemAcercaDe;

    //componentes de archivos
    JFileChooser fileChooser = new JFileChooser();
    public MiVentanaP(String titulo) {
        super(titulo);

        //comportamiento de ventanas
        comportamientoVentanas comportamientoVentanas = new comportamientoVentanas();
        addWindowListener(comportamientoVentanas);



        //Menu barra: (se necesito la clase MiVentanaP propia, la cual extiende JFrame, para poder usar el MenuBar)
        JMenuBar barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);


        //archivo
        //creacion de objeto manejadorArchivo para manejo de elementos del menu archivo
        ManejadorArchivo manejadorArchivo = new ManejadorArchivo();
        JMenu menuArchivo = new JMenu("Archivo");//crear menu archivo
        menuArchivo.setMnemonic('A');//establecer menomico

        //creacion de menuItems para archivo
        menuItemNuevo = new JMenuItem("Nuevo");
        menuItemNuevo.setMnemonic('N');
        menuItemNuevo.addActionListener(manejadorArchivo);//ligar oyente

        menuItemAbrir = new JMenuItem("Abrir...");
        menuItemAbrir.setMnemonic('b');
        menuItemAbrir.addActionListener(manejadorArchivo);

        menuItemGuardar = new JMenuItem("Guardar como...");
        menuItemGuardar.setMnemonic('G');
        menuItemGuardar.addActionListener(manejadorArchivo);

        menuItemImprimir = new JMenuItem("Imprimir");
        menuItemImprimir.setMnemonic('I');
        menuItemImprimir.addActionListener(manejadorArchivo);

        menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.setMnemonic('S');
        menuItemSalir.addActionListener(manejadorArchivo);

        //agregar menuItems al menu Archivo
        menuArchivo.add(menuItemNuevo);
        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemGuardar);
        menuArchivo.add(menuItemImprimir);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);



        //edicion
        ManejadorEdicion manejadorEdicion = new ManejadorEdicion();
        JMenu menuEdicion = new JMenu("Edicion");
        menuEdicion.setMnemonic('E');

        //menu items
        menuItemTipoLetra = new JMenuItem("Tipo de letra");
        menuItemTipoLetra.setMnemonic('L');
        menuItemTipoLetra.addActionListener(manejadorEdicion);

        menuItemCopiar = new JMenuItem("Copiar");
        menuItemCopiar.setMnemonic('C');
        menuItemCopiar.addActionListener(manejadorEdicion);

        menuItemPegar = new JMenuItem("Pegar");
        menuItemPegar.setMnemonic('P');
        menuItemPegar.addActionListener(manejadorEdicion);

        menuEdicion.add(menuItemTipoLetra);
        menuEdicion.add(menuItemCopiar);
        menuEdicion.add(menuItemPegar);


        //ayuda
        ManejadorAyuda manejadorAyuda = new ManejadorAyuda();
        JMenu menuAyuda = new JMenu("Ayuda");
        menuAyuda.setMnemonic('y');

        menuItemAcercaDe = new JMenuItem("Acerca de");
        menuItemAcercaDe.setMnemonic('d');
        menuItemAcercaDe.addActionListener(manejadorAyuda);

        menuAyuda.add(menuItemAcercaDe);


        //agregar los menus en la barra menu
        barraMenu.add(menuArchivo);//Dibujar el menu Archivo en la barra menu
        barraMenu.add(menuEdicion);
        barraMenu.add(menuAyuda);
        barraMenu.setBackground(new Color(100,100,100));
        barraMenu.setForeground(new Color(255,50,50));
        menuArchivo.setForeground(new Color(255,50,50));
        menuItemAbrir.setBackground(new Color(255,50,50));

        //añadir propiedades al TextArea
        ta = new JTextArea(35,90);
        ta.setBackground(new Color(237,171,228));
        //ta.setForeground(Color.WHITE);
        //ta.setLineWrap(true);
        //ta.setWrapStyleWord(true);
        //ta.setEditable( true );

        // Configurar el BorderLayout para que el JTextArea ocupe to-do el tamaño de la pantalla (cuando se minimiza/maximiza)
        setLayout(new BorderLayout());
        add(new JScrollPane(ta), BorderLayout.CENTER);
        //add(ta);


    }//fin constructor MiVentanaP
//creacion de la clase ManejadorArchivo, util para manejar los eventos de accion de los elementos de menuArchivo
private class ManejadorArchivo implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener el nombre del botón clickeado
        JMenuItem click = (JMenuItem) e.getSource();

        if (click == menuItemNuevo) {
            ta.setText(""); // borra el área de trabajo
        } else if (click == menuItemAbrir) {

            // Mostrar el cuadro de diálogo Abrir archivo
            int resultado = fileChooser.showOpenDialog(null);

            // se elige un archivo, se lee contenido y lo muestra en el área de texto
            if (resultado == JFileChooser.APPROVE_OPTION) {
                //se guarda el archivo en tipo file
                File archivo = fileChooser.getSelectedFile();
                //se lee mediante un bufferreader y se escribe en el textarea
                try (BufferedReader leer = new BufferedReader(new FileReader(archivo))) {
                    StringBuilder sb = new StringBuilder();
                    String linea;
                    while ((linea = leer.readLine()) != null) {
                        sb.append(linea);
                        sb.append("\n");
                    }
                    ta.setText(sb.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (click == menuItemGuardar) {

            //Filtro para archivos .txt
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto", "txt");
            fileChooser.setFileFilter(filtro);

            // Muestra el cuadro de diálogo para guardar el archivo
            int resultado = fileChooser.showSaveDialog(null);

            // Se selecciona una ubicacion y un nombre para guardar el contenido del JtextArea
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();

                // Si el archivo no tiene extensión .txt, se le agregara
                if (!archivo.getName().endsWith(".txt")) {
                    archivo = new File(archivo.getAbsolutePath() + ".txt");
                }


                try (BufferedWriter guardar = new BufferedWriter(new FileWriter(archivo))) {
                    String contenido = ta.getText();
                    guardar.write(contenido);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } else if (click == menuItemImprimir) {
            try {
                ta.print();//el metodo abre una ventana de impresoras para poder imprimir
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }

        } else if (click == menuItemSalir) {
            salir();
        }
    }
}


    private class ManejadorEdicion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem click = (JMenuItem) e.getSource();
            if (click == menuItemTipoLetra) {
                // Crear un nuevo JDialog para seleccionar el tipo de letra
                JDialog ventanaLetra = new JDialog();
                ventanaLetra.setTitle("Seleccionar tipo de letra");
                ventanaLetra.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                //Modal = no se puede interactuar con ninguna otra ventana en la aplicación hasta que se cierre
                ventanaLetra.setModal(true);

                // obtiene las fuentes instaladas en la computadora
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Font[] fuentes = ge.getAllFonts();

                //obtiene los colores existentes


                // Crea una lista de tipos de letra disponibles
                ArrayList<String> listaFuentes = new ArrayList<>();
                for (Font fuente : fuentes) {
                    listaFuentes.add(fuente.getName());
                }

                // Crea un JComboBox para seleccionar el tipo de letra
                JComboBox<String> fuentesComboBox = new JComboBox<>(listaFuentes.toArray(new String[0]));
                fuentesComboBox.setSelectedItem(ta.getFont().getName());

                // Crea un JComboBox para seleccionar el tamaño de letra
                Integer[] tamanos = { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 100, 200 };
                JComboBox<Integer> tamanosComboBox = new JComboBox<>(tamanos);
                tamanosComboBox.setSelectedItem(ta.getFont().getSize());

                // Crea checkbox para negrita y cursiva
                JCheckBox negritasCheckBox = new JCheckBox("Negrita", ta.getFont().isBold());
                JCheckBox cursivaCheckBox = new JCheckBox("Cursiva", ta.getFont().isItalic());

                // Crear un botón para aplicar los cambios
                JButton btnAceptar = new JButton("Aplicar");
                btnAceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener la selección del usuario
                        String nombreFuente = (String) fuentesComboBox.getSelectedItem();
                        int tamanoFuente = (int) tamanosComboBox.getSelectedItem();
                        int estilo = Font.PLAIN;
                        if (negritasCheckBox.isSelected()) {
                            estilo |= Font.BOLD;//es lo mismo que decir +=
                        }
                        if (cursivaCheckBox.isSelected()) {
                            estilo |= Font.ITALIC;
                        }

                        // Asignar el nuevo tipo de letra al JTextArea
                        ta.setFont(new Font(nombreFuente, estilo, tamanoFuente));

                        // Cerrar el diálogo
                        ventanaLetra.dispose();
                    }
                });

                // Agregar los componentes al diálogo
                JPanel panel = new JPanel(new GridLayout(4, 2));
                panel.add(new JLabel("Tipo de letra:"));
                panel.add(fuentesComboBox);
                panel.add(new JLabel("Tama\u00F1o de la letra:"));
                panel.add(tamanosComboBox);
                panel.add(new JLabel());
                panel.add(negritasCheckBox);
                panel.add(new JLabel());
                panel.add(cursivaCheckBox);
                ventanaLetra.add(panel, BorderLayout.CENTER);
                ventanaLetra.add(btnAceptar, BorderLayout.SOUTH);
                ventanaLetra.pack();
                ventanaLetra.setLocationRelativeTo(null);
                ventanaLetra.setVisible(true);

            } else if (click==menuItemCopiar) {
                ta.copy();
                
            } else if (click == menuItemPegar) {
               ta.paste();
            }

        }
    }

    private class ManejadorAyuda implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {

            JMenuItem click = (JMenuItem) e.getSource();

            if (click==menuItemAcercaDe){
                JOptionPane.showMessageDialog( MiVentanaP.this,
                        "Programa hecho con mucho cari\u00F1o por:" +
                                "\nJes\u00FAs Antonio Vega Gonz\u00E1lez el 10/Marzo/2023" +
                                "\npara la clase de T\u00F3picos Avanzados :)"+
                                "\nSaludos",
                        "Acerca de", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }
    private class comportamientoVentanas implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            salir();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
   void salir(){
        //si no esta vacio va a preguntar si desea salir
        if (ta.getText() == null || ta.getText().length() == 0) {
            System.exit(0);
        } else if (ta.getColumns() > 1) {
            int opcion = JOptionPane.showConfirmDialog(null, "\u00BFEstas seguro de querer salir (Si/No)?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}