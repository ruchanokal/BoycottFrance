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

        groupList.add("Mutfak eşyaları");
        groupList.add("Sigorta");
        groupList.add("Şampuan / Saç Bakım");
        groupList.add("Giyim / Aksesuar");
        groupList.add("Süpermarket");
        groupList.add("Süt Ürünleri");
        groupList.add("Şişe Suyu");
        groupList.add("Otomobil / Yedek Parça");
        groupList.add("Bebek Giyim / Mama / Oyuncak");
        groupList.add("Kozmetik");
        groupList.add("Parfüm");
        groupList.add("Cilt Bakım Ürünleri");
        groupList.add("İnşaat");
        groupList.add("Seyahat");
        groupList.add("Çakmak");
        groupList.add("Spor Ekipmanı");
        groupList.add("Akaryakıt");
        groupList.add("İlaç");
        groupList.add("Endüstriyel / Elektrik Ürünleri");
        groupList.add("Denizcilik");
        groupList.add("Finans");
        groupList.add("Hotel");
        groupList.add("Telekomünikasyon");
        groupList.add("Bilişim");
        groupList.add("Sigara");



        List<String> mutfak = new ArrayList<>();

        mutfak.add("Tefal");
        mutfak.add("T-fal");
        mutfak.add("Moulinex");
        mutfak.add("Groupe SEB");

        List<String> mutfakBilgi = new ArrayList<>();

        mutfakBilgi.add("Tefal, dünyada yapışmaz tava tencere sektörünün kurucusudur. " +
                "1956 yılında, PTFE (teflon) malzemesinin mucidi " +
                "Marc Grégoire tarafından" +
                " Fransa'da kurulmuştur. 1968 yılında " +
                "SEB şirketi tarafından satın alınmıştır. " +
                "Fransa merkezli Tefal, Türkiye'nin de içinde " +
                "bulunduğu 120 ülkede faaliyet göstermektedir.");
        mutfakBilgi.add("T-fal, Tefal markasının " +
                "bazı ülkelerde kullandığı isimdir.  " +
                "Amerika Birleşik Devletleri ve Kanada’da bu ismi kullanır.");
        mutfakBilgi.add("Moulinex, ev ürünleri markaları olan Rowenta, Calor, All-Clad, Lagostina, Krups ve Tefal ile birlikte bir Groupe SEB markasıdır. Şirket, meşhur Mouli rendesini tasarladı ve üretti. Şirket, 1932'de sebzeleri püre haline getirmek için el yapımı bir gıda fabrikası olan Moulin-Légumes'ı icat eden Jean Mantelet tarafından kuruldu. Tasarım, modern mutfak robotunun erken bir öncüsü olarak kabul edilir.\n" +
                "\n" +
                "Mantelet'in iş planı, \"geleceği olan bir sanayi sektörü seçmek, seri üretim kullanmak, fiyatları düşük tutmak, maaşların düşük olduğu bölgelerde üretim yapmak, maliyetleri düşürmek, tedarikçilere olan bağımlılıkları azaltmak, araştırma ve tanıtıma odaklanmak\" idi. 1929 ile 1953 yılları arasında Mantelet 93 patent başvurusunda bulundu."+"\n"+"\n"+"\n"+"\n"+"\n");
        mutfakBilgi.add("Groupe SEB, küçük ev aletleri üreten büyük bir Fransız konsorsiyumudur. Groupe SEB ile ilişkili önemli marka isimleri arasında All-Clad, Krups, Moulinex, Rowenta, Tefal (OBH Nordica dahil) ve WMF Group bulunmaktadır. Groupe SEB web sitesine göre, düşük fiyatlı Çinli rakiplerden önemli bir rekabetle karşılaştılar, ancak sabit bir satış seviyesini korumayı başardılar. Ürün hatlarının büyük bir kısmı şimdi Çin'de üretilmektedir. Merkezi Lyon banliyösü Ecully'dedir.\n" +
                "\n" +
                "Groupe SEB konsorsiyumunun öncüsü 1857'de Antoine Lescure tarafından kuruldu. 1977'de, birinci nesil ev video oyun konsolu Telescore 750'yi piyasaya sürdüler. Daha sonra Telescore 751 ve Telescore 752'yi piyasaya sürdüler."+"\n"+"\n"+"\n"+"\n");



        List<String> sigorta = new ArrayList<>();

        sigorta.add("Axa Sigorta");
        sigorta.add("Groupama International");
        sigorta.add("Equitable Life");

        List<String> sigortaBilgi = new ArrayList<>();

        sigortaBilgi.add("AXA Sigorta S.A., uluslararası Fransız yatırım, " +
                "emeklilik ve sigorta grubu. Merkezi Paris’tedir. " +
                "AXA bağımsız çalışan işletmeler konglomeratıdır, her " +
                "ülkenin hukuk sistemine ve düzenlemelerine uymakla yükümlüdür. " +
                "Grup özellikle Batı Avrupa, Kuzey Amerika, Asya-Pasifik " +
                "ve Ortadoğu bölgelerinde çalışır.");
        sigortaBilgi.add("Groupama, Fransız sigorta ve emeklilik şirketi.\n" +
                "Türkiye pazarına Başak Sigorta, Başak Emeklilik, Güven Sigorta ve Güven Hayat" +
                " şirketlerini satın alarak girmiştir.  " +
                "Fransa’nın en büyük mütüel sigorta şirketi olan Groupama, " +
                "tarım ve sağlık sigortalarından evde bakım sigortalarına uzanan " +
                "pek çok alanda lider konumdadır. Groupama; Fransa dışında, " +
                "İtalya, Portekiz, Slovakya, Türkiye, Yunanistan, Macaristan, " +
                "Romanya, Bulgaristan, Çin, Vietnam ve Tunus olmak üzere 11 ülkede " +
                "faaliyet göstermektedir. Groupama’nın 13 milyon müşterisi " +
                "ve 34.000 çalışanı bulunmaktadır.\n"+"\n"+"\n"+"\n");

        sigortaBilgi.add("Equitable Holdings (eski adıyla The Equitable Life Assurance Society " +
                "of the United States ve AXA Equitable Life Insurance Company " +
                "ve aynı zamanda The Equitable olarak da bilinir) 1859'da Henry " +
                "Baldwin Hyde tarafından kuruldu. 1991 yılında, bir Fransız sigorta" +
                " şirketi olan Axa, şirketin çoğunluk kontrolünü satın aldı. 2004 " +
                "yılında ismini resmi olarak AXA Equitable Life Insurance Company " +
                "olarak değiştirdi. 2018 itibariyle, şirketin Kaliforniya Eyaleti" +
                " tarafından lisanslanmış 15.800'den fazla temsilcisi vardı. Ocak " +
                "2020'de, AXA'dan yan ürünü ve Mayıs 2018'de başlayan ilgili halka" +
                " arzların ardından adını Equitable olarak değiştirdi."+"\n"+"\n"+"\n"+"\n");




        List<String> sampuan = new ArrayList<>();

        sampuan.add("L'Oreal");
        sampuan.add("Lancome");
        sampuan.add("Kerastase");

        List<String> sampuanBilgi = new ArrayList<>();

        sampuanBilgi.add("L'Oréal Group, Dünya'nın en büyük kozmetik ve güzellik şirketidir. " +
                "Fransa'da Paris banliyösünde Paris'e kayıtlı ofisi ve merkez ofisi ile Clichy, " +
                "Hauts-de-Seine'de bu kozmetik alanında faaliyetlerini geliştirmekte olup; " +
                "saç rengi, cilt bakımı, güneşten korunma, makyaj, parfüm ve saç bakımı üzerine" +
                " konsantre ve ilaç alanlarında aktif ve ABD'de üst nanoteknoloji'ye en çok " +
                "yatırım yapan ve bu konuda en çok patenti elinde bulunduran bir şirkettir." +
                " Kurucusu 1907 yılında \"halesi\" adında bir saç boyası formülü geliştiren " +
                "Eugène Schueller adında genç bir Fransız kimyagerdi.\n" +
                "L'Oréal; Clarisonic, Drakkar Noir, Kérastase, L'Oréal Professionnel, " +
                "Lancôme, Magic Shave, NYX Cosmetics, Pureology, Redken, Shu Uemura, " +
                "Urban Decay, Kiehl's, Maybelline, Vichy, Garnier, Helena Rubinstein, " +
                "Ralph Lauren, Giorgio Armani Parfums, Biotherm, Cacharel, CeraVe, " +
                "SkinCeuticals, La Roche-Posay gibi markaları da bünyesinde bulunur.\n"+"\n"+"\n"+"\n");
        sampuanBilgi.add("Lancôme, ürünleri uluslararası olarak dağıtan bir Fransız" +
                " lüks parfüm ve kozmetik evidir. Lancôme, yüksek fiyatlarla " +
                "lüks cilt bakımı, parfümler ve makyaj sunan L’Oréal Lüks " +
                "Ürünler Bölümü'nün bir parçasıdır.");
        sampuanBilgi.add("Kérastase , ürünleri uluslararası olarak dağıtan" +
                " bir Fransız lüks saç bakım serisidir. Kérastase, " +
                "L’Oréal Profesyonel Ürünler Bölümü'nün bir parçasıdır.");



        List<String> giyim = new ArrayList<>();

        giyim.add("Lacoste");
        giyim.add("Givenchy");
        giyim.add("Yves Saint Laurent");
        giyim.add("René Derhy");
        giyim.add("Daniel Hechter");
        giyim.add("Pierre Cardin");
        giyim.add("Louis Vuitton");
        giyim.add("Fred Joaillier");
        giyim.add("Berluti");
        giyim.add("Cacharel");
        giyim.add("Hermes");
        giyim.add("Longchamp");
        giyim.add("Lancel");
        giyim.add("Chloé");

        List<String> giyimBilgi = new ArrayList<>();

        giyimBilgi.add("Lacoste, Fransa'nın en önemli giyim ve aksesuar markalarından biridir. " +
                "1933 yılında René Lacoste tarafından kurulmuştur. O zamanların " +
                "en ünlü tenisçilerinden olan Lacoste, Davis Cup tenis turnuvasını" +
                " kazanan ve Amerikalı olmayan ilk tenisçidir.\n" +
                "\n" +
                "Tenis maçlarında kullanılan giysilere seçenek olarak, " +
                "rahat, terlemeyi önleyen, hareket yeteneğini artıran bir " +
                "tasarım yaparak bugünkü Lacoste Polo Tişörtü yaratmıştır. " +
                "Takma adı olan timsahı ise simge olarak bu t-shirt'ün sol " +
                "tarafına yerleştiren Lacoste, bu şekilde dünyada ilk defa " +
                "bir giyim ürününün görünen yüzeyinde simge kullanan marka " +
                "olmuştur. 1933'ten beri kalıbı değişmeyen tek ürünü olan " +
                "Lacoste Polo markanın ikonik ürünüdür.\n"+"\n"+"\n"+"\n");
        giyimBilgi.add("Givenchy, 1952 yılında Hubert de Givenchy " +
                "tarafından kurulan; giyim, aksesuar, parfüm " +
                "ve kozmetik ürünleri markasıdır.\n" +
                "\n" +
                "Givenchy, 1988 yılında dünyanın " +
                "en büyük lüks tüketim malları üreten " +
                "gruplarından Fransa merkezli LVMH " +
                "tarafından satın alındı.");
        giyimBilgi.add("Yves Saint Laurent  veya YSL, 1962 yılında tasarımcı Yves " +
                "Saint Laurent ve ortağı Pierre Bergé tarafından kurulmuş lüks " +
                "moda evidir. Bugün smokin diye tanımlanan ‘Le Smoking’ kıyafeti" +
                ",  keskin dikiş hatlı, tamamı siyah takım elbise, " +
                "1966 yılında Saint Laurent’in modadaki etkisini" +
                " en iyi tanımlayan imza stili haline geldi. " +
                "Bu günlerde birçok ünlünün giyim tercihidir.");
        giyimBilgi.add("René Derhy bazen Derhy olarak kısaltılır, " +
                "1969'da René ve Yvette Derhy tarafından " +
                "kurulan bir Fransız hazır giyim markasıdır.");
        giyimBilgi.add("Daniel Hechter, dünya çapında 45 lisans sahibi olan bir Fransız " +
                "moda ve yaşam tarzı markasıdır. Erkek ve kadın giyim, " +
                "aksesuar ve tüketim malları satmaktadır. 1962'de Fransız " +
                "modacı Daniel Hechter, ilk kadın koleksiyonunu yayınladı. " +
                "Üç yıl sonra, bir çocuk serisi ekledi ve 1968'de erkek serisiyle" +
                " moda serisini tamamladı. Bu koleksiyonlar, hazır giyim " +
                "veya Prêt-à-porter modasının mucidi olarak onu popüler " +
                "hale getirdi. Birkaç yıl sonra bu koleksiyonlar spor ve " +
                "günlük giyim olarak genişletildi. Bunu gözlük, parfüm, " +
                "kalem ve tüketim malları izledi, ardından saat ve deri " +
                "ürünler diğerleri arasına katıldı. Daniel Hechter Paris'in" +
                " en dikkat çeken ürünleri ayakkabılarıdır. " +
                "Aksesuarlar arasında en büyük ciroyu oluşturur."+"\n"+"\n"+"\n"+"\n");
        giyimBilgi.add("1950 yılında Fransa’da Pierre Cardin tarafından " +
                "temelleri atılan marka, ilkleri ve sıra dışı " +
                "tasarımları ile dünya modasına yön vererek en " +
                "bilinen giyim markalarından biri olmuştur. " +
                "Özellikle uzay araştırmalarının yoğunlaştığı " +
                "60’lı yıllarda dönemin coşkusunu koleksiyonlarına" +
                " “Space Age” temasıyla yansıtan Pierre Cardin, " +
                "modada avant-garde ve fütüristik akımın ilk " +
                "temsilcilerindendir. ");
        giyimBilgi.add("Louis Vuitton markası, kurucusu Louis Vuitton Malletier tarafından 1854 yılında kuruldu.\n" +
                "Kadınların modayla oldukça yakından ilgilenmeye başladığı ve kıyafetlerin çok hacimli olması ve geniş yer kaplaması sebebiyle büyük sandıkların gözde olduğu dönemde üretime sandıkla başlayan Louis Vuitton, bugün dünyada hazır giyim, ayakkabı, çanta, saat, takı-mücevher ve aksesuar üretimiyle lüks tüketimin öncü temsilcilerindendir.\n");
        giyimBilgi.add("Fred Joaillier, kurucusu Fred Samuel'in adını taşıyan bir Fransız mücevher markasıdır. İlk mağazası 1936'da Paris'te açıldı ve Jean Cocteau tarafından tasarlanan mücevherler ve Marlène Dietrich veya Grace Kelly gibi ünlü müşterileri ile ünlendi.");
        giyimBilgi.add("Berluti, ayakkabı ve bot üretiminde özellikle dana derisi, kanguru derisi ve timsah derisinin kaplamaları olmak üzere erkek giyim üreten bir LVMH yan kuruluşudur. Deri kemer, çanta ve cüzdanların yanı sıra ısmarlama ve hazır giyim ürünleri üretir. 1895 yılında Marche'lı İtalyan Alessandro Berluti tarafından kurulmuştur.");
        giyimBilgi.add("Cacharel, 1962'de Jean Bousquet tarafından kurulan Fransız hazır giyim, parfüm ve aksesuar markasıdır. Cacharel tasarımları, genç tarzları, hafiflikleri, incelikleri ve parlak renk kullanımlarıyla karakterizedir. Bir L'Oréal Group üyesidir.");
        giyimBilgi.add("Hermès International SA veya kısaca Hermès, 1837'de kurulmuş bir Fransız yüksek moda lüks eşya üreticisidir. Deri, yaşam tarzı aksesuarları, ev mobilyaları, parfümeri, mücevherler, saatler ve hazır giyim konusunda uzmanlaşmıştır.");
        giyimBilgi.add("Longchamp, 1948'de Jean Cassegrain tarafından Paris'te kurulan Fransız lüks bir deri ürünler şirketidir. Jean Cassegrain Dünya’nın ilk lüks deri kaplı pipolarını üretti, ardından cüzdanlar, pasaport kılıfları vb.  küçük deri ürünlere genişledi. Longchamp, 1971'de ilk kadın çantasını piyasaya sürdü ve Fransa'nın önde gelen deri eşya üreticilerinden biri oldu. Şirket bugün, deri ve kanvas çantalar, valizler, ayakkabılar, seyahat eşyaları, moda aksesuarları ve bir dizi \"hazır giyim\" kadın tasarımcı kıyafetleri de dahil olmak üzere çok çeşitli lüks ürünler tasarlar ve üretir. Yaklaşık 1.500 perakende satış noktası aracılığıyla 80 ülkede iş yapmaktadır, hala özel mülkiyettedir ve Cassegrain'in kurucu ailesi tarafından yönetilmektedir."+"\n"+"\n"+"\n"+"\n");
        giyimBilgi.add("Lancel, 1876'da Paris'te Angèle ve Alphonse Lancel tarafından kurulan ve oğulları Albert tarafından geliştirilen bir Fransız lüks deri ürünleri şirketidir. Lancel, el çantaları, cüzdanlar, çantalar, seyahat eşyaları, valizler, aksesuarlar gibi erkekler ve kadınlar için deri ürünler sağlar. Markanın 140 yılı aşkın bir süredir lüks deri ürünler üretme geleneği var. Şirketin merkezi halen Paris, Fransa'dadır. ");
        giyimBilgi.add("Chloé, Gaby Aghion tarafından 1952'de kurulan bir Fransız lüks moda evidir. Merkezi Paris, Fransa'dadır. Ev, lüks markalar holding şirketi Richemont Group'a aittir. Chloé; Marion Cotillard, Sienna Miller, Madonna, January Jones, Maggie Gyllenhaal, Cameron Diaz, Emma Stone, Clémence Poésy ve Katie Holmes gibi birçok ünlü tarafından giyildi.");


        List<String> supermarket = new ArrayList<>();

        supermarket.add("Gima");
        supermarket.add("Carrefour");

        List<String>  supermarketBilgi = new ArrayList<>();

        supermarketBilgi.add("Gima, 1956 yılında kurulan bir kamu iktisadi teşekkülü olan Gima, Türkiye'nin ilk ulusal süpermarket zinciridir. 1996 yılında çoğunluk hisseleri ve yönetiminin Fiba Holding satın aldı. 2005 yılında Endi ile birlikte CarrefourSA tarafından satın alındı. 10 Haziran 2007 tarihinde CarrefourSA Express adını almıştır. 10 yıl sonra, 2017 yılında Ankara’da tekrar faaliyete geçmiştir. CarrefourSA üyesidir.");
        supermarketBilgi.add("Carrefour, Fransa merkezli uluslararası süpermarketler zinciri. Walmart'tan sonra dünyanın en büyük cirosuna sahip mağazacılık şirketidir. Ağırlıklı olarak Avrupa Birliği, Brezilya ve Arjantin'de faaliyet göstermekle birlikte Kuzey Afrika ve Asya'da da mağazalara sahiptir. Şirket isminin Fransızca sözcük anlamı \"yol\" ya da \"dörtyol ağzı\"dır. ");



        List<String> sutUrun = new ArrayList<>();

        sutUrun.add("Danone");
        sutUrun.add("Yoplait");
        sutUrun.add("La Vache Qui Rit");
        sutUrun.add("Fromageries Riches Monts");
        sutUrun.add("Gerard");
        sutUrun.add("Taillefine");
        sutUrun.add("Président");
        sutUrun.add("Elle & Vire");
        sutUrun.add("Babybel");

        List<String> sutUrunBilgi = new ArrayList<>();

        sutUrunBilgi.add("Danone, sütlü ürünleri ve içecekleri ile tanınan Fransa merkezli çok uluslu bir şirket. Danone şirketinin kuruluşu Osmanlı Devleti’ne kadar uzanır. Yahudi asıllı doktor İzak Karasu'nun (İsaac Carraso) adı ile de bilinir. Balkan savaşları sırasında Yunanlar Selanik'i işgal ettiği zaman Karasu ailesi 1912 yılında İspanya'nın Barselona kentine göç etti. 1919'da geleneksel Osmanlı yoğurtlarını üretip oğlu Daniel'in ismi \"Danone\" adıyla piyasaya sürdü. İsaac'in oğlu Daniel Carasso, babasının temelini attığı yoğurt işini 1929 yılında Fransa’ya taşıdı ve burada 1932 yılında Levallois-Perret’de ilk fabrikayı inşa ettirdi. II. Dünya Savaşı yıllarında 1942 yılında ABD’ye göç eden Daniel Carasso bir süre bu ülkede çalıştı ve ilk yoğurt fabrikasını kurdu. Bir zaman sonra Danone dünyanın önde gelen gıda gruplarından birine dönüştü."+"\n"+"\n"+"\n"+"\n"+"\n");
        sutUrunBilgi.add("Yoplait Dünya’nın en büyük franchise yoğurt markasıdır. Birleşik Devletler merkezli gıda şirketi General Mills ve Fransız süt ürünleri kooperatifi Sodiaal'ın ortak mülkiyetindedir. Birçok ülkede, o ülkelerin markaları tarafından satış ve pazarlaması yapılmakta ve yönetilmektedir.");
        sutUrunBilgi.add("La Vache Qui Rit , Türkçe’de  gülen inek demektir. 1865'ten beri Bel Group (Fromageries Bel) tarafından üretilen işlenmiş peynir ürünlerinin bir markasıdır ve özellikle markanın en popüler ürünü olan yayılabilen peynir anlamına gelir. Şirket 1921'de kurulmuştur.");
        sutUrunBilgi.add("Compagnie des Fromages et RichesMonts (CF&R), Fransız peynirinin, daha özel olarak geleneksel Fransız yumuşak peynirinin ( camembert ve brie gibi ) ve raclette peynirinin üretimi ve pazarlanmasında uzmanlaşmış bir Fransız gıda işleme şirketidir.");
        sutUrunBilgi.add("Gérard ailesi 19. yüzyılın ilk yarısında, Fransa'nın doğusunda Vosges dağlarında bulunan küçük bir köy olan Le Tholy'de peynir üretimine başlar. 1898'de François GÉRARD'ın oğlu Eugène GÉRARD, La Fromagerie Gérard peynir fabrikasını kurar. Bugün ünlü bir peynir markasıdır.");
        sutUrunBilgi.add("Taillefine , süt ürünleri, tatlı kremalar, kompostolar ve az yağlı içecekler üreten, 1964 yılında kurulan bir Danone Group markasıdır.");
        sutUrunBilgi.add("Président Laval, Mayenne merkezli Lactalis şirketine ait bir Fransız süt ürünleri markasıdır. Marka 1968 yılında André Besnier tarafından kuruldu. Tereyağı ve geleneksel peynirlerin endüstriyel olarak üretilen çeşitleri için kullanılır.");
        sutUrunBilgi.add("Elle & Vire, tarımsal gıda grubu Savencia Fromage & Dairy'ye ait endüstriyel işlenmiş süt ürünlerinin ticari markasıdır. Bu grubun Fransız yan kuruluşu, Condé-sur-Vire (Manche) merkezli \"Société Coopérative Agricole Elle et Vire\", Cœur de Lion ve Elle-et-Vire fabrikaları için sığır yetiştiricilerinden süt satın alır ve toplar.");
        sutUrunBilgi.add("Babybel, tek tek paketlenmiş ve çeşitli tatlarda mevcut olan küçük atıştırmalık peynir ürünleri markasıdır. Fransa'nın Jura bölgesinde kökleri olan Le Groupe Bel'in (The Bel Group) bir ürünüdür ve 1865 yılında Jules Bel tarafından başlatılmıştır.");


        List<String> siseSuyu = new ArrayList<>();

        siseSuyu.add("Perrier");
        siseSuyu.add("Evian");
        siseSuyu.add("Danone");

        List<String> siseSuyuBilgi = new ArrayList<>();

        siseSuyuBilgi.add("Perrier, Gard département'ta bulunan Vergèze kaynağında yakalanan bir Fransız doğal şişe maden suyu markasıdır. İsviçre şirketi Nestlé'nin Nestlé Waters bölümünün bir parçasıdır. Perrier, doğal olarak oluşan karbonasyonu, kendine özgü yeşil şişesi ve akranlarından daha yüksek karbonasyon seviyeleri ile bilinir.");
        siseSuyuBilgi.add("Évian, Cenevre Gölü'nün güney kıyısında, Évian-les-Bains yakınlarındaki çeşitli kaynaklardan gelen bir şişelenmiş su markasıdır. Günümüzde Évian, Fransız çokuluslu şirket Danone'ye aittir. Fransa, Amerika Birleşik Devletleri, Belçika, İsviçre ve Rusya gibi ülkelerin yanı sıra Türkiye’de        Migros tarafından satılmaktadır.");
        siseSuyuBilgi.add("Fransa merkezli çok uluslu bir sütlü ürünler ve içecekler üreten bir şirket olan Danone, Yahudi asıllı doktor İsaac Carraso tarafından kurulmuştur.");



        List<String> otomobil = new ArrayList<>();

        otomobil.add("ValeoOto");
        otomobil.add("Peugeot");
        otomobil.add("Renault");
        otomobil.add("Citroën");
        otomobil.add("Michelin");
        otomobil.add("Uniroyal");
        otomobil.add("Recamic");
        otomobil.add("Dacia");

        List<String>  otomobilBilgi = new ArrayList<>();

        otomobilBilgi.add("Valeo, 1923 yılında Saint Ouen’de (Fransa) kurulmuştur. Valeo, merkezi Fransa'da bulunan ve Paris Borsası'nda ( CAC-40 Endeksi) listelenen bir Fransız küresel otomotiv tedarikçisidir. Otomobil üreticilerine ve satış sonrası pazara ürün yelpazesi sunar. Grup, dünya çapında 33 ülkede 113.600 kişiyi istihdam etmektedir. 186 üretim tesisi, 59 Ar-Ge merkezi ve 15 dağıtım platformuna sahiptir. ");
        otomobilBilgi.add("Peugeot, Fransız otomobil, bisiklet ve motosiklet markası, günümüzde PSA Peugeot Citroën`in bir parçasıdır. 1810 yılında el aletleri ile üretime başlamıştır, 1890 yılından bu yana da otomobil üreticisidir. Türkiye'deki ticari faaliyetlerini Peugeot Otomotiv Pazarlama A.Ş. şirketi üzerinden yürütmektedir.");
        otomobilBilgi.add("Renault S.A., Fransız araç üreticisi. Otomobil, kamyon, traktör, tank, tren, uçak, motosiklet, bisiklet, otobüs gibi birçok farklı türde araç üretmektedir. Türkiye'de Bursa'da kurulu bulunan Oyak-Renault ortaklığı (%51) ile yatırımı vardır. Ayrıca Nissan otomobil markasının motorlarını üretmektedir. ");
        otomobilBilgi.add("Citroën, Fransız ana otomobil üreticisi olan, PSA Peugeot Citroën grubunun 1976'den beri üyesi bir otomobil üreticisidir.");
        otomobilBilgi.add("Michelin (tam adı Fransızca: SCA Compagnie Générale des Établissements Michelin) Fransa'nın Auvergne bölgesinde bulunan Clermont-Ferrand şehrinde merkezi bulunan ve asıl olarak araç lastiği üreten bir şirkettir. 28 Mayıs 1888 yılında Édouard ve André Michelin kardeşler tarafından kurulmuştur. Michelin markası dışında B.F.Goodrich, Taurus, Kormoran ve Uniroyal (Kuzey Amerika'da) markalarına da sahiptir.");
        otomobilBilgi.add("Açılımı İngilizce’de “The United States Rubber Company”  olan Uniroyal, Amerikalı bir lastik ve sentetik kauçukla ilgili ürünler üreticisidir. Askeri kullanım için çeşitli ürünler üretmenin yanında devlete ait yüklenici tarafından işletilen tesislerde mühimmat, patlayıcılar ve operasyon ve bakım faaliyetleri sahalarında ürünler üretmektedir. 1892'de Connecticut, Naugatuck'ta kuruldu. 1990 yılında Uniroyal, Fransız lastik üreticisi Michelin tarafından satın alındı ve ayrı bir işletme olarak varlığını sona erdirdi.");
        otomobilBilgi.add("Recamic, Michelin Grubunun kaplama markasıdır. Recamic, lastik kaplama teknolojisinin güvenliğini ve performansını geliştirir.");
        otomobilBilgi.add("Dacia, 1966 yılında Romanya hükümetinin açmış olduğu ihale sonucu kurulmuş, adını Romanya topraklarının eski adı olan Daçya’dan alan, 1999 yılında Renault bünyesine geçen Romanyalı otomobil üreticisi. Ayrıca Renault'nun Romanya'daki markasıdır.");



        List<String> bebekGiyim = new ArrayList<>();

        bebekGiyim.add("Bledina");
        bebekGiyim.add("Majorette");
        bebekGiyim.add("DPAM");
        bebekGiyim.add("Petit Bateau");

        List<String>  bebekGiyimBilgi = new ArrayList<>();

        bebekGiyimBilgi.add("Blédina, Danone grubunun bir şirketi olup, bir bebek maması markasıdır. ");
        bebekGiyimBilgi.add("Majorette , özellikle 1:64 ölçekli olmak üzere çoğunlukla küçük pres döküm arabalar ve diğer inşaat ve askeri araçlar üreten Fransız bir oyuncak üreticisidir . Bu normal bir 2,5 ila 3 inç boyutudur, bu nedenle Majorette bazen Fransa'nın Kibrit Kutusu Oyuncakları olarak adlandırılır. Geleneksel olarak, üretim Lyon'un kentsel bölgesinde merkezlenmişti, ancak modeller artık Tayland'da yapılıyor.");
        bebekGiyimBilgi.add("Du Pareil au Même sık kullanılan adıyla DPAM, 1986 yılında Simon Benharrous tarafından kurulmuş Fransız bir çocuk-giyim mağaza zinciridir. 0-14 yaş aralığındaki çocukları kapsayan ayakkabı, giysi, aksesuar vb. ürünler satmaktadır. ");
        bebekGiyimBilgi.add("Petit Bateau, 1920'de kurulan, ancak kökeni Troyes'de 1893'e kadar uzanan çocuklar için bir Fransız giyim markasıdır. 1988'den beri Rocher Grubu'na entegre olan Petit Bateau, 2000'li yıllarda yetişkin koleksiyonunun lansmanı sayesinde nesiller arası hale gelen bir çocuk markasıdır.");



        List<String> kozmetik = new ArrayList<>();

        kozmetik.add("L'Oreal");
        kozmetik.add("La Roche Posay");
        kozmetik.add("Biotherm");
        kozmetik.add("Christian Dior");
        kozmetik.add("Clarins");
        kozmetik.add("Vichy");
        kozmetik.add("Garnier");

        List<String> kozmetikBilgi = new ArrayList<>();

        kozmetikBilgi.add("L'Oréal Group, Dünya'nın en büyük kozmetik ve güzellik şirketidir. Fransa'da Paris banliyösünde Paris'e kayıtlı ofisi ve merkez ofisi ile Clichy, Hauts-de-Seine'de bu kozmetik alanında faaliyetlerini geliştirmekte olup; saç rengi, cilt bakımı, güneşten korunma, makyaj, parfüm ve saç bakımı üzerine konsantre ve ilaç alanlarında aktif ve ABD'de üst nanoteknoloji'ye en çok yatırım yapan ve bu konuda en çok patenti elinde bulunduran bir şirkettir. Kurucusu 1907 yılında \"halesi\" adında bir saç boyası formülü geliştiren Eugène Schueller adında genç bir Fransız kimyagerdi. L'Oréal; Clarisonic, Drakkar Noir, Kérastase, L'Oréal Professionnel, Lancôme, Magic Shave, NYX Cosmetics, Pureology, Redken, Shu Uemura, Urban Decay, Kiehl's, Maybelline, Vichy, Garnier, Helena Rubinstein, Ralph Lauren, Giorgio Armani Parfums, Biotherm, Cacharel, CeraVe, SkinCeuticals, La Roche-Posay gibi markaları da bünyesinde bulunur."+"\n"+"\n"+"\n"+"\n"+"\n");
        kozmetikBilgi.add("La Roche Posay, yağlı ve akneli ciltler, hassas ve kızarıklığa eğilimli ciltler, leke karşıtı, makyaj temizleme ve cilt temizleme suları, nemlendirici, güneş ürünleri ve kuru ciltler için üretilen dermokozmetik bakım ürünleri markasıdır.\n" +
                "La Roche Posay markası Fransa merkezli Loreal grubu bünyesindedir.\n");
        kozmetikBilgi.add("Biotherm, Lüks Ürünler bölümü altında L'Oréal'e ait bir Fransız cilt bakım şirketidir. Biotherm, 1970 yılında L'Oréal tarafından satın alınmıştır.");
        kozmetikBilgi.add("Christian Dior SE, (Dior olarak bilinir), Avrupa'nın en büyük lüks mallar grubu olan LVMH'ye başkanlık eden Fransız iş adamı Bernard Arnault tarafından yönetilen bir Avrupa moda şirketidir. Dior, LVMH bünyesinde% 42.36 pay ve% 59.01 oy hakkına sahiptir.\n" +
                "1948 yılında tasarımcısı Christian Dior tarafından kurulan şirket, bugün tanınmış haute-couture (Christian Dior Couture bölümünün altında) konfeksiyon, hazır giyim, deri ürünleri, moda aksesuarları, ayakkabı, takı, saat, parfüm, makyaj ve cilt bakım ürünleri tasarlayıp satar. Christian Dior etiketi, kadınlara yönelik teklifleri büyük ölçüde korurken, erkekler için Dior Homme bölümünü ve çocuk giyiminde Dior etiketi kullanıyor. Ürünler, dünya çapındaki perakende mağazalarının portföyünde ve çevrimiçi mağazasında satılmaktadır. \n"+"\n"+"\n"+"\n"+"\n");
        kozmetikBilgi.add("Clarins Group, ticari ismiyle Clarins, Fransız lüks cilt bakımı, kozmetik ve parfüm şirketidir. Şirket bu  sahada ürünler üretir ve genellikle üst düzey alışveriş merkezleri ve seçkin eczaneler aracılığıyla satışlar yapar. ");
        kozmetikBilgi.add("Vichy L'Oréal'in Aktif Kozmetik Bölümü'nün sahibi olduğu cilt bakımı, vücut bakımı, makyaj ve yaşlanma karşıtı ürünlerden oluşan üstün bir markadır. Fransa'nın Vichy kentinin kaplıcalarından termal kaplıca suyu, formülasyonlarında kullanılan tek su kaynağıdır. Şirket 1931 yılında Georges Guérin tarafından kuruldu. Marka 1950'lerde gelişmeye başladı ve 1955'te L'Oréal grubu tarafından satın alındı. Şirket L'Oreal grubundaki, diğeri La Roche-Posay olmak üzere iki 'aktif kozmetik' markasından biridir. Vichy, Avrupa'da en popüler olanıdır ve şirket, 'Dermablend'in temelleri ve kapatıcıları için bilinir. Vichy, Avrupa cilt bakım pazarında en büyük markalardan biridir. Önde gelen hatlardan bazıları şunlardır: LiftActiv (Anti-Aging), Neovadiol, Normaderm, Dercos, Aqualia Thermal, Capital Soleil, Essentielles (daha ucuz ürünlerin temel hattı), Vichy Homme (erkek tıraş ve temizleme ürünleri). Vichy'nin sloganı \"Sağlık güzeldir\" şeklindedir."+"\n"+"\n"+"\n"+"\n");
        kozmetikBilgi.add("Garnier Fransız kozmetik şirketi L'Oréal'ın bir kozmetik markasıdır. Saç bakımı ve cilt bakım ürünleri üretir.");



        List<String> parfum = new ArrayList<>();

        parfum.add("Chanel");
        parfum.add("Christian Dior");
        parfum.add("Clarins");
        parfum.add("Drakkar Noir");
        parfum.add("Fahrenheit");
        parfum.add("Lancome");

        List<String> parfumBilgi = new ArrayList<>();

        parfumBilgi.add("Chanel 1909 yılında moda tasarımcısı Coco Chanel olarak bilinen ünlü Fransız modacı Gabrielle Bonheur tarafından kurulan hazır giyim, parfüm, saat, çanta ve kozmetik gibi ürünler üreten bir markadır. Yıllar içinde haute couture(özel dikim giysi), parfüm, saat, hazır giyim, çanta ve kozmetik gibi alanlarda uzmanlaşmış ve dünyaca ünlenmiş lüks bir markaya dönüşmüştür.");
        parfumBilgi.add("Christion Dior (Dior olarak bilinir), Avrupa'nın en büyük lüks mallar grubu olan LVMH'ye başkanlık eden Fransız iş adamı Bernard Arnault tarafından yönetilen bir Avrupa moda şirketidir. Dior, LVMH bünyesinde% 42.36 pay ve% 59.01 oy hakkına sahiptir.\n" +
                "1948 yılında tasarımcısı Christian Dior tarafından kurulan şirket, bugün tanınmış haute-couture (Christian Dior Couture bölümünün altında) konfeksiyon hazır giyim, deri ürünleri, moda aksesuarları, ayakkabı, takı, saatler, parfüm, makyaj ve cilt bakım ürünleri tasarlayıp satıyor. Christian Dior etiketi, kadınlara yönelik teklifleri büyük ölçüde korurken, erkekler için Dior Homme bölümünü ve çocuk giyiminde Dior etiketi kullanıyor. Ürünler, dünya çapındaki perakende mağazalarının portföyünde ve çevrimiçi mağazasında satılmaktadır.\n"+"\n"+"\n"+"\n"+"\n");
        parfumBilgi.add("Clarins Group, ticari ismiyle Clarins, Fransız lüks cilt bakımı, kozmetik ve parfüm şirketidir. Şirket bu  sahada ürünler üretir ve genellikle üst düzey alışveriş merkezleri ve seçkin eczaneler aracılığıyla satışlar yapar. ");
        parfumBilgi.add("Drakkar Noir, 1982 yılında Guy Laroche tarafından oluşturulan parfüm markası L’Oréal Group lisansı altında üretilmektedir.");
        parfumBilgi.add("Fahrenheit, 1988 yılından itibaren Christian Dior SE tarafından üretilen erkek parfüm markasıdır. Markanın ayrıca deodorant, traş losyonu gibi bir dizi ürüne sahiptir.");
        parfumBilgi.add("Lancôme, ürünleri uluslararası olarak dağıtan bir Fransız lüks parfüm ve kozmetik evidir. Lancôme, yüksek fiyatlarla lüks cilt bakımı, parfümler ve makyaj sunan L’Oréal Lüks Ürünler Bölümü'nün bir parçasıdır.");



        List<String> ciltBakim = new ArrayList<>();

        ciltBakim.add("Clarins");
        ciltBakim.add("Guerlain");

        List<String> ciltBakimBilgi = new ArrayList<>();

        ciltBakimBilgi.add("Clarins Group, ticari ismiyle Clarins, Fransız lüks cilt bakımı, kozmetik ve parfüm şirketidir. Şirket bu  sahada ürünler üretir ve genellikle üst düzey alışveriş merkezleri ve seçkin eczaneler aracılığıyla satışlar yapar. ");
        ciltBakimBilgi.add("Guerlain, Dünya’nın en eski parfüm, kozmetik ve cilt bakımı şirketlerinden birisidir. Şirket, parfümcü Pierre-François Pascal Guerlain tarafından 1828'de Paris'te kuruldu. Fransız çokuluslu şirket LVMH tarafından satın alındığı 1994 yılına kadar Guerlain ailesi tarafından işletildi. Ana mağazası Paris'teki 68, Avenue des Champs-Elysées'dir.");



        List<String> insaat = new ArrayList<>();

        insaat.add("Onduline Avrasya");
        insaat.add("Lafarge");
        insaat.add("Chryso");
        insaat.add("Weber Markem");

        List<String> insaatBilgi = new ArrayList<>();

        insaatBilgi.add("1944'te kurulan Onduline Group, hafif çatı kaplama ve kiremit altı levhalarında Dünya’nın ilk üreticisidir. Selülo-mineral esaslı çatı ve cephe kaplama levhaları üretiminde Dünya’da tanınan Onduline Group, Türkiye’de 1994 yılına kadar Onduline lisansı altında üretim yaparken, aynı yıl doğrudan yatırım yapma kararı almış ve Onduline Avrasya şirketi Ağustos 1994’te %100 yabancı sermayeli bir şirket olarak Onduline Group’un Türkiye’deki iştiraki olarak kurulmuştur.");
        insaatBilgi.add("Lafarge, merkezi Fransa'nın başkenti Paris'te bulunan ve inşaat malzemeleri üreten şirket. Çimento, agrega ve beton, çatı malzemeleri, alçı ve alçı levha olmak üzere dört ana faaliyet alanı bulunmaktadır. Kuruluşu 1833'e kadar uzanan Lafarge Grubu'nun bugün 68’i aşkın ülkede fabrikası bulunmaktadır.");
        insaatBilgi.add("Chryso, 1942 yılında kurulan Chryso SAS bünyesinde üretilen çimento katkı maddeleri markasıdır. Hazır beton, çimento, beton kimyasalları üretmektedir. 22 yan kuruluş, dağıtıcı ve lisans ağı aracılığıyla 100 ülkede faaliyet göstermektedir. Türkiye ile birlikte 14 ülkede doğrudan üretim tesislerine sahiptir. Türkiye’de 1994 yılından beri Chryso-Kat Katkı Malzemeleri San. ve Tic. A.Ş. olarak faaliyet göstermektedir. Chryso SAS Fransa merkezlidir.");
        insaatBilgi.add("Weber Markem, 1665 yılında kurulan Saint-Gobain bünyesinde bulunan endüstriyel harçlar, alçı, izolasyon, boru, dış cephe ürünleri, düz cam, yüksek performans ürünleri, aşındırıcılar, plastik ve cam elyaf gibi ürünler üreten bir Fransız markasıdır. Weber, 1927 yılından beri Fransa merkezli Saint-Gobain Grubu’nun yapı ürünleri iş kolunda faaliyet göstermektedir. Weber, Türkiye’de 1998 yılından beri faaliyet gösteriyor. Seramik yapıştırıcsı ve derz dolgu malzemesi, dış cephe ve ısı yalıtımı, su yalıtımı ve teknik harçlar, zemin sistemi ve iç cephe boyası ürünleriyle, Türkiye’de 6 fabrika, 8 bölge müdürlüğü ve yaygın bir bayi ağı bulunmaktadır."+"\n"+"\n"+"\n"+"\n");




        List<String> seyahat = new ArrayList<>();

        seyahat.add("Air France");


        List<String> seyahatBilgi = new ArrayList<>();

        seyahatBilgi.add("Société Air France, S.A. ya da kısaca Air France Fransa'nın bayrak taşıyıcı havayolu şirketi. Merkezi Paris'in kuzeyinde Tremblay-en-France'da bulunan şirket, Air France-KLM ortaklığı altında ve SkyTeam kurucu üyesi olarak faaliyet göstermektedir. ");




        List<String> cakmak = new ArrayList<>();

        cakmak.add("BIC");
        cakmak.add("Cartier");

        List<String> cakmakBilgi = new ArrayList<>();

        cakmakBilgi.add("Société Bic Clichy (Hauts-de-Seine), Fransa merkezli şirkettir. 1945 yılında kurulmuştur. Çakmak, mıknatıs, tükenmez kalem, tıraş bıçağı, jilet ve su sporları gereçleri gibi uzun ömürlü kullanılmayan ürünler üretmesiyle tanınmaktadır. Şirketin ilk ürünü Bic Cristal'dır.");
        cakmakBilgi.add("Cartier International SNC, ya da daha kısaca Cartier, lüks mücevher, saat, çakmak, parfüm gibi ürünler üreten, tasarlayan ve satışını yapan bir Fransız şirketidir. ");



        List<String> spor = new ArrayList<>();

        spor.add("Le coq sportif");
        spor.add("Decathlon");

        List<String> sporBilgi = new ArrayList<>();

        sporBilgi.add("Le Coq Sportif; ayakkabı, şort ve tişört başta olmak üzere çeşitli spor malzemeleri üreten Fransız şirket. 1948'de Émile Camuset tarafından kuruldu.[1] Kurucu Camuset, halen tasarımlarına devam etmektedir.[2] Şirketin ismi ve logosu, Fransa'nın ulusal sembollerinden biri olan Gal horozundan gelmektedir. Le Coq Sportif, Sheffield United, Wolverhampton Wanderers, Carlisle United ve Hibernian gibi bazı futbol kulüpleriyle sponsorluk anlaşmalarına sahiptir. Ek olarak Quick Step-Innergetic ve Team Milram bisiklet takımlarının da sponsorluğunu yapmaktadır.");
        sporBilgi.add("Decathlon, 1976 yılında Fransa'da kurulan, birçok farklı markanın spor ekipmanlarının bir arada bulunduğu uluslararası mağaza zinciridir. 2015 yılı verilerine göre dünya genelinde 70.000 çalışanı bulunmaktadır. Türkiye’de bugün 38 mağazası bulunmaktadır. ");




        List<String> akaryakit = new ArrayList<>();

        akaryakit.add("Total");
        akaryakit.add("Elf");

        List<String> akaryakitBilgi = new ArrayList<>();

        akaryakitBilgi.add("Total SA, Fransa merkezli çok uluslu petrol şirketi. \"Süper büyük\" 6 petrol şirketinden biridir. Şirketin merkezi Paris yakınlarındaki Tour Total adlı binada bulunmaktadır.");
        akaryakitBilgi.add("Elf, 1924 yılında Ernest Mercier tarafından kurulan Total S.A. bünyesindeki binek araç motor yağı markasıdır. Total S.A. Fransa merkezlidir.");



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

        ilacBilgi.add("Servier ya da diğer ismiyle Laboratoires Servier, merkezi Fransa’da bulunan uluslararası bir ilaç şirketidir. Servier, önde gelen Fransız bağımsız ilaç şirketi ve dünya çapında ikinci en büyük Fransız ilaç şirketidir. 149 ülkede şubeleri vardır ve satışlarının% 82'sini Fransa dışında gerçekleştirmektedir.");
        ilacBilgi.add("Guerbet, tıbbi görüntülemede kullanılan kontrast maddelerinin Fransa merkezli bir üreticisidir . Şirket, 1901'de ilk iyotlu X-ışını kontrast maddesi olan Lipiodol'ü keşfeden Marcel Guerbet'in oğlu André Guerbet tarafından 1926'da kuruldu. 2017 yılında Guerbet'in gelirleri 807,1 milyon € idi. Dünya çapında 2.700'den fazla kişiyi istihdam etmektedir ve Fransa, İrlanda, Kanada, Amerika Birleşik Devletleri ve Brezilya'da üretim tesisleri bulunmaktadır. Şirketin merkezi Paris'in bir banliyösü olan Villepinte'de bulunmaktadır ve hisseleri CAC Small hisse senedi endeksine dahildir"+"\n"+"\n"+"\n");
        ilacBilgi.add("Pierre Fabre Medicament ya da Laboratoires Pierre Fabre, Fransız çok uluslu bir ilaç ve kozmetik şirketidir. Şirketin 2012 yılında konsolide cirosu 1.978 milyar Euro'dur. Merkezi Castres , Midi-Pyrénées, Fransa'da bulunmaktadır.");
        ilacBilgi.add("Allegra, Fexofenadine içerikli bir allerji ilacı. Fransa kaynaklıdır.");
        ilacBilgi.add("Benzac AC Jel %5, bakterilerin cilt üzerinde gelişimini engelleyerek çalışan antibakteriyel bir ilaçtır. Akne, sivilce tedavisinde kullanılır. Menşei Fransa'dır.");
        ilacBilgi.add("Benzagel (benzoil peroksit jel), sivilceleri tedavi etmek için kullanılan hafif bir kuruma etkisine de sahip topikal (cilt için) bir antibakteriyel maddedir.");
        ilacBilgi.add("Benzamycin topikal jel etken maddesi Benzoil Peroksit ve Aktif Eritromisin, olan jel kıvamında, antibiyotik bir topikal üründür. Sivilce, iltihaplı sivilce ve ergenlik dönemi sivilceleri (akne vulgaris) tedavisinde etkin şekilde kullanılır.");
        ilacBilgi.add("Novalgin, ağrı kesici, ateş düşürücü ve kasılma çözücü etkilere sahip bir ilaçtır. Fransız kaynaklıdır.");




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

        endustriyelBilgi.add("Groupe Schneider ya da bilinen adıyla Schneider Electric, 1836 yılında Eugene Schneider tarafından kurulan Schneider Electric SE bünyesindeki enerji yönetimi, otomasyon çözümleri, kapsayan donanım ve yazılım hizmetleri markasıdır. Schneider Electric SE Fransa merkezli çokuluslu bir şirkettir. Bünyesindeki şirketler: 7-Technologies, ABS EMEA, AEM S.A., APC, APW President Systems, ASCO, AST, Modular, Andover Controls, Andromeda Telematics, Areva T&D, Aveva Group, BEI Technologies, Berger – Positec, CDI Power, Citect, Clipsal, Conzerv Systems, Crouzet Automatismes, Digilink, Digital Electronics Corporation, Eliwell, Federal Pioneer Limited, InStep Software, Invensys, Juno Lighting, Kavlico, Larsen & Toubro, Luminous, M&C Energy Group, MGE UPS Systems, Meher Capacitors, Merlin Gerin, Merten, Modicon, Nu-Lec Industries, OVA Bargellini, PDL, Pelco, Power Measurement, SCADAgroup, SolveIT Software, Square D, Summit Energy, TAC, Telemecanique, Telvent, Viconics Electronics, Viridity, Zicom Security Systems."+"\n"+"\n"+"\n"+"\n"+"\n");
        endustriyelBilgi.add("Metesan Lexel, 1981 yılında Necdet ve Cavit Mete kardeşler tarafından kurulan Metesan Elektrik Malzemeleri San. ve Tic. A.Ş. bünyesindeki elektrik malzemeleri markasıdır. Metesan Lexel, Schneider Electric tarafından satın alındı. Fransa merkezli Schneider Electric 160 yılı aşkın bir süredir Türkiye’de faaliyet göstermektedir. Schneider Electric’in İzmir, Kocaeli ve Manisa’da üç üretim tesisi bulunmaktadır.");
        endustriyelBilgi.add("Legrand Elektrik, 1860 yılında Legrand ailesi tarafından kurulan trafo, şalt ürünleri, dağıtım panoları, kesintisiz güç kaynağı, kablo taşıma sistemleri, anahtar, priz, ev otomasyon sistemleri, acil durum aydınlatma ürünleri, yapısal kablolama ve aydınlatma kontrol sistemleri markasıdır. Legrand 180 ülkede faaliyet gösteren Fransa merkezli bir şirkettir. Türkiye pazarına 1990 yılında Bufer Elektrik’i satın alarak Legrand Elektrik Sanayi A.Ş. adıyla girdi. 1992 yılında Gebze’de fabrika kurdu.");
        endustriyelBilgi.add("Duval Messien, yıldırımdan korunma ve topraklama alanlarındaki faaliyetlerde uzmanlaşmış bir elektrik firmasıdır.");
        endustriyelBilgi.add("Franklin France şirketi, yıldırımdan korunmak için paratoner üretimi yapan bir elektrik firmasıdır.");
        endustriyelBilgi.add("Merlin Gerin, 1920 yılında, Paul-Louis Merlin ve Gaston Gerin tarafından kurulan elektrik ürünleri markasıdır. Schneider Group tarafından satın alınmıştır.");
        endustriyelBilgi.add("Telemecanique Sensors, 1924 yılında kurulan Telemecanique Sensors Industries SAS bünyesindeki sensörler ve sensör teknolojileri markasıdır.\n" +
                "Limit anahtarları, basınç sensörleri, foto-elektrik ve yakınlık sensörleri dahil, birçok PLC imalatının yanı sıra endüstriyel kodlayıcılarla sıkı bir şekilde entegre edilmiş son teknoloji RFID sistemler üretmektedir.\n" +
                "Telemecanique Sensors Industries SAS, 20’den fazla ülkede bulunan Fransa merkezli şirkettir.\n");
        endustriyelBilgi.add("Square D, 1902 tarihinde Bryson Dexter Horton ve James B. McCarthy tarafından kurulan elektrikli ekipman markasıdır. 1991 yılında Schneider Electric SE grubuyla entegre olmuştur.");




        List<String> denizcilik = new ArrayList<>();

        denizcilik.add("Beneteau");

        List<String> denizcilikBilgi = new ArrayList<>();

        denizcilikBilgi.add("Beneteau veya Bénéteau, Fransa ve Amerika Birleşik Devletleri'nde üretim tesisleri bulunan Fransız yelkenli ve motorlu tekne üreticisi markasıdır. Şirket, dünya çapında önemli bir pazara hâkim olan büyük ve tanınmış bir tekne üreticisidir. Beneteau Group, ayrıca 1995 yılından beri diğer prestijli üreticiler olan Jeanneau ile Lagoon’u bünyesinde bulundurmaktadır. ");



        List<String> finans = new ArrayList<>();

        finans.add("Societe General Bankası");
        finans.add("BNP Paribas");
        finans.add("TEB, Türkiye Ekonomi Bankası");

        List<String> finansBilgi = new ArrayList<>();

        finansBilgi.add("Société Générale SA, genellikle takma adı \" SocGen \", merkezi Paris'te bulunan bir Fransız çokuluslu yatırım bankası ve finansal hizmetler şirketidir. Şirket evrensel bir bankadır ve Küresel İşlem Bankacılığı, Uluslararası Bireysel Bankacılık, Finansal Hizmetler, Kurumsal ve Yatırım Bankacılığı, Özel Bankacılık, Varlık Yönetimi ve Menkul Kıymet Hizmetlerini destekleyen bölümleri vardır. Société Générale, toplam varlıklar açısından Fransa'nın üçüncü en büyük, Avrupa’nın ise yedinci en büyük bankasıdır. Şirket, Euro Stoxx 50 borsa endeksinin bir parçasıdır. BNP Paribas ve Crédit Lyonnais ile birlikte Fransız bankacılığının Trois Vieilles'lerinden (\"Eski Üçlü\") biri olarak bilinir."+"\n"+"\n"+"\n"+"\n");
        finansBilgi.add("BNP Paribas, 2000 yılında Banque Nationale de Paris (BNP) ve Paribas’ın birleşmesiyle kurulmuştur. 75 ülkede varlığı bulunan uluslararası bir bankacılık grubudur. Fransa Paris merkezli BNP Paribas S.A., 2009 yılında, Belçika bankası Fortis’in yüzde 75’lik hissesini satın alarak Avro bölgesinin en büyük mevduata sahip bankası olmuştur. 2017 yılında uluslararası alanda 8. sırada yer aldı.");
        finansBilgi.add("Türkiye Ekonomi Bankası ya da TEB, Türk Ekonomi Bankası (TEB), 1927 yılında Kocaeli Halk Bankası T. A. Ş. olarak kurulan özel sektör ticari bankalarından biridir. Bankacılık hizmetlerinin yanı sıra grup şirketleri ve iştirakleri vasıtasıyla da faktoring, tüketici finasmanı finansal kiralama, filo kiralama, portföy yönetimini de içeren geniş bir finansal ürün ve hizmet yelpazesini müşterilerine sunmaktadır. 2005 yılında Fransa Paris merkezli BNP Paribas S.A. ile Çolakoğlu Grubu arasında gerçekleşen ortaklık sonucunda BNP Paribas TEB’in çoğunluk hissedarı olmuştur."+"\n"+"\n"+"\n");

        List<String> hotel = new ArrayList<>();
        hotel.add("Ibis");
        hotel.add("Sofitel");
        hotel.add("Accor");
        hotel.add("Novotel");

        List<String> hotelBilgi = new ArrayList<>();
        hotelBilgi.add("Ibis, Accor'a ait bir otel markasıdır. 1974'te kurulan Ibis, 2011'de Ibis Styles ve Ibis Budget'ın piyasaya sürülmesiyle Accor'un ekonomi mega markası oldu. Aralık 2019 itibarıyla, Ibis markası altında 1.218 otel (Styles ve Budget oteller hariç), 67 ülkede toplam 155.678 oda bulunmaktadır.");
        hotelBilgi.add("Sofitel Hotels & Resorts, Paris, Fransa merkezli ve 1980'den beri Accor'a ait bir lüks oteller zinciridir.\n" +
                "\n" +
                "1964 yılında Fransa'da kurulan Sofitel, dünya çapında hızla gelişerek 200'den fazla tesise ulaştı. 2008 yılında Sofitel sadece lüks otel markası olmuştur.");
        hotelBilgi.add("Accor S.A. otellere, tatil köylerine ve tatil mülklerine sahip olan, bunları yöneten ve franchise veren bir Fransız çok uluslu otelcilik şirketidir. Avrupa'nın en büyük ve dünyanın altıncı en büyük otelcilik şirketidir.\n" +
                "\n" +
                "Accor, dünya çapında 4.800'den fazla otel ve 280.000 çalışanıyla 100 ülkede faaliyet göstermektedir. Toplam kapasitesi yaklaşık 704.000 odadır. Accor, lüks segment (Raffles, Fairmont ve Sofitel dahil), premium segment (MGallery, Pullman ve Swissôtel dahil), orta ölçekli segment (Novotel, Mercure ve Adagio dahil) gibi konuk ağırlamanın her segmentini kapsayan markaların sahibidir ve bunları işletmektedir. ekonomi segmenti (ibis ve hotelF1'i içerir). Accor ayrıca onefinestay, D-Edge, ResDiary, John Paul ve Potel & Chabot gibi dijital konaklama ve etkinlik organizasyonunda uzmanlaşmış şirketlere de sahiptir.\n" +
                "\n" +
                "Şirketin genel merkezi Fransa'nın Issy-les-Moulineaux kentinde bulunmaktadır ve Paris borsasındaki CAC 40 endeksinin bir bileşenidir."+"\n"+"\n"+"\n"+"\n"+"\n");
        hotelBilgi.add("Novotel, modern, doğal ve sezgisel tasarıma odaklanmış orta ölçekli bir otel markasıdır. 1967'de Fransa'da kurulan şirket, 1983'te Accor grubu haline geldi ve Novotel, Accor'un çoklu marka stratejisinin temel markası olarak kaldı. 2018 verilerine göre Novotel, 59 ülkede 492 oteli yönetmektedir.");


        List<String> telekomunikasyon = new ArrayList<>();
        telekomunikasyon.add("Orange");

        List<String> telekomunikasyonBilgi = new ArrayList<>();
        telekomunikasyonBilgi.add("Orange S.A., bir Fransız çokuluslu telekomünikasyon şirketidir. Dünya çapında 266 milyon müşteriye sahiptir ve Fransa'da 89.000, başka yerlerde 59.000 kişiyi istihdam etmektedir. Vodafone, Telefónica ve VEON'dan sonra dünyanın en büyük onuncu ve Avrupa'nın dördüncü en büyük mobil şebeke operatörüdür. 2015 yılında grubun cirosu 40 milyar Euro'dur. Şirketin merkezi Paris'in 15. bölgesinde yer almaktadır. Şu anki CEO Stéphane Richard'dır. Şirket, Euro Stoxx 50 borsa endeksinin bir bileşenidir.\n" +
                "\n" +
                "Orange, 2006 yılından bu yana şirketin mobil, sabit hat, internet ve IPTV hizmetleri için ana markası olmuştur. 1990'ların başında Hutchison Whampoa'nın Microtel Communications'ın kontrol hissesini satın alması ve onu \"Orange\" olarak yeniden markalamasıyla 1994 yılında ortaya çıkmıştır. 1999'da Mannesmann'ın bir yan kuruluşu oldu ve 2000 yılında France Télécom tarafından satın alındı. Şirket, 1 Temmuz 2013'te Orange olarak yeniden markalandı."+"\n"+"\n"+"\n"+"\n"+"\n");

        List<String> bilisim = new ArrayList<>();
        bilisim.add("Capgemini");

        List<String> bilisimBilgi = new ArrayList<>();
        bilisimBilgi.add("Capgemini SE, Fransa merkezli bir bilişim teknolojileri hizmeti ve danışmanlık şirketidir. Şirket, 1 Ekim 1967 tarihinde Serge Kampf tarafından kurulmuş olup danışmanlık, teknoloji, profesyonel ve dış kaynak hizmetleri sunmaktadır. Şirketin merkezi Paris'in 17. semtinde yer almaktadır. \n" +
                "Capgemini, Euronext'te işlem görmekte olup şirketin 50'den fazla ülkede 200,000'den fazla çalışanı bulunmaktadır.\n");

        List<String> sigara = new ArrayList<>();
        sigara.add("Gauloises");

        List<String> sigaraBilgi = new ArrayList<>();
        sigaraBilgi.add("Gauloises, Fransızca \"Galyalı kadınlar\" anlamına gelir(Fransızcada \"sigara\" dişildir). Fransız üretimi bir sigara markasıdır. Çoğu ülkede Ocak 2008'de Altadis'i satın almasının ardından Imperial Tobacco şirketi tarafından üretildi, ancak Almanya'da Reemtsma tarafından üretilip satıldı.");


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

        searchView.setQueryHint("Ürün Ara");

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

                        StyleableToast.makeText(MainActivity.this,"Ürünle alakalı detaylı bilgileri ilgili kategori altından ürün ismine tıklayarak alabilirsiniz.",R.style.exampleToast).show();
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