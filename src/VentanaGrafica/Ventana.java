/*
 * VentanaGrafica permite crear una ventana GLFW
 * que nos servira de contexto para OpenGL
 */
package VentanaGrafica;



import static OpenGLporCanalizacionDeFuncionesFijas.HolaTriangulo_CFF.*;
import java.nio.IntBuffer;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryUtil;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 *
 * @author angel
 */
public abstract class Ventana {
     private static final GLFWErrorCallback errorCallback=GLFWErrorCallback.createPrint(System.err);
     private static final GLFWKeyCallback keyCallback=new GLFWKeyCallback() {
         @Override
         public void invoke(long window, int key, int scancode, int action, int mods) {
             if(key==GLFW_KEY_ESCAPE && action ==GLFW_PRESS){
             glfwSetWindowShouldClose(window,true);
             }
                 
         }
     };

    /**
     *OpeGL no ofrece una ventana para la salida del Renderizado de los gráficos 3d
     * Creamos una Ventana mendiante la libreía glfw 
     */
    public static void iniciarVentana()
    {
       long window;
       String titulo="miventana";
       if(!glfwInit()){
           throw new IllegalStateException("Imposible inicializar GLFW");
         }
        glfwSetErrorCallback(errorCallback);
       window=GLFW.glfwCreateWindow(600, 600, titulo, NULL, NULL);
     
       if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Fallo en la creación de la ventana GLFW");
        }
       GLFWVidMode vidMode;
       vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
       if(vidMode==null){
       glfwTerminate();
            throw new RuntimeException("No se reconocio Monitor Primario");
         }
       glfwSetWindowPos(window, (vidMode.width()/2), (vidMode.height()/2));
         
         /* CREAR CONTEXTO OPENGL*/
         
       glfwMakeContextCurrent(window);
       GLCapabilities gl;
       gl=GL.createCapabilities();
       glfwSwapInterval(1);
       /*Establece el cierre de ventana*/
       glfwSetKeyCallback(window,keyCallback);
       IntBuffer width=MemoryUtil.memAllocInt(1);
       IntBuffer height=MemoryUtil.memAllocInt(1);
         
         /* LOOP */
       while(!glfwWindowShouldClose(window)){
            CFFHolaTriangulo(window,width,height);
            glfwPollEvents();
            GLFW.glfwSwapBuffers(window);
         }
}
}
