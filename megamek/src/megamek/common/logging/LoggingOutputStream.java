/*
 * MegaMek - Copyright (C) 2000,2001,2002,2003,2004 Ben Mazur (bmazur@sev.org)
 * Copyright © 2013 Edward Cullen (eddy@obsessedcomputers.co.uk)
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation; either version 2 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 */
package megamek.common.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick Stephen
 * @see https://blogs.oracle.com/nickstephen/entry/java_redirecting_system_out_and
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
