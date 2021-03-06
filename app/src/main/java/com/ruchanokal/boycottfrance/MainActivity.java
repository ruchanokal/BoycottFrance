package com.ruchanokal.boycottfrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.muddzdev.styleabletoast.StyleableToast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    int s=0;
    String m;

    AdView mAdView;
    private InterstitialAd mInterstitialAd;

    ExpandableListViewAdapter expandableListViewAdapter;
    ExpandableListView expandableListView;
    List<String> groupList;
    HashMap<String, List<String>> productList;
    HashMap<String,List<String>>  detailList;
    HashMap<String,List<Bitmap>>  imageList;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    StorageReference newReference;
    UploadTask uploadTask;
    FirebaseDatabase firebaseDatabase;
    StorageReference imageName2;
    DatabaseReference databaseReference;
    ListView listView2;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Main Activity - Banner-ID***
        //ca-app-pub-5016889744069609/6692651260

        //MainToDetail Interstitial ID***
        //ca-app-pub-5016889744069609/4123162395

        //Banner Test ID
        //ca-app-pub-3940256099942544/6300978111

        //Interstitial Test ID
        //ca-app-pub-3940256099942544/1033173712


        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseDatabase = firebaseDatabase.getInstance();

        expandableListView = findViewById(R.id.expandableListView);
        listView2 = findViewById(R.id.listViewTurkce);

        showList();


        expandableListViewAdapter = new ExpandableListViewAdapter(this,groupList,productList);
        expandableListView.setAdapter(expandableListViewAdapter);

        //getUri();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5016889744069609/4123162395");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    /*
    private void getUri() {


        if(s==0) {
            s++;

            Uri airUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/airfrance");
            Uri allegraUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/allegra");
            Uri axaUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/axasigorta");
            Uri beneteaUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/beneteau");
            Uri benzacUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/benzac");
            Uri benzagelUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/benzagel");
            Uri benzamycinUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/benzamycin");
            Uri berlutiUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/berluti");
            Uri bicUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/bic3");
            Uri biothermUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/biotherm");
            Uri bledinaUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/bledina");
            Uri bnpUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/bnpparibas3");
            Uri cacharelUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/cacharel");
            Uri carrefourUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/carrefour");
            Uri cartierUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/cartier");
            Uri chanelUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/chanel");
            Uri christianDiorUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/christiandior");
            Uri chyrsoUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/chryso");
            Uri citroenUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/citroen");
            Uri clarinsUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/clarinsnew");
            Uri daciaUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/dacia");
            Uri danielUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/danielhechter");
            Uri danoneUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/danone");
            Uri decathlonUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/decathlon");
            Uri derhyUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/derhy");
            Uri dpamUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/dpam");
            Uri drakkarnoirUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/drakkarnoir");
            Uri duvalUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/duvalmessien");
            Uri elfUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/elf");
            Uri equitableUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/equitablelife");
            Uri evianUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/evian");
            Uri fahrenheitUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/fahrenheit4");
            Uri frankUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/frank");
            Uri fredjoallierUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/fredjoallier");
            Uri gerardUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/gerard");
            Uri gimaUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/gima");
            Uri givenchyUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/givenchy1");
            Uri groupamaUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/groupama");
            Uri guerbetUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/guerbet2");
            Uri guerlainUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/guerlain3");
            Uri hermesUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/hermes");
            Uri kerastaseUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/kerastase");
            Uri lacosteUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/lacoste");
            Uri lafargeUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/lafarge");
            Uri lancelUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/lancel");
            Uri lancomeUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/lancome");
            Uri larochePosayUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/larocheposay");
            Uri lavacheUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/lavachequirit");
            Uri lecoqUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/lecoq2");
            Uri legrandUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/legrand2");
            Uri longchampUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/longchamp");
            Uri lorealUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/lorealparis");
            Uri louisVuittonUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/louisvuitton");
            Uri majoretteUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/majorette");
            Uri mellinUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/mellin");
            Uri merlinUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/merlin");
            Uri metesanUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/metesan");
            Uri michelinUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/michelin");
            Uri novalginUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/novalgin");
            Uri ondulineUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/ondulinelogo");
            Uri patekUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/patekphilippe");
            Uri perrierUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/perrier");
            Uri petitUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/petitbateau");
            Uri peugeotUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/peugeot");
            Uri pierreCardinUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/pierrecardin1");
            Uri pierreFabreUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/pierrefabre2");
            Uri recamicUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/recamic");
            Uri renaultUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/renault2");
            Uri richesmontUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/richesmonts");
            Uri schneiderUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/schneider2");
            Uri servierUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/servier");
            Uri societegeneraleUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/societegenerale");
            Uri squaredUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/squared");
            Uri taillefineUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/taillefine");
            Uri tebUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/teb");
            Uri tefalUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/tefal");
            Uri telemacUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/telemec");
            Uri tfalUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/tfal");
            Uri totalUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/total2");
            Uri uniroyalUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/uniroyal");
            Uri valeoUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/valeo");
            Uri vichyUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/vichy");
            Uri webermarkemUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/webermarkem");
            Uri yoplaitUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/yoplait1");
            Uri yvesUri = Uri.parse("android.resource://com.ruchanokal.boycottfrance/drawable/yvessaintlaurent");


            final ArrayList<Uri> uriArrayList = new ArrayList<>();
            uriArrayList.add(airUri);
            uriArrayList.add(allegraUri);
            uriArrayList.add(axaUri);
            uriArrayList.add(beneteaUri);
            uriArrayList.add(benzacUri);
            uriArrayList.add(benzagelUri);
            uriArrayList.add(benzamycinUri);
            uriArrayList.add(berlutiUri);
            uriArrayList.add(bicUri);
            uriArrayList.add(biothermUri);
            uriArrayList.add(bledinaUri);
            uriArrayList.add(bnpUri);
            uriArrayList.add(cacharelUri);
            uriArrayList.add(carrefourUri);
            uriArrayList.add(cartierUri);
            uriArrayList.add(chanelUri);
            uriArrayList.add(christianDiorUri);
            uriArrayList.add(chyrsoUri);
            uriArrayList.add(citroenUri);
            uriArrayList.add(clarinsUri);
            uriArrayList.add(daciaUri);
            uriArrayList.add(danielUri);
            uriArrayList.add(danoneUri);
            uriArrayList.add(decathlonUri);
            uriArrayList.add(derhyUri);
            uriArrayList.add(dpamUri);
            uriArrayList.add(drakkarnoirUri);
            uriArrayList.add(duvalUri);
            uriArrayList.add(elfUri);
            uriArrayList.add(equitableUri);
            uriArrayList.add(evianUri);
            uriArrayList.add(fahrenheitUri);
            uriArrayList.add(frankUri);
            uriArrayList.add(fredjoallierUri);
            uriArrayList.add(gerardUri);
            uriArrayList.add(gimaUri);
            uriArrayList.add(givenchyUri);
            uriArrayList.add(groupamaUri);
            uriArrayList.add(guerbetUri);
            uriArrayList.add(guerlainUri);
            uriArrayList.add(hermesUri);
            uriArrayList.add(kerastaseUri);
            uriArrayList.add(lacosteUri);
            uriArrayList.add(lafargeUri);
            uriArrayList.add(lancelUri);
            uriArrayList.add(lancomeUri);
            uriArrayList.add(larochePosayUri);
            uriArrayList.add(lavacheUri);
            uriArrayList.add(lecoqUri);
            uriArrayList.add(legrandUri);
            uriArrayList.add(longchampUri);
            uriArrayList.add(lorealUri);
            uriArrayList.add(louisVuittonUri);
            uriArrayList.add(majoretteUri);
            uriArrayList.add(mellinUri);
            uriArrayList.add(merlinUri);
            uriArrayList.add(metesanUri);
            uriArrayList.add(michelinUri);
            uriArrayList.add(novalginUri);
            uriArrayList.add(ondulineUri);
            uriArrayList.add(patekUri);
            uriArrayList.add(perrierUri);
            uriArrayList.add(petitUri);
            uriArrayList.add(peugeotUri);
            uriArrayList.add(pierreCardinUri);
            uriArrayList.add(pierreFabreUri);
            uriArrayList.add(recamicUri);
            uriArrayList.add(renaultUri);
            uriArrayList.add(richesmontUri);
            uriArrayList.add(schneiderUri);
            uriArrayList.add(servierUri);
            uriArrayList.add(societegeneraleUri);
            uriArrayList.add(squaredUri);
            uriArrayList.add(taillefineUri);
            uriArrayList.add(tebUri);
            uriArrayList.add(tefalUri);
            uriArrayList.add(telemacUri);
            uriArrayList.add(tfalUri);
            uriArrayList.add(totalUri);
            uriArrayList.add(uniroyalUri);
            uriArrayList.add(valeoUri);
            uriArrayList.add(vichyUri);
            uriArrayList.add(webermarkemUri);
            uriArrayList.add(yoplaitUri);
            uriArrayList.add(yvesUri);




          StorageReference imageRef = storageReference.child("Images");

            for (int n = 0; n < uriArrayList.size(); n++) {

                m = String.valueOf(n);

                try {
                    InputStream stream = getContentResolver().openInputStream(uriArrayList.get(n));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                final StorageReference imageName = imageRef.child(m);

                imageName.putFile(uriArrayList.get(n)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(MainActivity.this, "Uploaded!!", Toast.LENGTH_SHORT).show();

                        imageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String downloadUrl = uri.toString();
                                StoreLink(downloadUrl);

                                //System.out.println(imageName);
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                }
        }
    }


    int z=0;
    private void StoreLink(String downloadUrl) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("downloadurl", downloadUrl);



        DatabaseReference newRef = firebaseDatabase.getReference().child("Images").child(String.valueOf(z));

        newRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Sonunda bee!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        z++;

    }

    */


    private void showList() {


        groupList = new ArrayList<>();

        productList = new HashMap<>();
        detailList = new HashMap<>();
        imageList = new HashMap<>();

        groupList.add("Mutfak e??yalar??");
        groupList.add("Sigorta");
        groupList.add("??ampuan / Sa?? Bak??m");
        groupList.add("Giyim / Aksesuar");
        groupList.add("S??permarket");
        groupList.add("S??t ??r??nleri");
        groupList.add("??i??e Suyu");
        groupList.add("Otomobil / Yedek Par??a");
        groupList.add("Bebek Giyim / Mama / Oyuncak");
        groupList.add("Kozmetik");
        groupList.add("Parf??m");
        groupList.add("Cilt Bak??m ??r??nleri");
        groupList.add("??n??aat");
        groupList.add("Seyahat");
        groupList.add("??akmak");
        groupList.add("Spor Ekipman??");
        groupList.add("Akaryak??t");
        groupList.add("??la??");
        groupList.add("End??striyel / Elektrik ??r??nleri");
        groupList.add("Denizcilik");
        groupList.add("Finans");
        groupList.add("Hotel");
        groupList.add("Telekom??nikasyon");
        groupList.add("Bili??im");
        groupList.add("Sigara");



        List<String> mutfak = new ArrayList<>();

        mutfak.add("Tefal");
        mutfak.add("T-fal");
        mutfak.add("Moulinex");
        mutfak.add("Groupe SEB");

        List<String> mutfakBilgi = new ArrayList<>();

        mutfakBilgi.add("Tefal, d??nyada yap????maz tava tencere sekt??r??n??n kurucusudur. " +
                "1956 y??l??nda, PTFE (teflon) malzemesinin mucidi " +
                "Marc Gr??goire taraf??ndan" +
                " Fransa'da kurulmu??tur. 1968 y??l??nda " +
                "SEB ??irketi taraf??ndan sat??n al??nm????t??r. " +
                "Fransa merkezli Tefal, T??rkiye'nin de i??inde " +
                "bulundu??u 120 ??lkede faaliyet g??stermektedir.");
        mutfakBilgi.add("T-fal, Tefal markas??n??n " +
                "baz?? ??lkelerde kulland?????? isimdir.  " +
                "Amerika Birle??ik Devletleri ve Kanada???da bu ismi kullan??r.");
        mutfakBilgi.add("Moulinex, ev ??r??nleri markalar?? olan Rowenta, Calor, All-Clad, Lagostina, Krups ve Tefal ile birlikte bir Groupe SEB markas??d??r. ??irket, me??hur Mouli rendesini tasarlad?? ve ??retti. ??irket, 1932'de sebzeleri p??re haline getirmek i??in el yap??m?? bir g??da fabrikas?? olan Moulin-L??gumes'?? icat eden Jean Mantelet taraf??ndan kuruldu. Tasar??m, modern mutfak robotunun erken bir ??nc??s?? olarak kabul edilir.\n" +
                "\n" +
                "Mantelet'in i?? plan??, \"gelece??i olan bir sanayi sekt??r?? se??mek, seri ??retim kullanmak, fiyatlar?? d??????k tutmak, maa??lar??n d??????k oldu??u b??lgelerde ??retim yapmak, maliyetleri d??????rmek, tedarik??ilere olan ba????ml??l??klar?? azaltmak, ara??t??rma ve tan??t??ma odaklanmak\" idi. 1929 ile 1953 y??llar?? aras??nda Mantelet 93 patent ba??vurusunda bulundu."+"\n"+"\n"+"\n"+"\n"+"\n");
        mutfakBilgi.add("Groupe SEB, k??????k ev aletleri ??reten b??y??k bir Frans??z konsorsiyumudur. Groupe SEB ile ili??kili ??nemli marka isimleri aras??nda All-Clad, Krups, Moulinex, Rowenta, Tefal (OBH Nordica dahil) ve WMF Group bulunmaktad??r. Groupe SEB web sitesine g??re, d??????k fiyatl?? ??inli rakiplerden ??nemli bir rekabetle kar????la??t??lar, ancak sabit bir sat???? seviyesini korumay?? ba??ard??lar. ??r??n hatlar??n??n b??y??k bir k??sm?? ??imdi ??in'de ??retilmektedir. Merkezi Lyon banliy??s?? Ecully'dedir.\n" +
                "\n" +
                "Groupe SEB konsorsiyumunun ??nc??s?? 1857'de Antoine Lescure taraf??ndan kuruldu. 1977'de, birinci nesil ev video oyun konsolu Telescore 750'yi piyasaya s??rd??ler. Daha sonra Telescore 751 ve Telescore 752'yi piyasaya s??rd??ler."+"\n"+"\n"+"\n"+"\n");



        List<String> sigorta = new ArrayList<>();

        sigorta.add("Axa Sigorta");
        sigorta.add("Groupama International");
        sigorta.add("Equitable Life");

        List<String> sigortaBilgi = new ArrayList<>();

        sigortaBilgi.add("AXA Sigorta S.A., uluslararas?? Frans??z yat??r??m, " +
                "emeklilik ve sigorta grubu. Merkezi Paris???tedir. " +
                "AXA ba????ms??z ??al????an i??letmeler konglomerat??d??r, her " +
                "??lkenin hukuk sistemine ve d??zenlemelerine uymakla y??k??ml??d??r. " +
                "Grup ??zellikle Bat?? Avrupa, Kuzey Amerika, Asya-Pasifik " +
                "ve Ortado??u b??lgelerinde ??al??????r.");
        sigortaBilgi.add("Groupama, Frans??z sigorta ve emeklilik ??irketi.\n" +
                "T??rkiye pazar??na Ba??ak Sigorta, Ba??ak Emeklilik, G??ven Sigorta ve G??ven Hayat" +
                " ??irketlerini sat??n alarak girmi??tir.  " +
                "Fransa???n??n en b??y??k m??t??el sigorta ??irketi olan Groupama, " +
                "tar??m ve sa??l??k sigortalar??ndan evde bak??m sigortalar??na uzanan " +
                "pek ??ok alanda lider konumdad??r. Groupama; Fransa d??????nda, " +
                "??talya, Portekiz, Slovakya, T??rkiye, Yunanistan, Macaristan, " +
                "Romanya, Bulgaristan, ??in, Vietnam ve Tunus olmak ??zere 11 ??lkede " +
                "faaliyet g??stermektedir. Groupama???n??n 13 milyon m????terisi " +
                "ve 34.000 ??al????an?? bulunmaktad??r.\n"+"\n"+"\n"+"\n");

        sigortaBilgi.add("Equitable Holdings (eski ad??yla The Equitable Life Assurance Society " +
                "of the United States ve AXA Equitable Life Insurance Company " +
                "ve ayn?? zamanda The Equitable olarak da bilinir) 1859'da Henry " +
                "Baldwin Hyde taraf??ndan kuruldu. 1991 y??l??nda, bir Frans??z sigorta" +
                " ??irketi olan Axa, ??irketin ??o??unluk kontrol??n?? sat??n ald??. 2004 " +
                "y??l??nda ismini resmi olarak AXA Equitable Life Insurance Company " +
                "olarak de??i??tirdi. 2018 itibariyle, ??irketin Kaliforniya Eyaleti" +
                " taraf??ndan lisanslanm???? 15.800'den fazla temsilcisi vard??. Ocak " +
                "2020'de, AXA'dan yan ??r??n?? ve May??s 2018'de ba??layan ilgili halka" +
                " arzlar??n ard??ndan ad??n?? Equitable olarak de??i??tirdi."+"\n"+"\n"+"\n"+"\n");




        List<String> sampuan = new ArrayList<>();

        sampuan.add("L'Oreal");
        sampuan.add("Lancome");
        sampuan.add("Kerastase");

        List<String> sampuanBilgi = new ArrayList<>();

        sampuanBilgi.add("L'Or??al Group, D??nya'n??n en b??y??k kozmetik ve g??zellik ??irketidir. " +
                "Fransa'da Paris banliy??s??nde Paris'e kay??tl?? ofisi ve merkez ofisi ile Clichy, " +
                "Hauts-de-Seine'de bu kozmetik alan??nda faaliyetlerini geli??tirmekte olup; " +
                "sa?? rengi, cilt bak??m??, g??ne??ten korunma, makyaj, parf??m ve sa?? bak??m?? ??zerine" +
                " konsantre ve ila?? alanlar??nda aktif ve ABD'de ??st nanoteknoloji'ye en ??ok " +
                "yat??r??m yapan ve bu konuda en ??ok patenti elinde bulunduran bir ??irkettir." +
                " Kurucusu 1907 y??l??nda \"halesi\" ad??nda bir sa?? boyas?? form??l?? geli??tiren " +
                "Eug??ne Schueller ad??nda gen?? bir Frans??z kimyagerdi.\n" +
                "L'Or??al; Clarisonic, Drakkar Noir, K??rastase, L'Or??al Professionnel, " +
                "Lanc??me, Magic Shave, NYX Cosmetics, Pureology, Redken, Shu Uemura, " +
                "Urban Decay, Kiehl's, Maybelline, Vichy, Garnier, Helena Rubinstein, " +
                "Ralph Lauren, Giorgio Armani Parfums, Biotherm, Cacharel, CeraVe, " +
                "SkinCeuticals, La Roche-Posay gibi markalar?? da b??nyesinde bulunur.\n"+"\n"+"\n"+"\n");
        sampuanBilgi.add("Lanc??me, ??r??nleri uluslararas?? olarak da????tan bir Frans??z" +
                " l??ks parf??m ve kozmetik evidir. Lanc??me, y??ksek fiyatlarla " +
                "l??ks cilt bak??m??, parf??mler ve makyaj sunan L???Or??al L??ks " +
                "??r??nler B??l??m??'n??n bir par??as??d??r.");
        sampuanBilgi.add("K??rastase , ??r??nleri uluslararas?? olarak da????tan" +
                " bir Frans??z l??ks sa?? bak??m serisidir. K??rastase, " +
                "L???Or??al Profesyonel ??r??nler B??l??m??'n??n bir par??as??d??r.");



        List<String> giyim = new ArrayList<>();

        giyim.add("Lacoste");
        giyim.add("Givenchy");
        giyim.add("Yves Saint Laurent");
        giyim.add("Ren?? Derhy");
        giyim.add("Daniel Hechter");
        giyim.add("Pierre Cardin");
        giyim.add("Louis Vuitton");
        giyim.add("Fred Joaillier");
        giyim.add("Berluti");
        giyim.add("Cacharel");
        giyim.add("Hermes");
        giyim.add("Longchamp");
        giyim.add("Lancel");
        giyim.add("Chlo??");

        List<String> giyimBilgi = new ArrayList<>();

        giyimBilgi.add("Lacoste, Fransa'n??n en ??nemli giyim ve aksesuar markalar??ndan biridir. " +
                "1933 y??l??nda Ren?? Lacoste taraf??ndan kurulmu??tur. O zamanlar??n " +
                "en ??nl?? tenis??ilerinden olan Lacoste, Davis Cup tenis turnuvas??n??" +
                " kazanan ve Amerikal?? olmayan ilk tenis??idir.\n" +
                "\n" +
                "Tenis ma??lar??nda kullan??lan giysilere se??enek olarak, " +
                "rahat, terlemeyi ??nleyen, hareket yetene??ini art??ran bir " +
                "tasar??m yaparak bug??nk?? Lacoste Polo Ti????rt?? yaratm????t??r. " +
                "Takma ad?? olan timsah?? ise simge olarak bu t-shirt'??n sol " +
                "taraf??na yerle??tiren Lacoste, bu ??ekilde d??nyada ilk defa " +
                "bir giyim ??r??n??n??n g??r??nen y??zeyinde simge kullanan marka " +
                "olmu??tur. 1933'ten beri kal??b?? de??i??meyen tek ??r??n?? olan " +
                "Lacoste Polo markan??n ikonik ??r??n??d??r.\n"+"\n"+"\n"+"\n");
        giyimBilgi.add("Givenchy, 1952 y??l??nda Hubert de Givenchy " +
                "taraf??ndan kurulan; giyim, aksesuar, parf??m " +
                "ve kozmetik ??r??nleri markas??d??r.\n" +
                "\n" +
                "Givenchy, 1988 y??l??nda d??nyan??n " +
                "en b??y??k l??ks t??ketim mallar?? ??reten " +
                "gruplar??ndan Fransa merkezli LVMH " +
                "taraf??ndan sat??n al??nd??.");
        giyimBilgi.add("Yves Saint Laurent  veya YSL, 1962 y??l??nda tasar??mc?? Yves " +
                "Saint Laurent ve orta???? Pierre Berg?? taraf??ndan kurulmu?? l??ks " +
                "moda evidir. Bug??n smokin diye tan??mlanan ???Le Smoking??? k??yafeti" +
                ",  keskin diki?? hatl??, tamam?? siyah tak??m elbise, " +
                "1966 y??l??nda Saint Laurent???in modadaki etkisini" +
                " en iyi tan??mlayan imza stili haline geldi. " +
                "Bu g??nlerde bir??ok ??nl??n??n giyim tercihidir.");
        giyimBilgi.add("Ren?? Derhy bazen Derhy olarak k??salt??l??r, " +
                "1969'da Ren?? ve Yvette Derhy taraf??ndan " +
                "kurulan bir Frans??z haz??r giyim markas??d??r.");
        giyimBilgi.add("Daniel Hechter, d??nya ??ap??nda 45 lisans sahibi olan bir Frans??z " +
                "moda ve ya??am tarz?? markas??d??r. Erkek ve kad??n giyim, " +
                "aksesuar ve t??ketim mallar?? satmaktad??r. 1962'de Frans??z " +
                "modac?? Daniel Hechter, ilk kad??n koleksiyonunu yay??nlad??. " +
                "???? y??l sonra, bir ??ocuk serisi ekledi ve 1968'de erkek serisiyle" +
                " moda serisini tamamlad??. Bu koleksiyonlar, haz??r giyim " +
                "veya Pr??t-??-porter modas??n??n mucidi olarak onu pop??ler " +
                "hale getirdi. Birka?? y??l sonra bu koleksiyonlar spor ve " +
                "g??nl??k giyim olarak geni??letildi. Bunu g??zl??k, parf??m, " +
                "kalem ve t??ketim mallar?? izledi, ard??ndan saat ve deri " +
                "??r??nler di??erleri aras??na kat??ld??. Daniel Hechter Paris'in" +
                " en dikkat ??eken ??r??nleri ayakkab??lar??d??r. " +
                "Aksesuarlar aras??nda en b??y??k ciroyu olu??turur."+"\n"+"\n"+"\n"+"\n");
        giyimBilgi.add("1950 y??l??nda Fransa???da Pierre Cardin taraf??ndan " +
                "temelleri at??lan marka, ilkleri ve s??ra d?????? " +
                "tasar??mlar?? ile d??nya modas??na y??n vererek en " +
                "bilinen giyim markalar??ndan biri olmu??tur. " +
                "??zellikle uzay ara??t??rmalar??n??n yo??unla??t?????? " +
                "60???l?? y??llarda d??nemin co??kusunu koleksiyonlar??na" +
                " ???Space Age??? temas??yla yans??tan Pierre Cardin, " +
                "modada avant-garde ve f??t??ristik ak??m??n ilk " +
                "temsilcilerindendir. ");
        giyimBilgi.add("Louis Vuitton markas??, kurucusu Louis Vuitton Malletier taraf??ndan 1854 y??l??nda kuruldu.\n" +
                "Kad??nlar??n modayla olduk??a yak??ndan ilgilenmeye ba??lad?????? ve k??yafetlerin ??ok hacimli olmas?? ve geni?? yer kaplamas?? sebebiyle b??y??k sand??klar??n g??zde oldu??u d??nemde ??retime sand??kla ba??layan Louis Vuitton, bug??n d??nyada haz??r giyim, ayakkab??, ??anta, saat, tak??-m??cevher ve aksesuar ??retimiyle l??ks t??ketimin ??nc?? temsilcilerindendir.\n");
        giyimBilgi.add("Fred Joaillier, kurucusu Fred Samuel'in ad??n?? ta????yan bir Frans??z m??cevher markas??d??r. ??lk ma??azas?? 1936'da Paris'te a????ld?? ve Jean Cocteau taraf??ndan tasarlanan m??cevherler ve Marl??ne Dietrich veya Grace Kelly gibi ??nl?? m????terileri ile ??nlendi.");
        giyimBilgi.add("Berluti, ayakkab?? ve bot ??retiminde ??zellikle dana derisi, kanguru derisi ve timsah derisinin kaplamalar?? olmak ??zere erkek giyim ??reten bir LVMH yan kurulu??udur. Deri kemer, ??anta ve c??zdanlar??n yan?? s??ra ??smarlama ve haz??r giyim ??r??nleri ??retir. 1895 y??l??nda Marche'l?? ??talyan Alessandro Berluti taraf??ndan kurulmu??tur.");
        giyimBilgi.add("Cacharel, 1962'de Jean Bousquet taraf??ndan kurulan Frans??z haz??r giyim, parf??m ve aksesuar markas??d??r. Cacharel tasar??mlar??, gen?? tarzlar??, hafiflikleri, incelikleri ve parlak renk kullan??mlar??yla karakterizedir. Bir L'Or??al Group ??yesidir.");
        giyimBilgi.add("Herm??s International SA veya k??saca Herm??s, 1837'de kurulmu?? bir Frans??z y??ksek moda l??ks e??ya ??reticisidir. Deri, ya??am tarz?? aksesuarlar??, ev mobilyalar??, parf??meri, m??cevherler, saatler ve haz??r giyim konusunda uzmanla??m????t??r.");
        giyimBilgi.add("Longchamp, 1948'de Jean Cassegrain taraf??ndan Paris'te kurulan Frans??z l??ks bir deri ??r??nler ??irketidir. Jean Cassegrain D??nya???n??n ilk l??ks deri kapl?? pipolar??n?? ??retti, ard??ndan c??zdanlar, pasaport k??l??flar?? vb.  k??????k deri ??r??nlere geni??ledi. Longchamp, 1971'de ilk kad??n ??antas??n?? piyasaya s??rd?? ve Fransa'n??n ??nde gelen deri e??ya ??reticilerinden biri oldu. ??irket bug??n, deri ve kanvas ??antalar, valizler, ayakkab??lar, seyahat e??yalar??, moda aksesuarlar?? ve bir dizi \"haz??r giyim\" kad??n tasar??mc?? k??yafetleri de dahil olmak ??zere ??ok ??e??itli l??ks ??r??nler tasarlar ve ??retir. Yakla????k 1.500 perakende sat???? noktas?? arac??l??????yla 80 ??lkede i?? yapmaktad??r, hala ??zel m??lkiyettedir ve Cassegrain'in kurucu ailesi taraf??ndan y??netilmektedir."+"\n"+"\n"+"\n"+"\n");
        giyimBilgi.add("Lancel, 1876'da Paris'te Ang??le ve Alphonse Lancel taraf??ndan kurulan ve o??ullar?? Albert taraf??ndan geli??tirilen bir Frans??z l??ks deri ??r??nleri ??irketidir. Lancel, el ??antalar??, c??zdanlar, ??antalar, seyahat e??yalar??, valizler, aksesuarlar gibi erkekler ve kad??nlar i??in deri ??r??nler sa??lar. Markan??n 140 y??l?? a??k??n bir s??redir l??ks deri ??r??nler ??retme gelene??i var. ??irketin merkezi halen Paris, Fransa'dad??r. ");
        giyimBilgi.add("Chlo??, Gaby Aghion taraf??ndan 1952'de kurulan bir Frans??z l??ks moda evidir. Merkezi Paris, Fransa'dad??r. Ev, l??ks markalar holding ??irketi Richemont Group'a aittir. Chlo??; Marion Cotillard, Sienna Miller, Madonna, January Jones, Maggie Gyllenhaal, Cameron Diaz, Emma Stone, Cl??mence Po??sy ve Katie Holmes gibi bir??ok ??nl?? taraf??ndan giyildi.");


        List<String> supermarket = new ArrayList<>();

        supermarket.add("Gima");
        supermarket.add("Carrefour");

        List<String>  supermarketBilgi = new ArrayList<>();

        supermarketBilgi.add("Gima, 1956 y??l??nda kurulan bir kamu iktisadi te??ekk??l?? olan Gima, T??rkiye'nin ilk ulusal s??permarket zinciridir. 1996 y??l??nda ??o??unluk hisseleri ve y??netiminin Fiba Holding sat??n ald??. 2005 y??l??nda Endi ile birlikte CarrefourSA taraf??ndan sat??n al??nd??. 10 Haziran 2007 tarihinde CarrefourSA Express ad??n?? alm????t??r. 10 y??l sonra, 2017 y??l??nda Ankara???da tekrar faaliyete ge??mi??tir. CarrefourSA ??yesidir.");
        supermarketBilgi.add("Carrefour, Fransa merkezli uluslararas?? s??permarketler zinciri. Walmart'tan sonra d??nyan??n en b??y??k cirosuna sahip ma??azac??l??k ??irketidir. A????rl??kl?? olarak Avrupa Birli??i, Brezilya ve Arjantin'de faaliyet g??stermekle birlikte Kuzey Afrika ve Asya'da da ma??azalara sahiptir. ??irket isminin Frans??zca s??zc??k anlam?? \"yol\" ya da \"d??rtyol a??z??\"d??r. ");



        List<String> sutUrun = new ArrayList<>();

        sutUrun.add("Danone");
        sutUrun.add("Yoplait");
        sutUrun.add("La Vache Qui Rit");
        sutUrun.add("Fromageries Riches Monts");
        sutUrun.add("Gerard");
        sutUrun.add("Taillefine");
        sutUrun.add("Pr??sident");
        sutUrun.add("Elle & Vire");
        sutUrun.add("Babybel");

        List<String> sutUrunBilgi = new ArrayList<>();

        sutUrunBilgi.add("Danone, s??tl?? ??r??nleri ve i??ecekleri ile tan??nan Fransa merkezli ??ok uluslu bir ??irket. Danone ??irketinin kurulu??u Osmanl?? Devleti???ne kadar uzan??r. Yahudi as??ll?? doktor ??zak Karasu'nun (??saac Carraso) ad?? ile de bilinir. Balkan sava??lar?? s??ras??nda Yunanlar Selanik'i i??gal etti??i zaman Karasu ailesi 1912 y??l??nda ??spanya'n??n Barselona kentine g???? etti. 1919'da geleneksel Osmanl?? yo??urtlar??n?? ??retip o??lu Daniel'in ismi \"Danone\" ad??yla piyasaya s??rd??. ??saac'in o??lu Daniel Carasso, babas??n??n temelini att?????? yo??urt i??ini 1929 y??l??nda Fransa???ya ta????d?? ve burada 1932 y??l??nda Levallois-Perret???de ilk fabrikay?? in??a ettirdi. II. D??nya Sava???? y??llar??nda 1942 y??l??nda ABD???ye g???? eden Daniel Carasso bir s??re bu ??lkede ??al????t?? ve ilk yo??urt fabrikas??n?? kurdu. Bir zaman sonra Danone d??nyan??n ??nde gelen g??da gruplar??ndan birine d??n????t??."+"\n"+"\n"+"\n"+"\n"+"\n");
        sutUrunBilgi.add("Yoplait D??nya???n??n en b??y??k franchise yo??urt markas??d??r. Birle??ik Devletler merkezli g??da ??irketi General Mills ve Frans??z s??t ??r??nleri kooperatifi Sodiaal'??n ortak m??lkiyetindedir. Bir??ok ??lkede, o ??lkelerin markalar?? taraf??ndan sat???? ve pazarlamas?? yap??lmakta ve y??netilmektedir.");
        sutUrunBilgi.add("La Vache Qui Rit , T??rk??e???de  g??len inek demektir. 1865'ten beri Bel Group (Fromageries Bel) taraf??ndan ??retilen i??lenmi?? peynir ??r??nlerinin bir markas??d??r ve ??zellikle markan??n en pop??ler ??r??n?? olan yay??labilen peynir anlam??na gelir. ??irket 1921'de kurulmu??tur.");
        sutUrunBilgi.add("Compagnie des Fromages et RichesMonts (CF&R), Frans??z peynirinin, daha ??zel olarak geleneksel Frans??z yumu??ak peynirinin ( camembert ve brie gibi ) ve raclette peynirinin ??retimi ve pazarlanmas??nda uzmanla??m???? bir Frans??z g??da i??leme ??irketidir.");
        sutUrunBilgi.add("G??rard ailesi 19. y??zy??l??n ilk yar??s??nda, Fransa'n??n do??usunda Vosges da??lar??nda bulunan k??????k bir k??y olan Le Tholy'de peynir ??retimine ba??lar. 1898'de Fran??ois G??RARD'??n o??lu Eug??ne G??RARD, La Fromagerie G??rard peynir fabrikas??n?? kurar. Bug??n ??nl?? bir peynir markas??d??r.");
        sutUrunBilgi.add("Taillefine , s??t ??r??nleri, tatl?? kremalar, kompostolar ve az ya??l?? i??ecekler ??reten, 1964 y??l??nda kurulan bir Danone Group markas??d??r.");
        sutUrunBilgi.add("Pr??sident Laval, Mayenne merkezli Lactalis ??irketine ait bir Frans??z s??t ??r??nleri markas??d??r. Marka 1968 y??l??nda Andr?? Besnier taraf??ndan kuruldu. Tereya???? ve geleneksel peynirlerin end??striyel olarak ??retilen ??e??itleri i??in kullan??l??r.");
        sutUrunBilgi.add("Elle & Vire, tar??msal g??da grubu Savencia Fromage & Dairy'ye ait end??striyel i??lenmi?? s??t ??r??nlerinin ticari markas??d??r. Bu grubun Frans??z yan kurulu??u, Cond??-sur-Vire (Manche) merkezli \"Soci??t?? Coop??rative Agricole Elle et Vire\", C??ur de Lion ve Elle-et-Vire fabrikalar?? i??in s??????r yeti??tiricilerinden s??t sat??n al??r ve toplar.");
        sutUrunBilgi.add("Babybel, tek tek paketlenmi?? ve ??e??itli tatlarda mevcut olan k??????k at????t??rmal??k peynir ??r??nleri markas??d??r. Fransa'n??n Jura b??lgesinde k??kleri olan Le Groupe Bel'in (The Bel Group) bir ??r??n??d??r ve 1865 y??l??nda Jules Bel taraf??ndan ba??lat??lm????t??r.");


        List<String> siseSuyu = new ArrayList<>();

        siseSuyu.add("Perrier");
        siseSuyu.add("Evian");
        siseSuyu.add("Danone");

        List<String> siseSuyuBilgi = new ArrayList<>();

        siseSuyuBilgi.add("Perrier, Gard d??partement'ta bulunan Verg??ze kayna????nda yakalanan bir Frans??z do??al ??i??e maden suyu markas??d??r. ??svi??re ??irketi Nestl??'nin Nestl?? Waters b??l??m??n??n bir par??as??d??r. Perrier, do??al olarak olu??an karbonasyonu, kendine ??zg?? ye??il ??i??esi ve akranlar??ndan daha y??ksek karbonasyon seviyeleri ile bilinir.");
        siseSuyuBilgi.add("??vian, Cenevre G??l??'n??n g??ney k??y??s??nda, ??vian-les-Bains yak??nlar??ndaki ??e??itli kaynaklardan gelen bir ??i??elenmi?? su markas??d??r. G??n??m??zde ??vian, Frans??z ??okuluslu ??irket Danone'ye aittir. Fransa, Amerika Birle??ik Devletleri, Bel??ika, ??svi??re ve Rusya gibi ??lkelerin yan?? s??ra T??rkiye???de        Migros taraf??ndan sat??lmaktad??r.");
        siseSuyuBilgi.add("Fransa merkezli ??ok uluslu bir s??tl?? ??r??nler ve i??ecekler ??reten bir ??irket olan Danone, Yahudi as??ll?? doktor ??saac Carraso taraf??ndan kurulmu??tur.");



        List<String> otomobil = new ArrayList<>();

        otomobil.add("ValeoOto");
        otomobil.add("Peugeot");
        otomobil.add("Renault");
        otomobil.add("Citro??n");
        otomobil.add("Michelin");
        otomobil.add("Uniroyal");
        otomobil.add("Recamic");
        otomobil.add("Dacia");

        List<String>  otomobilBilgi = new ArrayList<>();

        otomobilBilgi.add("Valeo, 1923 y??l??nda Saint Ouen???de (Fransa) kurulmu??tur. Valeo, merkezi Fransa'da bulunan ve Paris Borsas??'nda ( CAC-40 Endeksi) listelenen bir Frans??z k??resel otomotiv tedarik??isidir. Otomobil ??reticilerine ve sat???? sonras?? pazara ??r??n yelpazesi sunar. Grup, d??nya ??ap??nda 33 ??lkede 113.600 ki??iyi istihdam etmektedir. 186 ??retim tesisi, 59 Ar-Ge merkezi ve 15 da????t??m platformuna sahiptir. ");
        otomobilBilgi.add("Peugeot, Frans??z otomobil, bisiklet ve motosiklet markas??, g??n??m??zde PSA Peugeot Citro??n`in bir par??as??d??r. 1810 y??l??nda el aletleri ile ??retime ba??lam????t??r, 1890 y??l??ndan bu yana da otomobil ??reticisidir. T??rkiye'deki ticari faaliyetlerini Peugeot Otomotiv Pazarlama A.??. ??irketi ??zerinden y??r??tmektedir.");
        otomobilBilgi.add("Renault S.A., Frans??z ara?? ??reticisi. Otomobil, kamyon, trakt??r, tank, tren, u??ak, motosiklet, bisiklet, otob??s gibi bir??ok farkl?? t??rde ara?? ??retmektedir. T??rkiye'de Bursa'da kurulu bulunan Oyak-Renault ortakl?????? (%51) ile yat??r??m?? vard??r. Ayr??ca Nissan otomobil markas??n??n motorlar??n?? ??retmektedir. ");
        otomobilBilgi.add("Citro??n, Frans??z ana otomobil ??reticisi olan, PSA Peugeot Citro??n grubunun 1976'den beri ??yesi bir otomobil ??reticisidir.");
        otomobilBilgi.add("Michelin (tam ad?? Frans??zca: SCA Compagnie G??n??rale des ??tablissements Michelin) Fransa'n??n Auvergne b??lgesinde bulunan Clermont-Ferrand ??ehrinde merkezi bulunan ve as??l olarak ara?? lasti??i ??reten bir ??irkettir. 28 May??s 1888 y??l??nda ??douard ve Andr?? Michelin karde??ler taraf??ndan kurulmu??tur. Michelin markas?? d??????nda B.F.Goodrich, Taurus, Kormoran ve Uniroyal (Kuzey Amerika'da) markalar??na da sahiptir.");
        otomobilBilgi.add("A????l??m?? ??ngilizce???de ???The United States Rubber Company???  olan Uniroyal, Amerikal?? bir lastik ve sentetik kau??ukla ilgili ??r??nler ??reticisidir. Askeri kullan??m i??in ??e??itli ??r??nler ??retmenin yan??nda devlete ait y??klenici taraf??ndan i??letilen tesislerde m??himmat, patlay??c??lar ve operasyon ve bak??m faaliyetleri sahalar??nda ??r??nler ??retmektedir. 1892'de Connecticut, Naugatuck'ta kuruldu. 1990 y??l??nda Uniroyal, Frans??z lastik ??reticisi Michelin taraf??ndan sat??n al??nd?? ve ayr?? bir i??letme olarak varl??????n?? sona erdirdi.");
        otomobilBilgi.add("Recamic, Michelin Grubunun kaplama markas??d??r. Recamic, lastik kaplama teknolojisinin g??venli??ini ve performans??n?? geli??tirir.");
        otomobilBilgi.add("Dacia, 1966 y??l??nda Romanya h??k??metinin a??m???? oldu??u ihale sonucu kurulmu??, ad??n?? Romanya topraklar??n??n eski ad?? olan Da??ya???dan alan, 1999 y??l??nda Renault b??nyesine ge??en Romanyal?? otomobil ??reticisi. Ayr??ca Renault'nun Romanya'daki markas??d??r.");



        List<String> bebekGiyim = new ArrayList<>();

        bebekGiyim.add("Bledina");
        bebekGiyim.add("Majorette");
        bebekGiyim.add("DPAM");
        bebekGiyim.add("Petit Bateau");

        List<String>  bebekGiyimBilgi = new ArrayList<>();

        bebekGiyimBilgi.add("Bl??dina, Danone grubunun bir ??irketi olup, bir bebek mamas?? markas??d??r. ");
        bebekGiyimBilgi.add("Majorette , ??zellikle 1:64 ??l??ekli olmak ??zere ??o??unlukla k??????k pres d??k??m arabalar ve di??er in??aat ve askeri ara??lar ??reten Frans??z bir oyuncak ??reticisidir . Bu normal bir 2,5 ila 3 in?? boyutudur, bu nedenle Majorette bazen Fransa'n??n Kibrit Kutusu Oyuncaklar?? olarak adland??r??l??r. Geleneksel olarak, ??retim Lyon'un kentsel b??lgesinde merkezlenmi??ti, ancak modeller art??k Tayland'da yap??l??yor.");
        bebekGiyimBilgi.add("Du Pareil au M??me s??k kullan??lan ad??yla DPAM, 1986 y??l??nda Simon Benharrous taraf??ndan kurulmu?? Frans??z bir ??ocuk-giyim ma??aza zinciridir. 0-14 ya?? aral??????ndaki ??ocuklar?? kapsayan ayakkab??, giysi, aksesuar vb. ??r??nler satmaktad??r. ");
        bebekGiyimBilgi.add("Petit Bateau, 1920'de kurulan, ancak k??keni Troyes'de 1893'e kadar uzanan ??ocuklar i??in bir Frans??z giyim markas??d??r. 1988'den beri Rocher Grubu'na entegre olan Petit Bateau, 2000'li y??llarda yeti??kin koleksiyonunun lansman?? sayesinde nesiller aras?? hale gelen bir ??ocuk markas??d??r.");



        List<String> kozmetik = new ArrayList<>();

        kozmetik.add("L'Oreal");
        kozmetik.add("La Roche Posay");
        kozmetik.add("Biotherm");
        kozmetik.add("Christian Dior");
        kozmetik.add("Clarins");
        kozmetik.add("Vichy");
        kozmetik.add("Garnier");

        List<String> kozmetikBilgi = new ArrayList<>();

        kozmetikBilgi.add("L'Or??al Group, D??nya'n??n en b??y??k kozmetik ve g??zellik ??irketidir. Fransa'da Paris banliy??s??nde Paris'e kay??tl?? ofisi ve merkez ofisi ile Clichy, Hauts-de-Seine'de bu kozmetik alan??nda faaliyetlerini geli??tirmekte olup; sa?? rengi, cilt bak??m??, g??ne??ten korunma, makyaj, parf??m ve sa?? bak??m?? ??zerine konsantre ve ila?? alanlar??nda aktif ve ABD'de ??st nanoteknoloji'ye en ??ok yat??r??m yapan ve bu konuda en ??ok patenti elinde bulunduran bir ??irkettir. Kurucusu 1907 y??l??nda \"halesi\" ad??nda bir sa?? boyas?? form??l?? geli??tiren Eug??ne Schueller ad??nda gen?? bir Frans??z kimyagerdi. L'Or??al; Clarisonic, Drakkar Noir, K??rastase, L'Or??al Professionnel, Lanc??me, Magic Shave, NYX Cosmetics, Pureology, Redken, Shu Uemura, Urban Decay, Kiehl's, Maybelline, Vichy, Garnier, Helena Rubinstein, Ralph Lauren, Giorgio Armani Parfums, Biotherm, Cacharel, CeraVe, SkinCeuticals, La Roche-Posay gibi markalar?? da b??nyesinde bulunur."+"\n"+"\n"+"\n"+"\n"+"\n");
        kozmetikBilgi.add("La Roche Posay, ya??l?? ve akneli ciltler, hassas ve k??zar??kl????a e??ilimli ciltler, leke kar????t??, makyaj temizleme ve cilt temizleme sular??, nemlendirici, g??ne?? ??r??nleri ve kuru ciltler i??in ??retilen dermokozmetik bak??m ??r??nleri markas??d??r.\n" +
                "La Roche Posay markas?? Fransa merkezli Loreal grubu b??nyesindedir.\n");
        kozmetikBilgi.add("Biotherm, L??ks ??r??nler b??l??m?? alt??nda L'Or??al'e ait bir Frans??z cilt bak??m ??irketidir. Biotherm, 1970 y??l??nda L'Or??al taraf??ndan sat??n al??nm????t??r.");
        kozmetikBilgi.add("Christian Dior SE, (Dior olarak bilinir), Avrupa'n??n en b??y??k l??ks mallar grubu olan LVMH'ye ba??kanl??k eden Frans??z i?? adam?? Bernard Arnault taraf??ndan y??netilen bir Avrupa moda ??irketidir. Dior, LVMH b??nyesinde% 42.36 pay ve% 59.01 oy hakk??na sahiptir.\n" +
                "1948 y??l??nda tasar??mc??s?? Christian Dior taraf??ndan kurulan ??irket, bug??n tan??nm???? haute-couture (Christian Dior Couture b??l??m??n??n alt??nda) konfeksiyon, haz??r giyim, deri ??r??nleri, moda aksesuarlar??, ayakkab??, tak??, saat, parf??m, makyaj ve cilt bak??m ??r??nleri tasarlay??p satar. Christian Dior etiketi, kad??nlara y??nelik teklifleri b??y??k ??l????de korurken, erkekler i??in Dior Homme b??l??m??n?? ve ??ocuk giyiminde Dior etiketi kullan??yor. ??r??nler, d??nya ??ap??ndaki perakende ma??azalar??n??n portf??y??nde ve ??evrimi??i ma??azas??nda sat??lmaktad??r. \n"+"\n"+"\n"+"\n"+"\n");
        kozmetikBilgi.add("Clarins Group, ticari ismiyle Clarins, Frans??z l??ks cilt bak??m??, kozmetik ve parf??m ??irketidir. ??irket bu  sahada ??r??nler ??retir ve genellikle ??st d??zey al????veri?? merkezleri ve se??kin eczaneler arac??l??????yla sat????lar yapar. ");
        kozmetikBilgi.add("Vichy L'Or??al'in Aktif Kozmetik B??l??m??'n??n sahibi oldu??u cilt bak??m??, v??cut bak??m??, makyaj ve ya??lanma kar????t?? ??r??nlerden olu??an ??st??n bir markad??r. Fransa'n??n Vichy kentinin kapl??calar??ndan termal kapl??ca suyu, form??lasyonlar??nda kullan??lan tek su kayna????d??r. ??irket 1931 y??l??nda Georges Gu??rin taraf??ndan kuruldu. Marka 1950'lerde geli??meye ba??lad?? ve 1955'te L'Or??al grubu taraf??ndan sat??n al??nd??. ??irket L'Oreal grubundaki, di??eri La Roche-Posay olmak ??zere iki 'aktif kozmetik' markas??ndan biridir. Vichy, Avrupa'da en pop??ler olan??d??r ve ??irket, 'Dermablend'in temelleri ve kapat??c??lar?? i??in bilinir. Vichy, Avrupa cilt bak??m pazar??nda en b??y??k markalardan biridir. ??nde gelen hatlardan baz??lar?? ??unlard??r: LiftActiv (Anti-Aging), Neovadiol, Normaderm, Dercos, Aqualia Thermal, Capital Soleil, Essentielles (daha ucuz ??r??nlerin temel hatt??), Vichy Homme (erkek t??ra?? ve temizleme ??r??nleri). Vichy'nin slogan?? \"Sa??l??k g??zeldir\" ??eklindedir."+"\n"+"\n"+"\n"+"\n");
        kozmetikBilgi.add("Garnier Frans??z kozmetik ??irketi L'Or??al'??n bir kozmetik markas??d??r. Sa?? bak??m?? ve cilt bak??m ??r??nleri ??retir.");



        List<String> parfum = new ArrayList<>();

        parfum.add("Chanel");
        parfum.add("Christian Dior");
        parfum.add("Clarins");
        parfum.add("Drakkar Noir");
        parfum.add("Fahrenheit");
        parfum.add("Lancome");

        List<String> parfumBilgi = new ArrayList<>();

        parfumBilgi.add("Chanel 1909 y??l??nda moda tasar??mc??s?? Coco Chanel olarak bilinen ??nl?? Frans??z modac?? Gabrielle Bonheur taraf??ndan kurulan haz??r giyim, parf??m, saat, ??anta ve kozmetik gibi ??r??nler ??reten bir markad??r. Y??llar i??inde haute couture(??zel dikim giysi), parf??m, saat, haz??r giyim, ??anta ve kozmetik gibi alanlarda uzmanla??m???? ve d??nyaca ??nlenmi?? l??ks bir markaya d??n????m????t??r.");
        parfumBilgi.add("Christion Dior (Dior olarak bilinir), Avrupa'n??n en b??y??k l??ks mallar grubu olan LVMH'ye ba??kanl??k eden Frans??z i?? adam?? Bernard Arnault taraf??ndan y??netilen bir Avrupa moda ??irketidir. Dior, LVMH b??nyesinde% 42.36 pay ve% 59.01 oy hakk??na sahiptir.\n" +
                "1948 y??l??nda tasar??mc??s?? Christian Dior taraf??ndan kurulan ??irket, bug??n tan??nm???? haute-couture (Christian Dior Couture b??l??m??n??n alt??nda) konfeksiyon haz??r giyim, deri ??r??nleri, moda aksesuarlar??, ayakkab??, tak??, saatler, parf??m, makyaj ve cilt bak??m ??r??nleri tasarlay??p sat??yor. Christian Dior etiketi, kad??nlara y??nelik teklifleri b??y??k ??l????de korurken, erkekler i??in Dior Homme b??l??m??n?? ve ??ocuk giyiminde Dior etiketi kullan??yor. ??r??nler, d??nya ??ap??ndaki perakende ma??azalar??n??n portf??y??nde ve ??evrimi??i ma??azas??nda sat??lmaktad??r.\n"+"\n"+"\n"+"\n"+"\n");
        parfumBilgi.add("Clarins Group, ticari ismiyle Clarins, Frans??z l??ks cilt bak??m??, kozmetik ve parf??m ??irketidir. ??irket bu  sahada ??r??nler ??retir ve genellikle ??st d??zey al????veri?? merkezleri ve se??kin eczaneler arac??l??????yla sat????lar yapar. ");
        parfumBilgi.add("Drakkar Noir, 1982 y??l??nda Guy Laroche taraf??ndan olu??turulan parf??m markas?? L???Or??al Group lisans?? alt??nda ??retilmektedir.");
        parfumBilgi.add("Fahrenheit, 1988 y??l??ndan itibaren Christian Dior SE taraf??ndan ??retilen erkek parf??m markas??d??r. Markan??n ayr??ca deodorant, tra?? losyonu gibi bir dizi ??r??ne sahiptir.");
        parfumBilgi.add("Lanc??me, ??r??nleri uluslararas?? olarak da????tan bir Frans??z l??ks parf??m ve kozmetik evidir. Lanc??me, y??ksek fiyatlarla l??ks cilt bak??m??, parf??mler ve makyaj sunan L???Or??al L??ks ??r??nler B??l??m??'n??n bir par??as??d??r.");



        List<String> ciltBakim = new ArrayList<>();

        ciltBakim.add("Clarins");
        ciltBakim.add("Guerlain");

        List<String> ciltBakimBilgi = new ArrayList<>();

        ciltBakimBilgi.add("Clarins Group, ticari ismiyle Clarins, Frans??z l??ks cilt bak??m??, kozmetik ve parf??m ??irketidir. ??irket bu  sahada ??r??nler ??retir ve genellikle ??st d??zey al????veri?? merkezleri ve se??kin eczaneler arac??l??????yla sat????lar yapar. ");
        ciltBakimBilgi.add("Guerlain, D??nya???n??n en eski parf??m, kozmetik ve cilt bak??m?? ??irketlerinden birisidir. ??irket, parf??mc?? Pierre-Fran??ois Pascal Guerlain taraf??ndan 1828'de Paris'te kuruldu. Frans??z ??okuluslu ??irket LVMH taraf??ndan sat??n al??nd?????? 1994 y??l??na kadar Guerlain ailesi taraf??ndan i??letildi. Ana ma??azas?? Paris'teki 68, Avenue des Champs-Elys??es'dir.");



        List<String> insaat = new ArrayList<>();

        insaat.add("Onduline Avrasya");
        insaat.add("Lafarge");
        insaat.add("Chryso");
        insaat.add("Weber Markem");

        List<String> insaatBilgi = new ArrayList<>();

        insaatBilgi.add("1944'te kurulan Onduline Group, hafif ??at?? kaplama ve kiremit alt?? levhalar??nda D??nya???n??n ilk ??reticisidir. Sel??lo-mineral esasl?? ??at?? ve cephe kaplama levhalar?? ??retiminde D??nya???da tan??nan Onduline Group, T??rkiye???de 1994 y??l??na kadar Onduline lisans?? alt??nda ??retim yaparken, ayn?? y??l do??rudan yat??r??m yapma karar?? alm???? ve Onduline Avrasya ??irketi A??ustos 1994???te %100 yabanc?? sermayeli bir ??irket olarak Onduline Group???un T??rkiye???deki i??tiraki olarak kurulmu??tur.");
        insaatBilgi.add("Lafarge, merkezi Fransa'n??n ba??kenti Paris'te bulunan ve in??aat malzemeleri ??reten ??irket. ??imento, agrega ve beton, ??at?? malzemeleri, al???? ve al???? levha olmak ??zere d??rt ana faaliyet alan?? bulunmaktad??r. Kurulu??u 1833'e kadar uzanan Lafarge Grubu'nun bug??n 68???i a??k??n ??lkede fabrikas?? bulunmaktad??r.");
        insaatBilgi.add("Chryso, 1942 y??l??nda kurulan Chryso SAS b??nyesinde ??retilen ??imento katk?? maddeleri markas??d??r. Haz??r beton, ??imento, beton kimyasallar?? ??retmektedir. 22 yan kurulu??, da????t??c?? ve lisans a???? arac??l??????yla 100 ??lkede faaliyet g??stermektedir. T??rkiye ile birlikte 14 ??lkede do??rudan ??retim tesislerine sahiptir. T??rkiye???de 1994 y??l??ndan beri Chryso-Kat Katk?? Malzemeleri San. ve Tic. A.??. olarak faaliyet g??stermektedir. Chryso SAS Fransa merkezlidir.");
        insaatBilgi.add("Weber Markem, 1665 y??l??nda kurulan Saint-Gobain b??nyesinde bulunan end??striyel har??lar, al????, izolasyon, boru, d???? cephe ??r??nleri, d??z cam, y??ksek performans ??r??nleri, a????nd??r??c??lar, plastik ve cam elyaf gibi ??r??nler ??reten bir Frans??z markas??d??r. Weber, 1927 y??l??ndan beri Fransa merkezli Saint-Gobain Grubu???nun yap?? ??r??nleri i?? kolunda faaliyet g??stermektedir. Weber, T??rkiye???de 1998 y??l??ndan beri faaliyet g??steriyor. Seramik yap????t??r??cs?? ve derz dolgu malzemesi, d???? cephe ve ??s?? yal??t??m??, su yal??t??m?? ve teknik har??lar, zemin sistemi ve i?? cephe boyas?? ??r??nleriyle, T??rkiye???de 6 fabrika, 8 b??lge m??d??rl?????? ve yayg??n bir bayi a???? bulunmaktad??r."+"\n"+"\n"+"\n"+"\n");




        List<String> seyahat = new ArrayList<>();

        seyahat.add("Air France");


        List<String> seyahatBilgi = new ArrayList<>();

        seyahatBilgi.add("Soci??t?? Air France, S.A. ya da k??saca Air France Fransa'n??n bayrak ta????y??c?? havayolu ??irketi. Merkezi Paris'in kuzeyinde Tremblay-en-France'da bulunan ??irket, Air France-KLM ortakl?????? alt??nda ve SkyTeam kurucu ??yesi olarak faaliyet g??stermektedir. ");




        List<String> cakmak = new ArrayList<>();

        cakmak.add("BIC");
        cakmak.add("Cartier");

        List<String> cakmakBilgi = new ArrayList<>();

        cakmakBilgi.add("Soci??t?? Bic Clichy (Hauts-de-Seine), Fransa merkezli ??irkettir. 1945 y??l??nda kurulmu??tur. ??akmak, m??knat??s, t??kenmez kalem, t??ra?? b????a????, jilet ve su sporlar?? gere??leri gibi uzun ??m??rl?? kullan??lmayan ??r??nler ??retmesiyle tan??nmaktad??r. ??irketin ilk ??r??n?? Bic Cristal'd??r.");
        cakmakBilgi.add("Cartier International SNC, ya da daha k??saca Cartier, l??ks m??cevher, saat, ??akmak, parf??m gibi ??r??nler ??reten, tasarlayan ve sat??????n?? yapan bir Frans??z ??irketidir. ");



        List<String> spor = new ArrayList<>();

        spor.add("Le coq sportif");
        spor.add("Decathlon");

        List<String> sporBilgi = new ArrayList<>();

        sporBilgi.add("Le Coq Sportif; ayakkab??, ??ort ve ti????rt ba??ta olmak ??zere ??e??itli spor malzemeleri ??reten Frans??z ??irket. 1948'de ??mile Camuset taraf??ndan kuruldu.[1] Kurucu Camuset, halen tasar??mlar??na devam etmektedir.[2] ??irketin ismi ve logosu, Fransa'n??n ulusal sembollerinden biri olan Gal horozundan gelmektedir. Le Coq Sportif, Sheffield United, Wolverhampton Wanderers, Carlisle United ve Hibernian gibi baz?? futbol kul??pleriyle sponsorluk anla??malar??na sahiptir. Ek olarak Quick Step-Innergetic ve Team Milram bisiklet tak??mlar??n??n da sponsorlu??unu yapmaktad??r.");
        sporBilgi.add("Decathlon, 1976 y??l??nda Fransa'da kurulan, bir??ok farkl?? markan??n spor ekipmanlar??n??n bir arada bulundu??u uluslararas?? ma??aza zinciridir. 2015 y??l?? verilerine g??re d??nya genelinde 70.000 ??al????an?? bulunmaktad??r. T??rkiye???de bug??n 38 ma??azas?? bulunmaktad??r. ");




        List<String> akaryakit = new ArrayList<>();

        akaryakit.add("Total");
        akaryakit.add("Elf");

        List<String> akaryakitBilgi = new ArrayList<>();

        akaryakitBilgi.add("Total SA, Fransa merkezli ??ok uluslu petrol ??irketi. \"S??per b??y??k\" 6 petrol ??irketinden biridir. ??irketin merkezi Paris yak??nlar??ndaki Tour Total adl?? binada bulunmaktad??r.");
        akaryakitBilgi.add("Elf, 1924 y??l??nda Ernest Mercier taraf??ndan kurulan Total S.A. b??nyesindeki binek ara?? motor ya???? markas??d??r. Total S.A. Fransa merkezlidir.");



        List<String> ilac = new ArrayList<>();

        ilac.add("Servier");
        ilac.add("Guerbet");
        ilac.add("Pierre Fabre Medicament");
        ilac.add("Allegra Antihistamine");
        ilac.add("Benzac");
        ilac.add("Benzagel");
        ilac.add("Benzamycin");
        ilac.add("Novalgin");

        List<String> ilacBilgi = new ArrayList<>();

        ilacBilgi.add("Servier ya da di??er ismiyle Laboratoires Servier, merkezi Fransa???da bulunan uluslararas?? bir ila?? ??irketidir. Servier, ??nde gelen Frans??z ba????ms??z ila?? ??irketi ve d??nya ??ap??nda ikinci en b??y??k Frans??z ila?? ??irketidir. 149 ??lkede ??ubeleri vard??r ve sat????lar??n??n% 82'sini Fransa d??????nda ger??ekle??tirmektedir.");
        ilacBilgi.add("Guerbet, t??bbi g??r??nt??lemede kullan??lan kontrast maddelerinin Fransa merkezli bir ??reticisidir . ??irket, 1901'de ilk iyotlu X-??????n?? kontrast maddesi olan Lipiodol'?? ke??feden Marcel Guerbet'in o??lu Andr?? Guerbet taraf??ndan 1926'da kuruldu. 2017 y??l??nda Guerbet'in gelirleri 807,1 milyon ??? idi. D??nya ??ap??nda 2.700'den fazla ki??iyi istihdam etmektedir ve Fransa, ??rlanda, Kanada, Amerika Birle??ik Devletleri ve Brezilya'da ??retim tesisleri bulunmaktad??r. ??irketin merkezi Paris'in bir banliy??s?? olan Villepinte'de bulunmaktad??r ve hisseleri CAC Small hisse senedi endeksine dahildir"+"\n"+"\n"+"\n");
        ilacBilgi.add("Pierre Fabre Medicament ya da Laboratoires Pierre Fabre, Frans??z ??ok uluslu bir ila?? ve kozmetik ??irketidir. ??irketin 2012 y??l??nda konsolide cirosu 1.978 milyar Euro'dur. Merkezi Castres , Midi-Pyr??n??es, Fransa'da bulunmaktad??r.");
        ilacBilgi.add("Allegra, Fexofenadine i??erikli bir allerji ilac??. Fransa kaynakl??d??r.");
        ilacBilgi.add("Benzac AC Jel %5, bakterilerin cilt ??zerinde geli??imini engelleyerek ??al????an antibakteriyel bir ila??t??r. Akne, sivilce tedavisinde kullan??l??r. Men??ei Fransa'd??r.");
        ilacBilgi.add("Benzagel (benzoil peroksit jel), sivilceleri tedavi etmek i??in kullan??lan hafif bir kuruma etkisine de sahip topikal (cilt i??in) bir antibakteriyel maddedir.");
        ilacBilgi.add("Benzamycin topikal jel etken maddesi Benzoil Peroksit ve Aktif Eritromisin, olan jel k??vam??nda, antibiyotik bir topikal ??r??nd??r. Sivilce, iltihapl?? sivilce ve ergenlik d??nemi sivilceleri (akne vulgaris) tedavisinde etkin ??ekilde kullan??l??r.");
        ilacBilgi.add("Novalgin, a??r?? kesici, ate?? d??????r??c?? ve kas??lma ????z??c?? etkilere sahip bir ila??t??r. Frans??z kaynakl??d??r.");




        List<String> endustriyel = new ArrayList<>();

        endustriyel.add("Groupe Schneider");
        endustriyel.add("Metesan");
        endustriyel.add("Legrand");
        endustriyel.add("Duval Messien");
        endustriyel.add("Franklin France");
        endustriyel.add("Merlin Gerin");
        endustriyel.add("Telemechanique");
        endustriyel.add("Square-D");

        List<String> endustriyelBilgi = new ArrayList<>();

        endustriyelBilgi.add("Groupe Schneider ya da bilinen ad??yla Schneider Electric, 1836 y??l??nda Eugene Schneider taraf??ndan kurulan Schneider Electric SE b??nyesindeki enerji y??netimi, otomasyon ????z??mleri, kapsayan donan??m ve yaz??l??m hizmetleri markas??d??r. Schneider Electric SE Fransa merkezli ??okuluslu bir ??irkettir. B??nyesindeki ??irketler: 7-Technologies, ABS EMEA, AEM S.A., APC, APW President Systems, ASCO, AST, Modular, Andover Controls, Andromeda Telematics, Areva T&D, Aveva Group, BEI Technologies, Berger ??? Positec, CDI Power, Citect, Clipsal, Conzerv Systems, Crouzet Automatismes, Digilink, Digital Electronics Corporation, Eliwell, Federal Pioneer Limited, InStep Software, Invensys, Juno Lighting, Kavlico, Larsen & Toubro, Luminous, M&C Energy Group, MGE UPS Systems, Meher Capacitors, Merlin Gerin, Merten, Modicon, Nu-Lec Industries, OVA Bargellini, PDL, Pelco, Power Measurement, SCADAgroup, SolveIT Software, Square D, Summit Energy, TAC, Telemecanique, Telvent, Viconics Electronics, Viridity, Zicom Security Systems."+"\n"+"\n"+"\n"+"\n"+"\n");
        endustriyelBilgi.add("Metesan Lexel, 1981 y??l??nda Necdet ve Cavit Mete karde??ler taraf??ndan kurulan Metesan Elektrik Malzemeleri San. ve Tic. A.??. b??nyesindeki elektrik malzemeleri markas??d??r. Metesan Lexel, Schneider Electric taraf??ndan sat??n al??nd??. Fransa merkezli Schneider Electric 160 y??l?? a??k??n bir s??redir T??rkiye???de faaliyet g??stermektedir. Schneider Electric???in ??zmir, Kocaeli ve Manisa???da ???? ??retim tesisi bulunmaktad??r.");
        endustriyelBilgi.add("Legrand Elektrik, 1860 y??l??nda Legrand ailesi taraf??ndan kurulan trafo, ??alt ??r??nleri, da????t??m panolar??, kesintisiz g???? kayna????, kablo ta????ma sistemleri, anahtar, priz, ev otomasyon sistemleri, acil durum ayd??nlatma ??r??nleri, yap??sal kablolama ve ayd??nlatma kontrol sistemleri markas??d??r. Legrand 180 ??lkede faaliyet g??steren Fransa merkezli bir ??irkettir. T??rkiye pazar??na 1990 y??l??nda Bufer Elektrik???i sat??n alarak Legrand Elektrik Sanayi A.??. ad??yla girdi. 1992 y??l??nda Gebze???de fabrika kurdu.");
        endustriyelBilgi.add("Duval Messien, y??ld??r??mdan korunma ve topraklama alanlar??ndaki faaliyetlerde uzmanla??m???? bir elektrik firmas??d??r.");
        endustriyelBilgi.add("Franklin France ??irketi, y??ld??r??mdan korunmak i??in paratoner ??retimi yapan bir elektrik firmas??d??r.");
        endustriyelBilgi.add("Merlin Gerin, 1920 y??l??nda, Paul-Louis Merlin ve Gaston Gerin taraf??ndan kurulan elektrik ??r??nleri markas??d??r. Schneider Group taraf??ndan sat??n al??nm????t??r.");
        endustriyelBilgi.add("Telemecanique Sensors, 1924 y??l??nda kurulan Telemecanique Sensors Industries SAS b??nyesindeki sens??rler ve sens??r teknolojileri markas??d??r.\n" +
                "Limit anahtarlar??, bas??n?? sens??rleri, foto-elektrik ve yak??nl??k sens??rleri dahil, bir??ok PLC imalat??n??n yan?? s??ra end??striyel kodlay??c??larla s??k?? bir ??ekilde entegre edilmi?? son teknoloji RFID sistemler ??retmektedir.\n" +
                "Telemecanique Sensors Industries SAS, 20???den fazla ??lkede bulunan Fransa merkezli ??irkettir.\n");
        endustriyelBilgi.add("Square D, 1902 tarihinde Bryson Dexter Horton ve James B. McCarthy taraf??ndan kurulan elektrikli ekipman markas??d??r. 1991 y??l??nda Schneider Electric SE grubuyla entegre olmu??tur.");




        List<String> denizcilik = new ArrayList<>();

        denizcilik.add("Beneteau");

        List<String> denizcilikBilgi = new ArrayList<>();

        denizcilikBilgi.add("Beneteau veya B??n??teau, Fransa ve Amerika Birle??ik Devletleri'nde ??retim tesisleri bulunan Frans??z yelkenli ve motorlu tekne ??reticisi markas??d??r. ??irket, d??nya ??ap??nda ??nemli bir pazara h??kim olan b??y??k ve tan??nm???? bir tekne ??reticisidir. Beneteau Group, ayr??ca 1995 y??l??ndan beri di??er prestijli ??reticiler olan Jeanneau ile Lagoon???u b??nyesinde bulundurmaktad??r. ");



        List<String> finans = new ArrayList<>();

        finans.add("Societe General Bankas??");
        finans.add("BNP Paribas");
        finans.add("TEB, T??rkiye Ekonomi Bankas??");

        List<String> finansBilgi = new ArrayList<>();

        finansBilgi.add("Soci??t?? G??n??rale SA, genellikle takma ad?? \" SocGen \", merkezi Paris'te bulunan bir Frans??z ??okuluslu yat??r??m bankas?? ve finansal hizmetler ??irketidir. ??irket evrensel bir bankad??r ve K??resel ????lem Bankac??l??????, Uluslararas?? Bireysel Bankac??l??k, Finansal Hizmetler, Kurumsal ve Yat??r??m Bankac??l??????, ??zel Bankac??l??k, Varl??k Y??netimi ve Menkul K??ymet Hizmetlerini destekleyen b??l??mleri vard??r. Soci??t?? G??n??rale, toplam varl??klar a????s??ndan Fransa'n??n ??????nc?? en b??y??k, Avrupa???n??n ise yedinci en b??y??k bankas??d??r. ??irket, Euro Stoxx 50 borsa endeksinin bir par??as??d??r. BNP Paribas ve Cr??dit Lyonnais ile birlikte Frans??z bankac??l??????n??n Trois Vieilles'lerinden (\"Eski ????l??\") biri olarak bilinir."+"\n"+"\n"+"\n"+"\n");
        finansBilgi.add("BNP Paribas, 2000 y??l??nda Banque Nationale de Paris (BNP) ve Paribas?????n birle??mesiyle kurulmu??tur. 75 ??lkede varl?????? bulunan uluslararas?? bir bankac??l??k grubudur. Fransa Paris merkezli BNP Paribas S.A., 2009 y??l??nda, Bel??ika bankas?? Fortis???in y??zde 75???lik hissesini sat??n alarak Avro b??lgesinin en b??y??k mevduata sahip bankas?? olmu??tur. 2017 y??l??nda uluslararas?? alanda 8. s??rada yer ald??.");
        finansBilgi.add("T??rkiye Ekonomi Bankas?? ya da TEB, T??rk Ekonomi Bankas?? (TEB), 1927 y??l??nda Kocaeli Halk Bankas?? T. A. ??. olarak kurulan ??zel sekt??r ticari bankalar??ndan biridir. Bankac??l??k hizmetlerinin yan?? s??ra grup ??irketleri ve i??tirakleri vas??tas??yla da faktoring, t??ketici finasman?? finansal kiralama, filo kiralama, portf??y y??netimini de i??eren geni?? bir finansal ??r??n ve hizmet yelpazesini m????terilerine sunmaktad??r. 2005 y??l??nda Fransa Paris merkezli BNP Paribas S.A. ile ??olako??lu Grubu aras??nda ger??ekle??en ortakl??k sonucunda BNP Paribas TEB???in ??o??unluk hissedar?? olmu??tur."+"\n"+"\n"+"\n");

        List<String> hotel = new ArrayList<>();
        hotel.add("Ibis");
        hotel.add("Sofitel");
        hotel.add("Accor");
        hotel.add("Novotel");

        List<String> hotelBilgi = new ArrayList<>();
        hotelBilgi.add("Ibis, Accor'a ait bir otel markas??d??r. 1974'te kurulan Ibis, 2011'de Ibis Styles ve Ibis Budget'??n piyasaya s??r??lmesiyle Accor'un ekonomi mega markas?? oldu. Aral??k 2019 itibar??yla, Ibis markas?? alt??nda 1.218 otel (Styles ve Budget oteller hari??), 67 ??lkede toplam 155.678 oda bulunmaktad??r.");
        hotelBilgi.add("Sofitel Hotels & Resorts, Paris, Fransa merkezli ve 1980'den beri Accor'a ait bir l??ks oteller zinciridir.\n" +
                "\n" +
                "1964 y??l??nda Fransa'da kurulan Sofitel, d??nya ??ap??nda h??zla geli??erek 200'den fazla tesise ula??t??. 2008 y??l??nda Sofitel sadece l??ks otel markas?? olmu??tur.");
        hotelBilgi.add("Accor S.A. otellere, tatil k??ylerine ve tatil m??lklerine sahip olan, bunlar?? y??neten ve franchise veren bir Frans??z ??ok uluslu otelcilik ??irketidir. Avrupa'n??n en b??y??k ve d??nyan??n alt??nc?? en b??y??k otelcilik ??irketidir.\n" +
                "\n" +
                "Accor, d??nya ??ap??nda 4.800'den fazla otel ve 280.000 ??al????an??yla 100 ??lkede faaliyet g??stermektedir. Toplam kapasitesi yakla????k 704.000 odad??r. Accor, l??ks segment (Raffles, Fairmont ve Sofitel dahil), premium segment (MGallery, Pullman ve Swiss??tel dahil), orta ??l??ekli segment (Novotel, Mercure ve Adagio dahil) gibi konuk a????rlaman??n her segmentini kapsayan markalar??n sahibidir ve bunlar?? i??letmektedir. ekonomi segmenti (ibis ve hotelF1'i i??erir). Accor ayr??ca onefinestay, D-Edge, ResDiary, John Paul ve Potel & Chabot gibi dijital konaklama ve etkinlik organizasyonunda uzmanla??m???? ??irketlere de sahiptir.\n" +
                "\n" +
                "??irketin genel merkezi Fransa'n??n Issy-les-Moulineaux kentinde bulunmaktad??r ve Paris borsas??ndaki CAC 40 endeksinin bir bile??enidir."+"\n"+"\n"+"\n"+"\n"+"\n");
        hotelBilgi.add("Novotel, modern, do??al ve sezgisel tasar??ma odaklanm???? orta ??l??ekli bir otel markas??d??r. 1967'de Fransa'da kurulan ??irket, 1983'te Accor grubu haline geldi ve Novotel, Accor'un ??oklu marka stratejisinin temel markas?? olarak kald??. 2018 verilerine g??re Novotel, 59 ??lkede 492 oteli y??netmektedir.");


        List<String> telekomunikasyon = new ArrayList<>();
        telekomunikasyon.add("Orange");

        List<String> telekomunikasyonBilgi = new ArrayList<>();
        telekomunikasyonBilgi.add("Orange S.A., bir Frans??z ??okuluslu telekom??nikasyon ??irketidir. D??nya ??ap??nda 266 milyon m????teriye sahiptir ve Fransa'da 89.000, ba??ka yerlerde 59.000 ki??iyi istihdam etmektedir. Vodafone, Telef??nica ve VEON'dan sonra d??nyan??n en b??y??k onuncu ve Avrupa'n??n d??rd??nc?? en b??y??k mobil ??ebeke operat??r??d??r. 2015 y??l??nda grubun cirosu 40 milyar Euro'dur. ??irketin merkezi Paris'in 15. b??lgesinde yer almaktad??r. ??u anki CEO St??phane Richard'd??r. ??irket, Euro Stoxx 50 borsa endeksinin bir bile??enidir.\n" +
                "\n" +
                "Orange, 2006 y??l??ndan bu yana ??irketin mobil, sabit hat, internet ve IPTV hizmetleri i??in ana markas?? olmu??tur. 1990'lar??n ba????nda Hutchison Whampoa'n??n Microtel Communications'??n kontrol hissesini sat??n almas?? ve onu \"Orange\" olarak yeniden markalamas??yla 1994 y??l??nda ortaya ????km????t??r. 1999'da Mannesmann'??n bir yan kurulu??u oldu ve 2000 y??l??nda France T??l??com taraf??ndan sat??n al??nd??. ??irket, 1 Temmuz 2013'te Orange olarak yeniden markaland??."+"\n"+"\n"+"\n"+"\n"+"\n");

        List<String> bilisim = new ArrayList<>();
        bilisim.add("Capgemini");

        List<String> bilisimBilgi = new ArrayList<>();
        bilisimBilgi.add("Capgemini SE, Fransa merkezli bir bili??im teknolojileri hizmeti ve dan????manl??k ??irketidir. ??irket, 1 Ekim 1967 tarihinde Serge Kampf taraf??ndan kurulmu?? olup dan????manl??k, teknoloji, profesyonel ve d???? kaynak hizmetleri sunmaktad??r. ??irketin merkezi Paris'in 17. semtinde yer almaktad??r. \n" +
                "Capgemini, Euronext'te i??lem g??rmekte olup ??irketin 50'den fazla ??lkede 200,000'den fazla ??al????an?? bulunmaktad??r.\n");

        List<String> sigara = new ArrayList<>();
        sigara.add("Gauloises");

        List<String> sigaraBilgi = new ArrayList<>();
        sigaraBilgi.add("Gauloises, Frans??zca \"Galyal?? kad??nlar\" anlam??na gelir(Frans??zcada \"sigara\" di??ildir). Frans??z ??retimi bir sigara markas??d??r. ??o??u ??lkede Ocak 2008'de Altadis'i sat??n almas??n??n ard??ndan Imperial Tobacco ??irketi taraf??ndan ??retildi, ancak Almanya'da Reemtsma taraf??ndan ??retilip sat??ld??.");


        productList.put(groupList.get(0),mutfak);
        productList.put(groupList.get(1),sigorta);
        productList.put(groupList.get(2),sampuan);
        productList.put(groupList.get(3),giyim);
        productList.put(groupList.get(4),supermarket);
        productList.put(groupList.get(5),sutUrun);
        productList.put(groupList.get(6),siseSuyu);
        productList.put(groupList.get(7),otomobil);
        productList.put(groupList.get(8),bebekGiyim);
        productList.put(groupList.get(9),kozmetik);
        productList.put(groupList.get(10),parfum);
        productList.put(groupList.get(11),ciltBakim);
        productList.put(groupList.get(12),insaat);
        productList.put(groupList.get(13),seyahat);
        productList.put(groupList.get(14),cakmak);
        productList.put(groupList.get(15),spor);
        productList.put(groupList.get(16),akaryakit);
        productList.put(groupList.get(17),ilac);
        productList.put(groupList.get(18),endustriyel);
        productList.put(groupList.get(19),denizcilik);
        productList.put(groupList.get(20),finans);
        productList.put(groupList.get(21),hotel);
        productList.put(groupList.get(22),telekomunikasyon);
        productList.put(groupList.get(23),bilisim);
        productList.put(groupList.get(24),sigara);



        detailList.put(groupList.get(0),mutfakBilgi);
        detailList.put(groupList.get(1),sigortaBilgi);
        detailList.put(groupList.get(2),sampuanBilgi);
        detailList.put(groupList.get(3),giyimBilgi);
        detailList.put(groupList.get(4),supermarketBilgi);
        detailList.put(groupList.get(5),sutUrunBilgi);
        detailList.put(groupList.get(6),siseSuyuBilgi);
        detailList.put(groupList.get(7),otomobilBilgi);
        detailList.put(groupList.get(8),bebekGiyimBilgi);
        detailList.put(groupList.get(9),kozmetikBilgi);
        detailList.put(groupList.get(10),parfumBilgi);
        detailList.put(groupList.get(11),ciltBakimBilgi);
        detailList.put(groupList.get(12),insaatBilgi);
        detailList.put(groupList.get(13),seyahatBilgi);
        detailList.put(groupList.get(14),cakmakBilgi);
        detailList.put(groupList.get(15),sporBilgi);
        detailList.put(groupList.get(16),akaryakitBilgi);
        detailList.put(groupList.get(17),ilacBilgi);
        detailList.put(groupList.get(18),endustriyelBilgi);
        detailList.put(groupList.get(19),denizcilikBilgi);
        detailList.put(groupList.get(20),finansBilgi);
        detailList.put(groupList.get(21),hotelBilgi);
        detailList.put(groupList.get(22),telekomunikasyonBilgi);
        detailList.put(groupList.get(23),bilisimBilgi);
        detailList.put(groupList.get(24),sigaraBilgi);




        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {



                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("productName",productList.get(groupList.get(i)).get(i1));
                intent.putExtra("productDetail",detailList.get(groupList.get(i)).get(i1));
                intent.putExtra("groupIndex",i);
                intent.putExtra("childIndex",i1);
                startActivity(intent);


                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }

                return false;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.ayarlar_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.ara);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setQueryHint("??r??n Ara");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                final ArrayList<String> arrayList = new ArrayList<>();


                for(String categories : groupList) {

                    for(String products: productList.get(categories)){

                        if (!s.isEmpty()) {
                            if (products.toLowerCase().contains(s.toLowerCase())) {

                                arrayList.add(products + "  ("+ categories+")");

                                expandableListView.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            expandableListView.setVisibility(View.VISIBLE);
                        }


                    }
                }


                final ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.list_product,R.id.productText,arrayList);
                listView2.setAdapter(arrayAdapter);


                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        StyleableToast.makeText(MainActivity.this,"??r??nle alakal?? detayl?? bilgileri ilgili kategori alt??ndan ??r??n ismine t??klayarak alabilirsiniz.",R.style.exampleToast).show();
                    }
                });



                arrayAdapter.notifyDataSetChanged();




                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


            if (item.getItemId() == R.id.ayarlar) {
                Intent intent = new Intent(MainActivity.this, AyarlarAktivitesiTurk.class);
                startActivity(intent);
                finish();
            }

            return super.onOptionsItemSelected(item);
        }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeListener);
    }


}