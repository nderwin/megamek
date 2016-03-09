package megamek.common.logging;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.util.logging.Level;

/**
 *
 */
public class StdOutErrLevel extends Level {
    
    private static final long serialVersionUID = -4341237222913163784L;

    public static Level STDOUT = new StdOutErrLevel("STDOUT", Level.INFO.intValue() + 53);
    public static Level STDERR = new StdOutErrLevel("STDERR", Level.INFO.intValue() + 54);
    
    private StdOutErrLevel(final String name, final int value) {
        super(name, value);
    }
    
    protected Object readResolve() throws ObjectStreamException {
        if (STDOUT.intValue() == this.intValue()) {
            return STDOUT;
        }
        
        if (STDERR.intValue() == this.intValue()) {
            return STDERR;
        }
        
        throw new InvalidObjectException("Unknown instance :" + this);
    }
}
