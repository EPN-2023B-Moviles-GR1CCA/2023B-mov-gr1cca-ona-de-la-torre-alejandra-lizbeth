package GUI

import Repository.CocineroRepository
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableCellEditor
import javax.swing.table.TableCellRenderer

class CocineroInterface(): JFrame() {

    var mainPanel:JPanel ?= null
    val cocineroRepository: CocineroRepository = CocineroRepository("src/data/cocineros.json")

    init {
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        title = "Aplicación cocinero comidas"
        contentPane.background = Color(255, 255, 255)
        setSize(900, 650)
        setLocation(300,80)
        isResizable = false
        // Agregar un WindowListener para manejar el evento de cierre de la ventana
        addWindowListener(object : WindowAdapter() {
            override fun windowClosed(e: WindowEvent) {
                // Lógica para volver a abrir MainInterface cuando se cierre CocineroInterface
                mostrarMainInterface()
            }
        })

        agregarPanel()
        volverMainInterface()
        agregarEtiquetas()
        agregarCocineros()
        agregarTabla()
    }

    fun agregarPanel(){
        var contenedor = JPanel()
        contenedor.background = Color(25,94,99)
        this.contentPane.add(contenedor)

        mainPanel = JPanel()
        contenedor.add(mainPanel)
        mainPanel!!.background = Color(25,94,99)
        contenedor.layout = BoxLayout(contenedor, BoxLayout.X_AXIS)
    }

    fun agregarEtiquetas(){
        var lblTitle = JLabel("Cocineros")
        mainPanel!!.add(lblTitle)
        mainPanel!!.setLayout(null)
        lblTitle.setBounds(375,10,150,40)
        lblTitle.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle.foreground = Color.white
        lblTitle.font = Font("Times New Roman", Font.BOLD, 25)
    }

