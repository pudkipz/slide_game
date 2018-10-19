import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;

public enum Assets {
    INSTANCE;

    private final String IMAGE_DIR = "file::assets/";
    private final String SOUND_DIR = "file:assets/sound/";

    private final Map<Object, Image> objectImage = new HashMap<>();

    public final Image background = getImage("background.png");

    // public final Image background = new Image("file::assets/background.png");

    {
        bind(Tile.class, getImage("tile.png"));
        bind(Player.class, getImage("player.png"));
    }




    // -------------- Connect classes or objects to images -----------------

    // This will bind an object to an image to be used in GUI
    public <T> void bind(T object, Image image) {
        objectImage.put(object, image);
    }

    // This will bind a class to an image to be used in GUI
    public void bind(Class<?> clazz, Image image) {
        objectImage.put(clazz, image);
    }

    // Get a previously bound image for an object
    public <T> Image get(T a) {
        return objectImage.get(a);
    }

    // Get a previously bound image for some class
    public Image get(Class<?> clazz) {
        return objectImage.get(clazz);
    }

    // ---------- Helpers for file handling ------------------------

    private Image getImage(String fileName) {
        return new Image(IMAGE_DIR + fileName);
    }

    public AudioClip getSound(String fileName) {
        return new AudioClip(SOUND_DIR + fileName);
    }
}
