package GUI

import Model.Cocinero
import Model.Comida
import Repository.ComidaRepository
import java.awt.Color
import java.awt.Font
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.*
import javax.swing.*

class ActualizarComidaInterface(chefParam: Cocinero?, identificador: String): JFrame() {

    val comidaRepository: ComidaRepository = ComidaRepository()
    val chef = chefParam
    val identificador = identificador
    var mainPanel: JPanel?= null
    var txtIdentificador: JTextField?= null
    var txtNombre: JTextField?= null
    var txtFechaCreacion: JTextField?= null
    var txtEsPlatoDelDia: JTextField?= null
    var txtTipoCocina: JTextField?= null
    var txtCantidadProductos: JTextField?= null
    var txtPrecio: JTextField?= null

    init {
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        title = "Aplicación cocinero comidas"
        contentPane.background = Color(255, 255, 255)
        setSize(450, 500)
        setLocation(400,80)
        isResizable = false
        // Agregar un WindowListener para manejar el evento de cierre de la ventana
        addWindowListener(object : WindowAdapter() {
            override fun windowClosed(e: WindowEvent) {
                // Lógica para volver a abrir MainInterface cuando se cierre CocineroInterface
                ComidaInterface(chef!!.codigoUnico).isVisible = true
            }
        })

        agregarPanel()
        volverComidaInterface()
        agregarEtiquetas()
        agregarCampoTexto()
        guardarActualizacion()
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
        var lblTitle = JLabel("Bienvenido")
        var lblTitle2 = JLabel("Ingresa los datos del nuevo plato")

        var lblIdentificador = JLabel("Identificador: ")
        var lblNombre = JLabel("Nombre: ")
        var lblFechaCreacion = JLabel("Fecha de creación: ")
        var lblEsPlatoDia = JLabel("¿Es el plato del día? ")
        var lblTipoCocina = JLabel("Tipo de cocina: ")
        var lblCantidadProductos = JLabel("Cantidad de productos: ")
        var lblPrecio = JLabel("Precio: ")
        mainPanel!!.add(lblTitle)
        mainPanel!!.add(lblTitle2)
        mainPanel!!.add(lblIdentificador)
        mainPanel!!.add(lblNombre)
        mainPanel!!.add(lblFechaCreacion)
        mainPanel!!.add(lblEsPlatoDia)
        mainPanel!!.add(lblTipoCocina)
        mainPanel!!.add(lblCantidadProductos)
        mainPanel!!.add(lblPrecio)

        mainPanel!!.setLayout(null)
        lblTitle.setBounds(150,10,150,40)
        lblTitle.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle.foreground = Color(236,225,195)
        lblTitle.font = Font("Times New Roman", Font.BOLD, 25)

        lblTitle2.setBounds(75,60,300,30)
        lblTitle2.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle2.foreground = Color(236,225,195)
        lblTitle2.font = Font("Times New Roman", Font.BOLD, 20)

        lblIdentificador.setBounds(30,100,150,30)
        lblIdentificador.horizontalAlignment = 2 //alineado izquierdo
        lblIdentificador.foreground = Color.white
        lblIdentificador.font = Font("Times New Roman", Font.BOLD, 14)

        lblNombre.setBounds(30,130,150,30)
        lblNombre.horizontalAlignment = 2
        lblNombre.foreground = Color.white
        lblNombre.font = Font("Times New Roman", Font.BOLD, 14)

        lblFechaCreacion.setBounds(30,160,150,30)
        lblFechaCreacion.horizontalAlignment = 2
        lblFechaCreacion.foreground = Color.white
        lblFechaCreacion.font = Font("Times New Roman", Font.BOLD, 14)

        lblEsPlatoDia.setBounds(30,190,150,30)
        lblEsPlatoDia.horizontalAlignment = 2
        lblEsPlatoDia.foreground = Color.white
        lblEsPlatoDia.font = Font("Times New Roman", Font.BOLD, 14)

        lblTipoCocina.setBounds(30,220,150,30)
        lblTipoCocina.horizontalAlignment = 2
        lblTipoCocina.foreground = Color.white
        lblTipoCocina.font = Font("Times New Roman", Font.BOLD, 14)

        lblCantidadProductos.setBounds(30,250,150,30)
        lblCantidadProductos.horizontalAlignment = 2
        lblCantidadProductos.foreground = Color.white
        lblCantidadProductos.font = Font("Times New Roman", Font.BOLD, 14)

        lblPrecio.setBounds(30,280,150,30)
        lblPrecio.horizontalAlignment = 2
        lblPrecio.foreground = Color.white
        lblPrecio.font = Font("Times New Roman", Font.BOLD, 14)
    }

