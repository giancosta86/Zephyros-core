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

package info.gianlucacosta.zephyros.db.sql;

import java.util.Arrays;
import java.util.Optional;

/**
 * Common SQL states
 */
public enum SqlState {
    UNIQUE_VIOLATION("23505");

    public static Optional<SqlState> getByCode(String code) {
        return
                Arrays
                        .stream(SqlState.values())
                        .filter(sqlState ->
                                sqlState.code.equals(code)
                        )
                        .findAny();
    }


    private final String code;


    SqlState(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}
