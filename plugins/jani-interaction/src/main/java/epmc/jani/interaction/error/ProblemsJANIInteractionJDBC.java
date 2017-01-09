package epmc.jani.interaction.error;

import epmc.error.Problem;
import epmc.error.UtilError;

public final class ProblemsJANIInteractionJDBC {
	/** Base name of resource file containing plugin problem descriptions. */
    private final static String ERROR_JANI_INTERACTION_JDBC = "ErrorJANIInteractionJDBC";
    /** Timeout when connecting to JDBC database. */
    public final static Problem JANI_INTERACTION_JDBC_CONNECTION_TIMEOUT = newProblem("jani-interaction-jdbc-connection-timeout");
    /** Error when connecting to JDBC database. */
    public final static Problem JANI_INTERACTION_JDBC_CONNECTION_ERROR = newProblem("jani-interaction-jdbc-connection-error");
    /** Error when connecting preparing statement. */
    public final static Problem JANI_INTERACTION_JDBC_PREPARE = newProblem("jani-interaction-jdbc-prepare");
    /** Inconsistent setting of driver JAR/class. */
    public final static Problem JANI_INTERACTION_JDBC_DRIVER_JAR_CLASS = newProblem("jani-interaction-jdbc-driver-jar-class");
    /** Failed to find driver class. */
    public final static Problem JANI_INTERACTION_JDBC_DRIVER_CLASS_NOT_FOUND = newProblem("jani-interaction-jdbc-driver-class-not-found");
    /** Failed to instantiate driver class. */
    public final static Problem JANI_INTERACTION_JDBC_DRIVER_CLASS_INSTANTIATION_FAILED = newProblem("jani-interaction-jdbc-driver-class-instantiation-failed");
    /** Failed to instantiate driver class. */
    public final static Problem JANI_INTERACTION_JDBC_DRIVER_CLASS_REGISTER_FAILED = newProblem("jani-interaction-jdbc-driver-class-register-failed");
    /** General problem during SQL operation. */
    public final static Problem JANI_INTERACTION_JDBC_SQL_ERROR = newProblem("jani-interaction-jdbc-sql-error");

	/**
	 * Create new problem object using plugin resource file.
	 * The name parameter must not be {@code null}.
	 * 
	 * @param name problem identifier String
	 * @return newly created problem identifier
	 */
    private static Problem newProblem(String name) {
    	assert name != null;
    	return UtilError.newProblem(ERROR_JANI_INTERACTION_JDBC, name);
    }

    /**
     * Private constructor to prevent instantiation of this class.
     */
	private ProblemsJANIInteractionJDBC() {
	}
}
