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

package info.gianlucacosta.zephyros.swing.dialogs;

import info.gianlucacosta.zephyros.os.User;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Optional;

/**
 * Standard, handy "About..." dialog
 */
public class AboutBox extends JDialog {
    public AboutBox(
            Image windowIcon,
            Image productImage,
            String productName,
            String productVersion,
            String copyrightHolder,
            Optional<String> facebookPageOption
    ) {
        setIconImage(windowIcon);

        setTitle("About...");

        setResizable(false);

        setModal(true);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setMinimumSize(
                new Dimension(500, 0)
        );


        setLayout(new BorderLayout());

        add(new JPanel() {
                {
                    setBackground(Color.WHITE);

                    setBorder(
                            BorderFactory.createEmptyBorder(
                                    20, 20, 20, 20
                            )
                    );

                    setLayout(
                            new BoxLayout(
                                    this,
                                    BoxLayout.PAGE_AXIS
                            )
                    );

                    add(
                            new JLabel(
                                    new ImageIcon(productImage)
                            ) {
                                {
                                    setBorder(
                                            BorderFactory.createEmptyBorder(
                                                    0,
                                                    0,
                                                    20,
                                                    0
                                            )
                                    );

                                    setAlignmentX(Component.CENTER_ALIGNMENT);
                                }
                            }
                    );


                    add(
                            new JLabel(productName) {
                                {
                                    setFont(
                                            getFont()
                                                    .deriveFont(Font.BOLD, 45)
                                    );

                                    setBorder(
                                            BorderFactory.createEmptyBorder(
                                                    0,
                                                    0,
                                                    15,
                                                    0
                                            )
                                    );

                                    setAlignmentX(Component.CENTER_ALIGNMENT);
                                }
                            }
                    );


                    add(
                            new JLabel(
                                    String.format(
                                            "Version %s",
                                            productVersion
                                    )
                            ) {
                                {
                                    setFont(
                                            getFont()
                                                    .deriveFont(Font.BOLD, 20)
                                    );

                                    setBorder(
                                            BorderFactory.createEmptyBorder(
                                                    0,
                                                    0,
                                                    25,
                                                    0
                                            )
                                    );

                                    setAlignmentX(Component.CENTER_ALIGNMENT);
                                }
                            }
                    );

                    add(
                            new JLabel(
                                    String.format(
                                            "Copyright © %s",
                                            copyrightHolder
                                    )
                            ) {
                                {
                                    setFont(
                                            getFont()
                                                    .deriveFont(Font.ITALIC, 13)
                                    );

                                    setAlignmentX(Component.CENTER_ALIGNMENT);
                                }
                            }
                    );
                }
            },

                BorderLayout.CENTER
        );


        add(
                new JPanel() {
                    {
                        setBackground(Color.WHITE);

                        setBorder(
                                BorderFactory.createEmptyBorder(0, 20, 20, 20)
                        );

                        setLayout(new FlowLayout());

                        add(
                                new JButton("OK") {
                                    {
                                        addActionListener(event ->
                                                AboutBox.this.dispose()
                                        );

                                        AboutBox.this.getRootPane().setDefaultButton(this);
                                    }
                                }
                        );


                        facebookPageOption.ifPresent(facebookPage ->
                                add(
                                        new JButton("Facebook page") {
                                            {
                                                addActionListener(event ->
                                                        User.openBrowser(facebookPage)
                                                );
                                            }
                                        }
                                )
                        );
                    }
                },

                BorderLayout.SOUTH
        );


        pack();

        setLocationRelativeTo(null);
    }
}
