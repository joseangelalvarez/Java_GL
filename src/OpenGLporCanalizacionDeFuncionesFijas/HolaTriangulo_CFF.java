/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OpenGLporCanalizacionDeFuncionesFijas;

import java.nio.IntBuffer;
import static org.lwjgl.glfw.GLFW.glfwGetFramebufferSize;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glViewport;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author angel
 */
public abstract class HolaTriangulo_CFF {
    
   public static void CFFHolaTriangulo(long window,IntBuffer width,IntBuffer height){
   float ratio;
            glfwGetFramebufferSize(window,width,height);
            ratio=width.get()/(float)height.get();
           
        width.rewind();
        height.rewind();
        glViewport(0,0,width.get(),height.get());
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL20.GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-ratio,ratio,-1f,1f,1f,-1f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glRotatef((float)glfwGetTime()*50f,1f,1f,1f);
        glBegin(GL_TRIANGLES);
            glColor3f(1f, 0f, 0f);
            glVertex3f(-0.6f, -0.4f, 0f);
            glColor3f(0f, 1f, 0f);
            glVertex3f(0.6f, -0.4f, 0f);
            glColor3f(0f, 0f, 1f);
            glVertex3f(0f, 0.6f, 0f);
            glEnd();
        /* Flip buffers for next loop */
        width.flip();
        height.flip();
        }
        }

