package test.testapp1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;


public class MainActivity extends ActionBarActivity {

    public Button Button1;
    public Button Button2;
    public EditText MytextEditText;
    public EditText EditTextA;
    public EditText EditTextB;
    public TextView TextViewA;
    public TextView TextViewB;
    public EditText TextViewFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.Button2);
        MytextEditText = (EditText) findViewById(R.id.EditText1);
        TextViewA = (TextView) findViewById(R.id.instruction_a);
        TextViewB = (TextView) findViewById(R.id.instruction_b);
        TextViewFinal = (EditText) findViewById(R.id.TextView_final);
        EditTextA = (EditText) findViewById(R.id.clef_a);
        EditTextB = (EditText) findViewById(R.id.clef_b);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButton1Click(View view) { //Les instructions que l'ordinateur doit effectuer si on appuie sur le bouton "Coder".

        char[] tableauMinuscules = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '?', ' '};
        char[] tableauMajuscules = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ';', ':', 'à'};

        String clefA = EditTextA.getText().toString();
        String clefB = EditTextB.getText().toString();
        if (clefA.length() == 0 || clefB.length() == 0) { //On commence par vérifier que l'utilisateur a renseigné les deux clefs.

            Toast.makeText(this, "Entrez un nombre dans chaque clef !", Toast.LENGTH_LONG).show();

        } else {

            int a = Integer.parseInt(clefA); //On transforme les clefs en nombres.
            int b = Integer.parseInt(clefB);

            if (a <= 0 || a >= 29) { //On vérifie que ces nombres sont bien dans les intervalles demandés.

                Toast.makeText(this,"La première clef doit être comprise entre 0 et 29 !", Toast.LENGTH_LONG).show();

            } else if (b <= 0 || b >= 101) { //S'il y a une erreur, on affiche un message pour l'utilisateur.

                Toast.makeText(this, "La deuxième clef doit être comprise entre 0 et 101 !", Toast.LENGTH_LONG).show();

            } else {//S'il n'y a aucune erreur, on peut démarrer le programme à proprement parler.

                String myText = MytextEditText.getText().toString(); //Le téléphone récupère le texte à coder.
                String affichage = "";//Cette variable va nous servir à afficher le texte codé. Pour l'instant, elle est vide.

                int c = myText.length();
                int rang = 100;
                char lettre = 'A';

                for (int i = 0; i <= c - 1; i++) { //Cette boucle va permettre d'isoler un à un chaque caractère

                    char charac = myText.charAt(i);
                    String characS = String.valueOf(charac);
                    rang = 100;
                    for (int j = 0; j <= 28; j++) { //Celle-ci, de tester la correspondance avec chacun des caractères du tableau pour retrouver son rang.
                        char test = tableauMinuscules[j];
                        String testS = String.valueOf(test);
                        if (characS.equals(testS)) {

                            rang = j;
                            rang = a * rang + b;
                            rang = rang % 29;
                            lettre = tableauMinuscules[rang];
                            String lettreS = String.valueOf(lettre);
                            affichage = affichage + lettreS;

                        }
                    }
                    if (rang == 100) { //et s'il n'y a aucune correspondance, le caractère n'est pas codé.

                        affichage = affichage + characS;

                    }

                }


                TextViewFinal.setText(affichage);
            }

        }

    }

    public void onButton2Click(View view) {

        char[] tableauMinuscules = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '?', ' '};
        char[] tableauMajuscules = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ';', ':', 'à'};
        int[] tableauCoefficients = new int[]{0, 1, 15, 10, 22, 6, 5, 25, 11, 13, 3, 8, 17, 9, 27, 2, 20, 12, 21, 26, 16, 18, 4, 24, 23, 7, 19, 14, 28};


        String clefA = EditTextA.getText().toString();
        String clefB = EditTextB.getText().toString();
        if (clefA.length() == 0 || clefB.length() == 0) {

            Toast.makeText(this, "Entrez un nombre dans chaque clef !", Toast.LENGTH_LONG).show();

        } else {

            int a = Integer.parseInt(clefA);
            int b = Integer.parseInt(clefB);

            if (a <= 0 || a >= 29) {

                Toast.makeText(this, "La première clef doit être comprise entre 0 et 29 !", Toast.LENGTH_LONG).show();

            } else if (b <= 0 || b >= 101) {

                Toast.makeText(this, "La deuxième clef doit être comprise entre 0 et 101 !", Toast.LENGTH_LONG).show();

            } else {
                String myText = MytextEditText.getText().toString();
                String affichage = "";

                int c = myText.length();
                int rang = 100;
                char lettre = 'A';
                int coeff = tableauCoefficients[a]; //On prend le coefficient qui correspond à la première clef.
                int decod = 101;

                for (int i = 0; i <= c - 1; i++) { //On isole un par un les caractères.

                    char charac = myText.charAt(i);
                    String characS = String.valueOf(charac);
                    rang = 100;
                    for (int j = 0; j <= 28; j++) { //On cherche le rang de la lettre codée...

                        char test = tableauMinuscules[j];
                        String testS = String.valueOf(test);
                        if (characS.equals(testS)) {

                            rang = j;
                            decod = (coeff * rang) - (coeff * b); //et on décode ce rang pour obtenir celui de la lettre de départ.
                            if (decod < 0) {

                                while (decod < 0) {

                                    decod = decod + 29;

                                }

                            } else if (decod > 29) {

                                while (decod > 29) {

                                    decod = decod - 29;

                                }

                            }
                            lettre = tableauMinuscules[decod];
                            String lettreS = String.valueOf(lettre);
                            affichage = affichage + lettreS;
                        }


                    }

                    if (rang == 100) {

                        affichage = affichage + characS;

                    }

                }

                TextViewFinal.setText(affichage); //Enfin, on affiche le message décodé.
            }
        }
    }
}