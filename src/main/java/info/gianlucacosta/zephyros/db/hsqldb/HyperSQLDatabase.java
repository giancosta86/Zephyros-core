/*^
  ===========================================================================
  Zephyros - Core
  ===========================================================================
  Copyright (C) 2017 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.zephyros.db.hsqldb;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

/**
 * HyperSQL database. Can be both on-disk and in-memory, according to its
 * constructor parameters.
 */
public class HyperSQLDatabase {
    private final Optional<Path> rootDirectoryPathOption;
    private final String connectionString;


    public HyperSQLDatabase() {
        this(Optional.empty());
    }

    public HyperSQLDatabase(Path rootDirectoryPath) {
        this(Optional.of(rootDirectoryPath));
    }

    /**
     * @param rootDirectoryPathOption The root directory is the directory containing
     *                                both the global database files and the actual
     *                                data subdirectory
     */
    public HyperSQLDatabase(Optional<Path> rootDirectoryPathOption) {
        this.rootDirectoryPathOption = rootDirectoryPathOption;

        Optional<Path> dataDirectoryPathOption =
                rootDirectoryPathOption.map(rootDirectoryPath ->
                        rootDirectoryPath.resolve("data")
                );

        this.connectionString =
                dataDirectoryPathOption
                        .map(dataDirectoryPath ->
                                String.format(
                                        "jdbc:hsqldb:file:%s",
                                        dataDirectoryPath
                                )
                        )
                        .orElseGet(() ->
                                String.format(
                                        "jdbc:hsqldb:mem:%s",
                                        UUID.randomUUID()
                                )
                        );
    }

    /**
     * @return The root directory for the database - containing both the global files
     * and the actual data subdirectory
     */
    public Optional<Path> getRootDirectoryPath() {
        return rootDirectoryPathOption;
    }


    public boolean isInMemory() {
        return !rootDirectoryPathOption.isPresent();
    }


    /**
     * @return true if the database is on-disk and its root directory was created
     */
    public boolean exists() {
        return rootDirectoryPathOption
                .map(Files::isDirectory)
                .orElse(false);
    }


    /**
     * @return The connection string to the database - as required,
     * for example, by JDBC and Hibernate
     */
    public String getConnectionString() {
        return connectionString;
    }
}