    fun agregarCampoTexto(){
        txtIdentificador = JTextField()
        txtNombre = JTextField()
        txtFechaCreacion = JTextField()
        txtEsPlatoDelDia = JTextField()
        txtTipoCocina = JTextField()
        txtCantidadProductos = JTextField()
        txtPrecio = JTextField()
        mainPanel!!.add(txtIdentificador)
        mainPanel!!.add(txtNombre)
        mainPanel!!.add(txtFechaCreacion)
        mainPanel!!.add(txtEsPlatoDelDia)
        mainPanel!!.add(txtTipoCocina)
        mainPanel!!.add(txtCantidadProductos)
        mainPanel!!.add(txtPrecio)
        mainPanel!!.setLayout(null)
        txtIdentificador!!.setBounds(200,110,200,20)
        txtIdentificador!!.foreground = Color.BLACK
//        txtCodigoUnico.background = Color(142,189,182)
        txtIdentificador!!.horizontalAlignment = 0
        txtIdentificador!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtNombre!!.setBounds(200,140,200,20)
        txtNombre!!.foreground = Color.BLACK
        txtNombre!!.horizontalAlignment = 0
        txtNombre!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtFechaCreacion!!.setBounds(200,170,200,20)
        txtFechaCreacion!!.foreground = Color.BLACK
        txtFechaCreacion!!.horizontalAlignment = 0
        txtFechaCreacion!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtEsPlatoDelDia!!.setBounds(200,200,200,20)
        txtEsPlatoDelDia!!.foreground = Color.BLACK
        txtEsPlatoDelDia!!.horizontalAlignment = 0
        txtEsPlatoDelDia!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtTipoCocina!!.setBounds(200,230,200,20)
        txtTipoCocina!!.foreground = Color.BLACK
        txtTipoCocina!!.horizontalAlignment = 0
        txtTipoCocina!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtCantidadProductos!!.setBounds(200,260,200,20)
        txtCantidadProductos!!.foreground = Color.BLACK
        txtCantidadProductos!!.horizontalAlignment = 0
        txtCantidadProductos!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtPrecio!!.setBounds(200,290,200,20)
        txtPrecio!!.foreground = Color.BLACK
        txtPrecio!!.horizontalAlignment = 0
        txtPrecio!!.font = Font("Times New Roman", Font.PLAIN, 13)

        llenarCampos()
    }

    fun llenarCampos(){
        val comida: Comida ?= comidaRepository.getComidaByIdentificador(identificador)
        txtIdentificador!!.setText(identificador)
        txtNombre!!.setText(comida!!.nombre)
        txtFechaCreacion!!.setText(comida.fechaCreacion.toString())
        txtEsPlatoDelDia!!.setText(
            if(comida.esPlatoDelDia) "Si" else "No"
        )
        txtTipoCocina!!.setText(comida.tipoCocina)
        txtCantidadProductos!!.setText(comida.cantidadProductos.toString())
        txtPrecio!!.setText(comida.precio.toString())

    }
    fun volverComidaInterface(){
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
                ComidaInterface(chef!!.codigoUnico).isVisible = true
            }
        })
    }

    fun guardarActualizacion(){
        var btnAgregarCocinero = JButton("Guardar")
        mainPanel!!.add(btnAgregarCocinero)
        mainPanel!!.setLayout(null)
        btnAgregarCocinero.setBounds(150,370,150,25)
        btnAgregarCocinero.background = Color(236,225,195)
        btnAgregarCocinero.font = Font("Times New Roman", Font.BOLD, 14)

        btnAgregarCocinero.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {

                val identificador: String = txtIdentificador!!.getText()
                val nombre: String = txtNombre!!.getText()
                val fechaCreacion: Date = Date(txtFechaCreacion!!.getText())
                val esPlatoDia: Boolean = txtEsPlatoDelDia!!.getText().equals("Si")
                val tipoCocina: String = txtTipoCocina!!.getText()
                val cantidadProductos: Int = txtCantidadProductos!!.getText().toInt()
                val precio: Double = txtPrecio!!.getText().toDouble()

                val newFood: Comida = Comida(
                    identificador, nombre, fechaCreacion, esPlatoDia, tipoCocina, cantidadProductos, precio, chef
                )

                println(newFood.cantidadProductos)
                println(newFood.fechaCreacion)
                println(newFood.identificador)
                println(newFood.nombre)
                println(newFood.esPlatoDelDia)

                limpiarCampos()

//                isVisible = false
//                CocineroInterface().isVisible = true
            }
        })
    }

    fun limpiarCampos(){
        txtIdentificador!!.setText("")
        txtNombre!!.setText("")
        txtFechaCreacion!!.setText(null)
        txtEsPlatoDelDia!!.setText(null)
        txtTipoCocina!!.setText("")
        txtCantidadProductos!!.setText(null)
        txtPrecio!!.setText(null)
    }

}