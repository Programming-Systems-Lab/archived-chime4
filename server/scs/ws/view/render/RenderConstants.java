package psl.chime4.server.scs.ws.view.render;

/**
 * Constants that have to deal with the distrubted 3D engine.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface RenderConstants
{
   /** colors defining the different constants **/
   public static final int COLOR_WHITE = 0;
   public static final int COLOR_BLACK = 10;
   public static final int COLOR_BLUE = 20;
   public static final int COLOR_RED = 30;
   public static final int COLOR_GREEN = 40;
   public static final int COLOR_YELLOW = 50;
   public static final int COLOR_MAGENTA = 60;
   public static final int COLOR_CYAN = 70;
   public static final int COLOR_TRANSPARENT = 80;
   
   /** constants and uris defining the different instructions **/
   public static final int RENDER_3D_TEXT = 10;
   public static final String RENDER_3D_TEXT_URI = "ri:render_3d_text";
   
   public static final int RENDER_BILLBOARD = 20;
   public static final String RENDER_BILLBOARD_URI = "ri:render_billboard";
   
   public static final int RENDER_FOG_EFFECT = 30;
   public static final String RENDER_FOG_EFFECT_URI = "ri:render_fog_effect";
   
   public static final int RENDER_GEOMETRY = 40;
   public static final String RENDER_GEOMETRY_URI = "ri:render_geometry";
   
   public static final int RENDER_INSTRUCTION_CHAIN = 50;
   public static final String RENDER_INSTRUCTION_CHAIN_URI = 
      "ri:render_instruction_chain";
   
   public static final int RENDER_LIGHT = 60;
   public static final String RENDER_LIGHT_URI = "ri:render_light";
   
   public static final int RENDER_MODEL = 70;
   public static final String RENDER_MODEL_URI = "ri:render_model";
   
   public static final int RENDER_ROTATION = 80;
   public static final String RENDER_ROTATION_URI = "ri:render_rotation";
   
   public static final int RENDER_SCALING = 90;
   public static final String RENDER_SCALING_URI = "ri:render_scaling";
   
   public static final int RENDER_SOUND = 100;
   public static final String RENDER_SOUND_URI = "ri:render_sound";
   
   public static final int RENDER_SPEECH = 110;
   public static final String RENDER_SPEECH_URI = "ri:render_speech";
   
   public static final int RENDER_TEXTURE = 120;
   public static final String RENDER_TEXTURE_URI = "ri:render_texture";
   
   public static final int RENDER_TRANSLATION = 130;
   public static final String RENDER_TRANSLATION_URI = 
      "ri:render_translation";
   
   /** constants defining the different geometries **/
   public static final int CONE_GEOMETRY = 0;
   public static final String CONE_GEOMETRY_URI = "geom:cone";
   
   public static final int CUBE_GEOMETRY = 10;
   public static final String CUBE_GEOMETRY_URI = "geom:cube";
   
   public static final int CYLINDER_GEOMETRY = 20;
   public static final String CYLINDER_GEOMETRY_URI = "geom:cylinder";
   
   public static final int SPHERE_GEOMETRY = 30;
   public static final String SPHERE_GEOMETRY_URI = "geom:sphere";
   
   public static final int POLYGON_GEOMETRY = 40;
   public static final String POLYGON_GEOMETRY_URI = "geom:polygon";
}
