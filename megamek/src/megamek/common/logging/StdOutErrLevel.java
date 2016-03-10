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

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.util.logging.Level;

/**
 *
 * @author Nick Stephen
 * @see https://blogs.oracle.com/nickstephen/entry/java_redirecting_system_out_and
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