    fun volverMainInterface(){
        var btnVolver = JButton()
        mainPanel!!.add(btnVolver)
        mainPanel!!.setLayout(null)
        btnVolver.setBounds(20,20,30,20)
        btnVolver.isOpaque = false

        btnVolver.icon = ImageIcon(ImageIcon("src/images/flecha-izquierda.png").image.getScaledInstance(30,20, Image.SCALE_FAST))
        // Agregar ActionListener para el botón "volver"
        btnVolver.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                isVisible = false
                mostrarMainInterface()
            }
        })
    }

    fun agregarCocineros(){
        var btnAgregarCocinero = JButton("Agregar cocinero")
        mainPanel!!.add(btnAgregarCocinero)
        mainPanel!!.setLayout(null)
        btnAgregarCocinero.setBounds(20,80,150,25)
        btnAgregarCocinero.background = Color(236,225,195)
        btnAgregarCocinero.font = Font("Times New Roman", Font.BOLD, 14)

        btnAgregarCocinero.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                // Lógica para ocultar esta ventana y mostrar creacion cocineros
                isVisible = false
                CreacionCocineroInterface().isVisible = true
            }
        })
    }

    private fun agregarTabla() {
        val columnNames = arrayOf("Código único","Nombre completo", "Edad", "Fecha de contratación", "Salario", "Chef principal", "Opciones")
        val cocineros = cocineroRepository.getChefs()

        fun getFechaContratacion(fecha: Date): String {
            val formato = SimpleDateFormat("yyyy-MM-dd")
            formato.timeZone = TimeZone.getTimeZone("UTC") // Establece la zona horaria a UTC
            return formato.format(fecha)
        }
        // Convierte la lista de cocineros a un arreglo de arreglos
        val data = cocineros.map {
            arrayOf(
                it.codigoUnico,
                "${it.nombre} ${it.apellido}",
                it.edad,
                getFechaContratacion(it.fechaContratacion),
                it.salario,
                if(it.isMainChef) "Si" else "No",
                true
            )
        }.toTypedArray()

        val model = DefaultTableModel(data, columnNames)
        val table = JTable(model)
        table.setRowHeight(35)

        val columModel = table.columnModel
        columModel.getColumn(1).preferredWidth = 200
        columModel.getColumn(2).preferredWidth = 50
        columModel.getColumn(3).preferredWidth = 130
        columModel.getColumn(4).preferredWidth = 60
        columModel.getColumn(5).preferredWidth = 90
        columModel.getColumn(6).preferredWidth = 180

        for (column in 0 until table.columnCount) {
            table.getColumnModel().getColumn(column).cellRenderer = CenterRenderer()
        }

        val opcionesColumn = table.getColumn("Opciones")
        opcionesColumn.cellRenderer = ButtonRenderer()
        opcionesColumn.cellEditor = ButtonEditor(table, this, cocineroRepository)

        // Add the table to a scroll pane, then add the scroll pane to the frame
        val scrollPane = JScrollPane(table)
        mainPanel!!.add(scrollPane)
        mainPanel!!.setLayout(null)
        scrollPane.setBounds(20, 130, 850, 450)
    }

    class CenterRenderer : DefaultTableCellRenderer() {
        init {
            horizontalAlignment = SwingConstants.CENTER
        }
    }

    class ButtonRenderer : TableCellRenderer {
        private val panel = JPanel(FlowLayout(FlowLayout.LEFT))
        private val editButton = JButton()
        private val deleteButton = JButton()
        private val viewButton = JButton()

        init {
            editButton.icon = ImageIcon(ImageIcon("src/images/editar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            editButton.setToolTipText("Actualizar datos")
            deleteButton.icon = ImageIcon(ImageIcon("src/images/borrar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            deleteButton.setToolTipText("Eliminar cocinero")
            viewButton.icon = ImageIcon(ImageIcon("src/images/food-and-restaurant.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            viewButton.setToolTipText("Ver comidas")
            panel.add(editButton)
            panel.add(deleteButton)
            panel.add(viewButton)
        }

        override fun getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
            return panel
        }
    }

    class ButtonEditor(private val table: JTable, private val window: JFrame, private val cocineroRepository: CocineroRepository) : AbstractCellEditor(), TableCellEditor, ActionListener {
        private val panel = JPanel(FlowLayout(FlowLayout.LEFT))
        private val editButton = JButton()
        private val deleteButton = JButton()
        private val viewButton = JButton()

        init {
            editButton.icon = ImageIcon(ImageIcon("src/images/editar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            editButton.setToolTipText("Actualizar datos")
            deleteButton.icon = ImageIcon(ImageIcon("src/images/borrar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            deleteButton.setToolTipText("Eliminar cocinero")
            viewButton.icon = ImageIcon(ImageIcon("src/images/food-and-restaurant.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            viewButton.setToolTipText("Ver comidas")
            panel.add(editButton)
            panel.add(deleteButton)
            panel.add(viewButton)
            editButton.addActionListener(this)
            deleteButton.addActionListener(this)
            viewButton.addActionListener(this)
        }

        override fun getTableCellEditorComponent(table: JTable, value: Any, isSelected: Boolean, row: Int, column: Int): Component {
            return panel
        }

        override fun getCellEditorValue(): Any {
            return ""
        }

        override fun actionPerformed(e: ActionEvent) {
            if (e.source == editButton) {
                val selectedRow = table.selectedRow
                // Get the data of the selected row
                val numeroUnico = table.getValueAt(selectedRow, 0) as String

                editButton.addActionListener(object : ActionListener {
                    override fun actionPerformed(e: ActionEvent) {
                        window.isVisible = false
                        ActualizacionCocineroInterface(numeroUnico).isVisible = true
                    }
                })
            } else if (e.source == deleteButton) {
                val selectedRow = table.selectedRow
                // Get the data of the selected row
                val numeroUnico = table.getValueAt(selectedRow, 0) as String

                fun showDeleteConfirmationDialog(): Boolean {
                    val confirmation = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar este cocinero?",
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION
                    )

                    return confirmation == JOptionPane.YES_OPTION
                }

                val confirmacion = showDeleteConfirmationDialog()

                if (confirmacion) {
                    cocineroRepository.deleteByCodigoUnico(numeroUnico)

                    // Actualiza la tabla después de la eliminación
                    (table.model as DefaultTableModel).removeRow(selectedRow)
                }

            } else if (e.source == viewButton) {
                val selectedRow = table.selectedRow
                // Get the data of the selected row
                val numeroUnico = table.getValueAt(selectedRow, 0) as String

//                println(numeroUnico)
                viewButton.addActionListener(object : ActionListener {
                    override fun actionPerformed(e: ActionEvent) {
                        window.isVisible = false
                        ComidaInterface(numeroUnico).isVisible = true
                    }
                })
            }
        }
    }



    private fun mostrarMainInterface() {
        SwingUtilities.invokeLater {
            val frame: JFrame = MainInterface()
            frame.title = "Aplicación cocinero comidas"
            frame.setLocation(600, 250)
            frame.setSize(400, 300)
            frame.isResizable = false
            frame.isVisible = true
        }
    }
}