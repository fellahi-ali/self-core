/**
 * Copyright (c) 2020, Self XDSD Contributors
 * All rights reserved.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to read the Software only. Permission is hereby NOT GRANTED to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.selfxdsd.api;

import java.math.BigDecimal;

/**
 * A collaboration Contract between a Project and a Contributor.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 0.0.1
 */
public interface Contract {

    /**
     * The Project.
     * @return Project.
     */
    Project project();

    /**
     * The Contributor.
     * @return Contributor.
     */
    Contributor contributor();

    /**
     * The Contributor's hourly rate in USD Cents.
     * @return BigDecimal.
     */
    BigDecimal hourlyRate();

    /**
     * The Contributor's role (DEV, QA, ARCH etc).
     * @return String.
     */
    String role();

    /**
     * Possible roles in a Contract.
     */
    class Roles {

        /**
         * Hidden ctor.
         */
        private Roles(){ }

        /**
         * Constant for the architect role.
         */
        public static final String ARCH = "ARCH";

        /**
         * Constant for the developer role.
         */
        public static final String DEV = "DEV";

        /**
         * Constant for the reviewer role.
         */
        public static final  String REV = "REV";

        /**
         * Constant for the QA role.
         */
        public static final String QA = "QA";

        /**
         * Constant for the chatbot role.
         */
        public static final String BOT = "BOT";

        /**
         * Constant for the Project Manager role.
         */
        public static final String PM = "PM";

        /**
         * Constant for the Project Owner role.
         */
        public static final String PO = "PO";
    }
}
