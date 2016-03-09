package megamek.common.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class LoggingOutputStream extends ByteArrayOutputStream {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Logger logger;
    private Level level;
    
    public LoggingOutputStream(final Logger logger, final Level level) {
        super();
        this.logger = logger;
        this.level = level;
    }

    @Override
    public void flush() throws IOException {
        String record;
        
        synchronized(this) {
            super.flush();
            record = this.toString();
            super.reset();
            
            if (record.length() == 0 || LINE_SEPARATOR.equals(record)) {
                return;
            }
            
            logger.logp(level, "", "", record);
        }
    }
}
