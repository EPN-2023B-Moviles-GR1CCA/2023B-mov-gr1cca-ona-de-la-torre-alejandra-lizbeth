package GUI

import Model.Cocinero
import Repository.CocineroRepository
import Repository.ComidaRepository
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

class ComidaInterface(numeroUnico: String): JFrame() {

    var mainPanel: JPanel?= null
    val comidaRepository: ComidaRepository = ComidaRepository("src/data/comidas.json")
    val cocineroRepository: CocineroRepository = CocineroRepository("src/data/cocineros.json")
    val chef: Cocinero ?= cocineroRepository.getChefByCodigoUnico(numeroUnico)

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
                isVisible = false
                CocineroInterface().isVisible = true
            }
        })

        agregarPanel()
        volverCocineroInterface()
        agregarEtiquetas()
        agregarComidas()
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
        var lblTitle = JLabel("Comidas hechas por ${chef!!.nombre} ${chef.apellido}")
        mainPanel!!.add(lblTitle)
        mainPanel!!.setLayout(null)
        lblTitle.setBounds(75,10,750,40)
        lblTitle.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle.foreground = Color.white
        lblTitle.font = Font("Times New Roman", Font.BOLD, 25)
    }

    fun volverCocineroInterface(){
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
                CocineroInterface().isVisible = true
            }
        })
    }

    fun agregarComidas(){
        var btnAgregarComida = JButton("Agregar comida")
        mainPanel!!.add(btnAgregarComida)
        mainPanel!!.setLayout(null)
        btnAgregarComida.setBounds(20,80,150,25)
        btnAgregarComida.background = Color(236,225,195)
        btnAgregarComida.font = Font("Times New Roman", Font.BOLD, 14)

        btnAgregarComida.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {

                if(chef!=null){
                    isVisible = false
                    CreacionComidaInterface(chef).isVisible = true
                }else{
                    JOptionPane.showMessageDialog(this@ComidaInterface,
                        "No se puede agregar un nuevo plato, no se han obtenido los datos del chef",
                        "Error",
                        JOptionPane.ERROR_MESSAGE)
                }
            }
        })
    }

    private fun agregarTabla() {
        val columnNames = arrayOf("Identificador","Nombre", "Fecha de creación", "Es plato del día", "Tipo de cocina", "Cantidad de productos", "Precio", "Opciones")
        val comidas = comidaRepository.getComidasByIdentificadorCocinero(chef!!.codigoUnico)

        fun getFechaCreacion(fecha: Date): String {
            val formato = SimpleDateFormat("yyyy-MM-dd")
            formato.timeZone = TimeZone.getTimeZone("UTC") // Establece la zona horaria a UTC
            return formato.format(fecha)
        }

        // Convierte la lista de cocineros a un arreglo de arreglos
        val data = comidas.map {
            arrayOf(
                it.identificador,
                "${it.nombre}",
                getFechaCreacion(it.fechaCreacion),
                if(it.esPlatoDelDia) "Si" else "No",
                it.tipoCocina,
                it.cantidadProductos,
                it.precio,
                true
            )
        }.toTypedArray()

        val model = DefaultTableModel(data, columnNames)
        val table = JTable(model)
        table.setRowHeight(35)

        val columModel = table.columnModel
        columModel.getColumn(0).preferredWidth = 80
        columModel.getColumn(1).preferredWidth = 150
        columModel.getColumn(2).preferredWidth = 120
        columModel.getColumn(3).preferredWidth = 100
        columModel.getColumn(4).preferredWidth = 100
        columModel.getColumn(5).preferredWidth = 130
        columModel.getColumn(6).preferredWidth = 100
        columModel.getColumn(7).preferredWidth = 150

        for (column in 0 until table.columnCount) {
            table.getColumnModel().getColumn(column).cellRenderer = CenterRenderer()
        }

        val opcionesColumn = table.getColumn("Opciones")
        opcionesColumn.cellRenderer = ButtonRenderer()
        opcionesColumn.cellEditor = ButtonEditor(table, this, chef, comidaRepository)

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

        init {
            editButton.icon = ImageIcon(ImageIcon("src/images/editar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            editButton.setToolTipText("Actualizar datos")
            deleteButton.icon = ImageIcon(ImageIcon("src/images/borrar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            deleteButton.setToolTipText("Eliminar comida")
            panel.add(editButton)
            panel.add(deleteButton)
        }

        override fun getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
            return panel
        }
    }

    class ButtonEditor(private val table: JTable, private val window: JFrame, private val chef: Cocinero?, private val comidaRepository: ComidaRepository) : AbstractCellEditor(), TableCellEditor,
        ActionListener {
        private val panel = JPanel(FlowLayout(FlowLayout.LEFT))
        private val editButton = JButton()
        private val deleteButton = JButton()

        init {
            editButton.icon = ImageIcon(ImageIcon("src/images/editar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            editButton.setToolTipText("Actualizar datos")
            deleteButton.icon = ImageIcon(ImageIcon("src/images/borrar.png").image.getScaledInstance(20,20, Image.SCALE_FAST))
            deleteButton.setToolTipText("Eliminar comida")
            panel.add(editButton)
            panel.add(deleteButton)
            editButton.addActionListener(this)
            deleteButton.addActionListener(this)
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
                val identificador = table.getValueAt(selectedRow, 0) as String

                editButton.addActionListener(object : ActionListener {
                    override fun actionPerformed(e: ActionEvent) {
                        window.isVisible = false
                        ActualizarComidaInterface(chef, identificador).isVisible = true
                    }
                })
            } else if (e.source == deleteButton) {
                val selectedRow = table.selectedRow
                // Get the data of the selected row
                val identificador = table.getValueAt(selectedRow, 0) as String

                fun showDeleteConfirmationDialog(): Boolean {
                    val confirmation = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar esta comida?",
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION
                    )

                    return confirmation == JOptionPane.YES_OPTION
                }

                val confirmacion = showDeleteConfirmationDialog()

                if (confirmacion) {
                    comidaRepository.deleteComidaByIdentificadorAndCodigoChef(identificador, chef!!.codigoUnico)
                    // Actualiza la tabla después de la eliminación
                    (table.model as DefaultTableModel).removeRow(selectedRow)
                }
            }
        }
    }
}