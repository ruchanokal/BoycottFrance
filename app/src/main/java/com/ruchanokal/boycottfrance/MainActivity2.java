package com.ruchanokal.boycottfrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.muddzdev.styleabletoast.StyleableToast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    AdView mAdView;
    private InterstitialAd mInterstitialAd;

    ArrayList<String> categoryList;
    HashMap<String,ArrayList<String>> brandList;
    HashMap<String,ArrayList<String>> detailList;
    ExpandableListView expandableListView2;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ExpandableAdapter2 expandableAdapter2;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Main2 Banner
        //ca-app-pub-5016889744069609/8287792203

        //Main2 Interstitial
        //ca-app-pub-5016889744069609/6156452888

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);

        expandableListView2 = findViewById(R.id.expandableListView2);
        listView = findViewById(R.id.listView);

        categoryList = new ArrayList<>();
        brandList = new HashMap<>();
        detailList = new HashMap<>();

        showlist();

        expandableAdapter2 = new ExpandableAdapter2(categoryList,brandList,MainActivity2.this);
        expandableListView2.setAdapter(expandableAdapter2);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5016889744069609/6156452888");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());



    }



    private void showlist() {

        categoryList.add("Kitchen");
        categoryList.add("Insurance");
        categoryList.add("Hair Care");
        categoryList.add("Clothing");
        categoryList.add("Supermarket");
        categoryList.add("Dairy Products");
        categoryList.add("Bottled Water");
        categoryList.add("Automobile Industry");
        categoryList.add("Infant Formula/Clothing/Toys");
        categoryList.add("Cosmetics");
        categoryList.add("Perfume");
        categoryList.add("Skin Care");
        categoryList.add("Construction");
        categoryList.add("Travel Industry");
        categoryList.add("Lighter");
        categoryList.add("Sports Equipment");
        categoryList.add("Fuel Oil");
        categoryList.add("Pharmaceutical Company");
        categoryList.add("Industrial / Electricity");
        categoryList.add("Marine");
        categoryList.add("Finance");
        categoryList.add("Hotel");
        categoryList.add("Telecommunication");
        categoryList.add("Informatics");
        categoryList.add("Cigarette");
        categoryList.add("Food Processing");


        ArrayList<String> kitchen = new ArrayList<>();
        kitchen.add("Tefal");
        kitchen.add("T-fal");
        kitchen.add("Moulinex");
        kitchen.add("Groupe SEB");

        ArrayList<String> kitchenInfo = new ArrayList<>();
        kitchenInfo.add("Tefal, is a French cookware and small appliance manufacturer owned by Groupe SEB (the world's largest manufacturer of cookware). Its name is a portmanteau of the words TEFlon and ALuminium. The company is known for creating the non-stick cookware category and for offering frying equipment with a low requirement of fat or oils. In some countries like the United States, Tefal is also marketed as T-Fal. Tefal also manufactures linen care products such as Steam Irons and Garment Steamers.");
        kitchenInfo.add("T-Fal, is a French cookware and small appliance manufacturer owned by Groupe SEB (the world's largest manufacturer of cookware). In some countries like the United States, Tefal is also marketed as T-Fal.");
        kitchenInfo.add("Moulinex is a Groupe SEB brand along with Rowenta, Calor, All-Clad, Lagostina, Krups, and Tefal, all household products brands. The company designed and produced the Mouli grater. The company was founded by Jean Mantelet who in 1932 invented the Moulin-Légumes, a hand-crank food mill for puréeing vegetables. The design is considered an early forerunner to the modern food processor.\n" +
                "\n" +
                "Mantelet's business plan was to \"choose an industrial sector with a future, use mass production, keep prices low, manufacture in regions where salaries are low, reduce costs, reduce dependencies on suppliers, and focus on research and publicity\". Between 1929 and 1953 Mantelet applied for 93 patents."+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        kitchenInfo.add("Groupe SEB (Société d'Emboutissage de Bourgogne) is a large French consortium that produces small appliances, and it's the world's largest manufacturer of cookware. Notable brand names associated with Groupe SEB include All-Clad, IMUSA , Krups, Moulinex, Rowenta, Tefal (including OBH Nordica) and WMF Group. According to the Groupe SEB website, they have faced considerable competition from low-price Chinese competitors, but have managed to maintain a constant sales level. A large proportion of their product lines are now manufactured in China. Its headquarters are in Ecully, a Lyon suburb.\n" +
                "\n"+"\n"+"\n"+"\n");


        ArrayList<String> insurance = new ArrayList<>();
        insurance.add("Axa Insurance");
        insurance.add("Groupama International");
        insurance.add("Equitable Life");

        ArrayList<String> insuranceInfo = new ArrayList<>();
        insuranceInfo.add("AXA S.A. (styled as AXA) is a French multinational insurance firm headquartered in the 8th arrondissement of Paris that engages in global insurance, investment management, and other financial services. The Axa Group operates primarily in Western Europe, North America, the India Pacific region, and the Middle East, with a presence also in Africa. Axa is a conglomerate of independently run businesses, operated according to the laws and regulations of many different countries. The company is a component of the Euro Stoxx 50 stock market index.");
        insuranceInfo.add("Groupama an abbreviation for Groupe des Assurances Mutuelles Agricoles (English: Group of Mutual Agricultural Insurances) is a French insurance group headquartered in Paris with operations in 12 countries. It is listed in the 2007 ICA Global 300 list of mutuals and co-operatives, ranked 6th by 2005 turnover, making it the 2nd largest mutual insurer in the world.");
        insuranceInfo.add("Equitable Holdings (formerly The Equitable Life Assurance Society of the United States and AXA Equitable Life Insurance Company, and also known as The Equitable) was founded by Henry Baldwin Hyde in 1859. In 1991, Axa, a French insurance company, acquired majority control of The Equitable.\n" +
                "In 2004, it officially changed its name to AXA Equitable Life Insurance Company. By 2018, the company had over 15,800 agents licensed by the State of California. In January 2020 it changed its name back to Equitable following its spinoff from AXA and the related public offerings beginning in May 2018.\n"+"\n"+"\n"+"\n");


        ArrayList<String> hairCare = new ArrayList<>();
        hairCare.add("L'Oreal");
        hairCare.add("Lancome");
        hairCare.add("Kerastase");

        ArrayList<String> hairCareInfo = new ArrayList<>();
        hairCareInfo.add("L'Oréal Group, L'Oréal S.A. is a French personal care company headquartered in Clichy, Hauts-de-Seine with a registered office in Paris. It is the world's largest cosmetics company and has developed activities in the field concentrating on hair colour, skin care, sun protection, make-up, perfume, and hair care\n" +
                "L'Oréal include some brands: Clarisonic, Drakkar Noir, Kérastase, L'Oréal Professionnel, Lancôme, Magic Shave, NYX Cosmetics, Pureology, Redken, Shu Uemura, Urban Decay, Kiehl's, Maybelline, Vichy, Garnier, Helena Rubinstein, Ralph Lauren, Giorgio Armani Parfums, Biotherm, Cacharel, CeraVe, SkinCeuticals, La Roche-Posay.\n"+"\n"+"\n"+"\n");
        hairCareInfo.add("Lancôme is a French luxury perfumes and cosmetics house that distributes products internationally. Lancôme is part of the L'Oréal Luxury Products division, which is its parent company and offers luxury skin care, fragrances, and makeup at higher-end prices.");
        hairCareInfo.add("Kérastase is a French luxury hair care line that distributes products internationally. Kérastase is part of the L’Oréal Professional Products Division.");


        ArrayList<String> clothing = new ArrayList<>();
        clothing.add("Lacoste");
        clothing.add("Givenchy");
        clothing.add("Yves Saint Laurent");
        clothing.add("René Derhy");
        clothing.add("Daniel Hechter");
        clothing.add("Pierre Cardin");
        clothing.add("Louis Vuitton");
        clothing.add("Fred Joallier");
        clothing.add("Berluti");
        clothing.add("Cacharel");
        clothing.add("Hermes");
        clothing.add("Longchamp");
        clothing.add("Lancel");
        clothing.add("Chloé");

        ArrayList<String> clothingInfo = new ArrayList<>();
        clothingInfo.add("Lacoste is a French company, founded in 1933 by tennis player René Lacoste and André Gillier. It sells clothing, footwear, sportswear, eyewear, leather goods, perfume, towels and watches. The company can be recognised by its green crocodile logo. René Lacoste, the company's founder, was nicknamed \"the Crocodile\" by fans because of his tenacity on the tennis court. René Lacoste founded La Chemise Lacoste in 1933 with André Gillier, the owner and president of the largest French knitwear manufacturing firm at the time. They began to produce the revolutionary tennis shirt Lacoste had designed and worn on the tennis courts with the crocodile logo embroidered on the chest."+"\n"+"\n"+"\n"+"\n");
        clothingInfo.add("Givenchy is a French luxury fashion and perfume house. It hosts the brand of haute couture clothing, accessories, perfumes and cosmetics (Parfums Givenchy). The house of Givenchy was founded in 1952 by designer Hubert de Givenchy and is a member of Chambre Syndicale de la Haute Couture et du Pret-a-Porter. It is owned by a luxury conglomerate LVMH.");
        clothingInfo.add("Yves Saint Laurent (YSL), also known as Saint Laurent, is a French luxury fashion house founded by Yves Saint Laurent and his partner, Pierre Bergé. The company revived its haute couture collection in 2015 under former Creative Director Hedi Slimane. In April 2016, Anthony Vaccarello was appointed as Creative Director.\n" +
                "Founded in 1961, it has been considered one of the world's most prominent fashion houses and is known for its modern and iconic[clarification needed] pieces, such as its tuxedo jackets for women. Today, Saint Laurent markets a broad range of women's and men's ready-to-wear products, leather goods, shoes and jewellery. \n"+"\n"+"\n"+"\n"+"\n");
        clothingInfo.add("René Derhy is a brand of dresses, tops, skirts, sweaters, knitwear, coats, t-shirts, accessories for women and children within the body of Groupe René Derhy, founded in 1969.");
        clothingInfo.add("Daniel Hechter Paris is a French fashion and lifestyle brand with 45 licensees worldwide. It sells men's and women's wear, accessories, and consumer goods. In 1962, the French fashion designer Daniel Hechter published his first women's collection. Three years later, he added a children's line and completed the fashion range with the men's line in 1968. These collections made him become popular as the inventor of ready- to - wear or Prêt-à-porter fashion. After a few years, these collections were expanded into sports, relaxation, and leisure wear. Eyewear, perfume, pens, and consumer goods followed, then watches and leather goods joined the product range. The most significant license of Daniel Hechter Paris is for shoes: Among the accessories, it generates the biggest turnover."+"\n"+"\n"+"\n"+"\n"+"\n");
        clothingInfo.add("The brand, which was founded by Pierre Cardin in France in 1950, has become one of the most well-known clothing brands by guiding the world fashion with its firsts and extraordinary designs. Reflecting the enthusiasm of the period in his collections with the theme \"Space Age\", especially in the 60s, when space exploration was intense, Pierre Cardin is one of the first representatives of the avant-garde and futuristic trend in fashion.");
        clothingInfo.add("Louis Vuitton Malletier, commonly known as Louis Vuitton or shortened to LV, is a French fashion house and luxury goods company founded in 1854 by Louis Vuitton. The label's LV monogram appears on most of its products, ranging from luxury trunks and leather goods to ready-to-wear, shoes, watches, jewelry, accessories, sunglasses and books. Louis Vuitton is one of the world's leading international fashion houses; it sells its products through standalone boutiques, lease departments in high-end department stores, and through the e-commerce section of its website.");
        clothingInfo.add("Fred Joaillier is a French jewellery brand named after the founder Fred Samuel. Its first store opened in Paris in 1936 and became famous for jewels designed by Jean Cocteau and for its famous client such as Marlène Dietrich or Grace Kelly.");
        clothingInfo.add("Berluti, is a subsidiary brand of LVMH that manufactures menswear, especially the leather finishing of calfskin, kangaroo leather and alligator skin in its production of shoes and boots. It makes leather belts, bags, and wallets, as well as bespoke and ready-to-wear garments. Established in 1895 by Italian Alessandro Berluti of Marche, Berluti is based in Paris on rue Marbeuf.");
        clothingInfo.add("Cacharel is a French brand of ready-to-wear clothing, perfume and accessories, founded in 1962 by Jean Bousquet. Cacharel designs are characterized by their youthful style, femininity, lightness, refinement, and use of bright colours.");
        clothingInfo.add("Hermès International S.A., or simply Hermès, is a French high fashion luxury goods manufacturer established in 1837. It specializes in leather, lifestyle accessories, home furnishings, perfumery, jewellery, watches and ready-to-wear. Its logo, since the 1950s, is of a Duc carriage with horse.");
        clothingInfo.add("Longchamp, Longchamp is a French luxury leather goods company, founded in Paris in 1948 by Jean Cassegrain.\n" +
                "Jean Cassegrain produced the world's first luxury leather-covered pipes, then expanded into small leather goods, such as wallets, passport covers, etc.. Longchamp launched its first women handbag in 1971 and became one of France's leading leather goods makers. The company today designs and manufactures a wide range of luxury goods including leather and canvas handbags, luggage, shoes, travel items, fashion accessories, and a line of \"ready-to-wear\" women's designer clothing. The company does business in 80 countries through around 1,500 retail outlets. The house is still privately owned and managed by the Cassegrain founding family.\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        clothingInfo.add("Lancel, is a French luxury leather goods company, founded in Paris in 1876 by Angèle and Alphonse Lancel and developed by their son Albert. The company manufactured pipes before expanding into leather goods and handbags. It remained in the hands of the couple's descendants until it was acquired in the late 1970s by the Zorbibe brothers, who then launched the popular bucket-shaped Elsa bag under Chief Executive Sidney Toledano, now CEO at Dior. Lancel provides leather goods for men and women, such as handbags, wallets, purses, travel items, suitcases, accessories. The brand has an over 140-year tradition creating luxury leather goods. The company is still based in Paris, France."+"\n"+"\n"+"\n"+"\n"+"\n");
        clothingInfo.add("Chloé, is a French luxury fashion house founded in 1952 by Gaby Aghion.  Its headquarters are located in Paris, France. The house is owned by luxury brands holding company Richemont Group. Chloé has been worn by many celebrities, including Marion Cotillard, Sienna Miller, Madonna, January Jones, Maggie Gyllenhaal, Cameron Diaz, Emma Stone, Clémence Poésy and Katie Holmes.");

        ArrayList<String> supermarket = new ArrayList<>();
        supermarket.add("Carrefour");

        ArrayList<String> supermarketInfo = new ArrayList<>();
        supermarketInfo.add("Carrefour, Carrefour S.A. , is a French multinational corporation specialized in retail. The first Carrefour shop (not a hypermarket) opened in 1960, within suburban Annecy, near a crossroads. The group was created in 1958 by Marcel Fournier, Denis Defforey and Jacques Defforey, who attended and were influenced by several seminars in the United States led by \"the Pope of retail\" Bernardo Trujillo. The Carrefour group was the first in Europe to open a hypermarket, a large supermarket, and a department store under the same roof. They opened their first hypermarket on 15 June 1963 in Sainte-Geneviève-des-Bois, near Paris."+"\n"+"\n"+"\n");


        ArrayList<String> dairyProducts = new ArrayList<>();
        dairyProducts.add("Danone");
        dairyProducts.add("Yoplait");
        dairyProducts.add("La Vache Qui Rit");
        dairyProducts.add("Fromageries Riches Monts");
        dairyProducts.add("Gerard");
        dairyProducts.add("Taillefine");
        dairyProducts.add("Président");
        dairyProducts.add("Elle & Vire");
        dairyProducts.add("Babybel");


        ArrayList<String> dairyProductsInfo = new ArrayList<>();
        dairyProductsInfo.add("Danone S.A., is a French multinational food-products corporation based in Paris and founded in Barcelona, Spain. It is listed on Euronext Paris where it is a component of the CAC 40 stock market index. Some of the company's products are branded Dannon in the United States.\n" +
                "As of 2018, Danone sold products in 120 markets, and had sales in 2018 of €24.65 billion. In the first half of 2018, 29% of sales came from specialized nutrition, 19% came from waters, and 52% came from dairy and plant-based products.\n" +
                "In the 2010s, reports indicated that Danone engaged in unethical marketing of infant formula in China, Indonesia, Turkey, and India.\n"+"\n"+"\n"+"\n"+"\n");
        dairyProductsInfo.add("Yoplait, is the world's largest franchise brand of yogurt. It is jointly owned by United States–based food conglomerate General Mills and French dairy cooperative Sodiaal. In 1964, 100,000 French farmers agreed to merge six regional dairy cooperatives in order to more efficiently market their products at the national level. In 1965, the Yoplait brand was launched as a new national brand, as a portmanteau of the brands of two member cooperatives, Yola and Coplait. ");
        dairyProductsInfo.add("La Vache Qui Rit , (The Laughing Cow) is a brand of processed cheese products made by Fromageries Bel since 1921, and in particular refers to the brand's most popular product, the spreadable wedge. The cheese is a blend of cream, milk and fresh and aged cheeses, particularly comté, which are pasteurized to stop the ripening process.");
        dairyProductsInfo.add("Compagnie des Fromages et RichesMonts (CF&R) is a French food-processing company, specialized in the manufacturing and the marketing of French cheese, more specifically traditional French soft cheese (such as camembert and brie) and raclette cheese.");
        dairyProductsInfo.add("The cheese making tradition in the Gérard family starts in the first half of the 19th century with François Gérard in a small village, Le Tholy, located in the Vosges mountains on the eastern side of France. In 1898, François Gérard’s son, Eugène Gérard, founds the cheese factory La Fromagerie Gérard.");
        dairyProductsInfo.add("Taillefine, is a brand of the Danone Group, created in 1964, which manufactures dairy products, dessert creams, compotes and “low-fat” drinks.");
        dairyProductsInfo.add("Président is a French dairy brand owned by the Laval-based Lactalis company. The brand was created in 1933 by André Besnier. It is used for butter and for a range of industrially produced versions of traditional cheeses.");
        dairyProductsInfo.add("Elle & Vire is a commercial brand of industrial processed dairy products belonging to the agro-food group Savencia Fromage & Dairy. The French subsidiary of this group, \"Société Coopérative Agricole Elle et Vire\", based in Condé-sur-Vire (Manche), buys and collects milk from cattle breeders for the Cœur de Lion and Elle-et-Vire factories.");
        dairyProductsInfo.add("Babybel, is a brand of small snack cheese products that are individually packaged and available in various flavors. It is a product of Le Groupe Bel (The Bel Group), a company with roots in the Jura region of France, and started by Jules Bel in 1865. Half of the global production of Mini Babybel is made in Évron, a commune in the north west of France. Le Groupe Bel has produced Babybel cheeses in the United States in Kentucky since 1970. In March 2016, Bel Brands USA opened a new plant in Brookings, South Dakota. At the time, Bel Brands projected that its 250 employees would produce 1.5 million Mini Babybel cheese wheels a day. In July 2018, Le Groupe Bel announced that their first Canadian office would be in Quebec, and that the company had 12,700 employees, in thirty subsidiaries around the world."+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");


        ArrayList<String> bottledWater = new ArrayList<>();
        bottledWater.add("Perrier");
        bottledWater.add("Evian");
        bottledWater.add("Danone");

        ArrayList<String> bottledWaterInfo = new ArrayList<>();
        bottledWaterInfo.add("Perrier, is a French brand of natural bottled mineral water captured at the source in Vergèze, located in the Gard département. Perrier is best known for its naturally occurring carbonation, distinctive green bottle, and higher levels of carbonation than its peers. Perrier was part of the Perrier Vittel Group SA, which became Nestlé Waters France after the acquisition of the company by Nestlé in 1992. Nestlé Waters France also includes Vittel, S.Pellegrino and Contrex.");
        bottledWaterInfo.add("Évian, is a brand of mineral water coming from several sources near Évian-les-Bains, on the south shore of Lake Geneva. Today, Évian is owned by Danone, a French multinational corporation.");
        bottledWaterInfo.add("Danone S.A., is a French multinational food-products corporation based in Paris and founded in Barcelona, Spain. Today, water represented approximately %21 of Danone Group’s total sales in the world.");


        ArrayList<String> automobileIndustry = new ArrayList<>();
        automobileIndustry.add("Valeo");
        automobileIndustry.add("Peugeot");
        automobileIndustry.add("Renault");
        automobileIndustry.add("Citroen");
        automobileIndustry.add("Michelin");
        automobileIndustry.add("Uniroyal");
        automobileIndustry.add("Recamic");
        automobileIndustry.add("Dacia");

        ArrayList<String> automobileIndustryInfo = new ArrayList<>();
        automobileIndustryInfo.add("Valeo is a French global automotive supplier headquartered in France, listed on the Paris Stock Exchange (CAC-40 Index). It supplies a wide range of products to automakers and the aftermarket.");
        automobileIndustryInfo.add("Peugeot has an strange story, one of the longest and most interesting in the automotive industry. Peugeot began life as an industrial manufacturer way back in 1810 – a steel foundry producing products such as band saws, umbrella frames and coffee grinders. The first car to carry the Peugeot name was unveiled in 1889, making Peugeot the world’s second oldest car manufacturer and the oldest continuous car brand. Since 1850, Peugeot has been represented by the Lion symbol. Originally appearing on the company’s saw blades, the Lion symbolised the toughness of the saw’s teeth, the flexibility of blade like the lion’s spine, the strength of the steel and the speed of the cut, like a bounding lion."+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        automobileIndustryInfo.add("Groupe Renault, is a French multinational automobile manufacturer established in 1899. The company produces a range of cars and vans, and in the past has manufactured trucks, tractors, tanks, buses/coaches, aircraft and aircraft engines, and autorail vehicles. According to the Organisation Internationale des Constructeurs d'Automobiles, in 2016 Renault was the ninth biggest automaker in the world by production volume. By 2017, the Renault–Nissan–Mitsubishi Alliance had become the world's biggest seller of light vehicles, bumping Volkswagen AG off the top spot."+"\n"+"\n"+"\n");
        automobileIndustryInfo.add("Citroën, is a French automobile manufacturer founded in 1919 by the French industrialist André-Gustave Citroën, and part of Groupe PSA since 1976. In 1934, the firm established its reputation for innovative technology with the Traction Avant. This was the world's first car to be mass-produced with front-wheel drive, four-wheel independent suspension, as well as unibody construction, omitting a separate chassis, and instead using the body of the car itself as its main load-bearing structure.\n" +
                "In 1954 they produced the world's first hydropneumatic self-levelling suspension system then, in 1955, the revolutionary DS, the first mass-produced car with modern disc brakes and, in 1967, they introduced in several of their models swiveling headlights that allowed for greater visibility on winding roads; these cars have received various national and international awards, including three European Car of the Year.\n" +
                "Citroën has been selling vehicles in China since 1984, largely via the Dongfeng Peugeot-Citroën joint venture, which today represents a major market for the brand. In 2014, when PSA Peugeot Citroën ran into severe financial difficulties, the Dongfeng Motor Corporation took an ownership stake.\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        automobileIndustryInfo.add("Michelin, is a French multinational tyre manufacturer based in Clermont-Ferrand in the Auvergne-Rhône-Alpes région of France. It is the second largest tyre manufacturer in the world after Bridgestone and larger than both Goodyear and Continental. In addition to the Michelin brand, it also owns the Kléber tyres company, Uniroyal-Goodrich Tire Company, SASCAR, Bookatable and Camso brands. Michelin is also notable for its Red and Green travel guides, its roadmaps, the Michelin stars that the Red Guide awards to restaurants for their cooking, and for its company mascot Bibendum, colloquially known as the Michelin Man."+"\n"+"\n"+"\n"+"\n");
        automobileIndustryInfo.add("The United States Rubber Company (Uniroyal) is an American manufacturer of tires and other synthetic rubber-related products, as well as variety of items for military use, such as ammunition, explosives and operations and maintenance activities (O&MA) at the government-owned contractor-operated facilities. It was founded in Naugatuck, Connecticut, in 1892. It was one of the original 12 stocks in the Dow Jones Industrial Average, and became Uniroyal, Inc., as part of creating a unified brand for its products and subsidiaries in 1961.\n" +
                "In 1990, Uniroyal was acquired by French tire maker Michelin and ceased to exist as a separate business. Today around 1,000 workers in the U.S. remain employed by Michelin to make its Uniroyal brand products. While in North America, Colombia and Peru, the Uniroyal brand has been owned by Michelin since 1990, outside those regions, the Uniroyal brand has been owned by Continental AG since 1979 following their acquisition of Uniroyal Europe, formerly known as Englebert.\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        automobileIndustryInfo.add("Recamic, is a retreaded brand of the Michelin Group. Recamic improves retreading safety and performance. With the Michelin Retread Technologies, the Recamic retreaded tyres get a new lease on life similar to new tyre life.");
        automobileIndustryInfo.add("Automobile Dacia S.A., is a Romanian car manufacturer that takes its name from the historic region that constitutes the present-day Romania. The company was established in 1966. In 1999, after 33 years, the Romanian government sold Dacia to the French car manufacturer Groupe Renault. It is Romania's largest company by revenue and the largest exporter, constituting 8% of the country's total exports in 2018. In 2019, the company sold 736,654 passenger and commercial vehicles.");


        ArrayList<String> infantClothingAndFormula = new ArrayList<>();
        infantClothingAndFormula.add("Bledina");
        infantClothingAndFormula.add("Majorette");
        infantClothingAndFormula.add("DPAM");
        infantClothingAndFormula.add("Petit Bateau");

        ArrayList<String> infantClothingAndFormulaInfo = new ArrayList<>();
        infantClothingAndFormulaInfo.add("Blédina, is Danone's subsidiary in Infantile food. Blédina produces baby nutrition products. It offers a variety of cereals, sweets, meals, vegetables, and fruit drinks for babies and toddlers. Danone is based in France.");
        infantClothingAndFormulaInfo.add("Majorette, is a French toy manufacturer which mostly produces small die-cast cars and other construction and military vehicles, particularly in 1:64 scale. This is a normal 2.5 to 3 inch size, thus Majorette has sometimes been called the Matchbox Toys of France. Traditionally, production was centered in the urban area of Lyon, but models are now made in Thailand.");
        infantClothingAndFormulaInfo.add("Du Pareil au Même, often known as DPAM, is a French children's clothing chain founded by Simon Benharrous in 1986. It sells products such as shoes, clothes, accessories that cover children between the ages of 0-14.");
        infantClothingAndFormulaInfo.add("Petit Bateau, is a French brand of clothing and underwear for children founded in 1920, but whose origins date back to 1893, in Troyes. Integrated into the Rocher Group since 1988, Petit Bateau is a children's brand that became trans-generational in the 2000s thanks to the launch of the adult collection.");


        ArrayList<String> cosmetics = new ArrayList<>();
        cosmetics.add("L'Oreal");
        cosmetics.add("La Roche Posay");
        cosmetics.add("Biotherm");
        cosmetics.add("Christian Dior");
        cosmetics.add("Clarins");
        cosmetics.add("Vichy");
        cosmetics.add("Garnier");

        ArrayList<String> cosmeticsInfo = new ArrayList<>();
        cosmeticsInfo.add("L'Oréal Group, L'Oréal S.A. is a French personal care company headquartered in Clichy, Hauts-de-Seine with a registered office in Paris. It is the world's largest cosmetics company and has developed activities in the field concentrating on hair colour, skin care, sun protection, make-up, perfume, and hair care\n" +
                "L'Oréal include some brands: Clarisonic, Drakkar Noir, Kérastase, L'Oréal Professionnel, Lancôme, Magic Shave, NYX Cosmetics, Pureology, Redken, Shu Uemura, Urban Decay, Kiehl's, Maybelline, Vichy, Garnier, Helena Rubinstein, Ralph Lauren, Giorgio Armani Parfums, Biotherm, Cacharel, CeraVe, SkinCeuticals, La Roche-Posay.\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        cosmeticsInfo.add("La Roche Posay, is a brand of dermocosmetic care products for oily and acne skin, sensitive and rash-prone skin and dry skin. La Roche Posay brand is part of France-based Loreal group.");
        cosmeticsInfo.add("Biotherm, is a French skin care company owned by L'Oréal under the Luxury Products division. Biotherm was acquired by L'Oréal in 1970.\n" +
                "Biotherm originated from mineral water. In early 20th century, the French doctor Jos Jullien discovered mineral thermal spring waters under Pyrenees mountain in the southern part of France which contained thermal plankton, supposedly a key to healthy skin and a potent skin rejuvenator. In 1952, intellectual property rights was acquired and she used it in skin care products. Thus, therm in Biotherm comes from thermal plankton, an ingredient found in all Biotherm products. Bio comes from the profession of the founder biologist.\n"+"\n"+"\n"+"\n"+"\n");
        cosmeticsInfo.add("Christian Dior SE commonly known as Dior, is a French luxury goods company controlled and chaired by French businessman Bernard Arnault, who also heads LVMH, the world's largest luxury group. Dior itself holds 42.36% shares of and 59.01% voting rights within LVMH. \n" +
                "The company was founded in 1946 by French fashion designer Christian Dior, who was originally from Normandy. This brand just sells shoes and clothing and can only be bought in Dior stores. haute-couture under the Christian Dior Couture division. Pietro Beccari has been the CEO of Christian Dior Couture since 2018.\n" +
                "The Christian Dior label remains largely for women's offerings, although the company also operates the Dior Homme division for men and the baby Dior label for children's wear. Products are sold throughout its portfolio of retail stores worldwide, as well as through its online store.\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        cosmeticsInfo.add("Clarins Group, trading as Clarins, is a French luxury skin care, cosmetics and perfume company, which manufactures and sells products, usually through high-end department store counters and selected pharmacies.");
        cosmeticsInfo.add("Vichy, is a premium brand of skincare, bodycare, make-up and anti-aging products owned by L'Oréal under its Active Cosmetics division. Thermal spa water from the hot springs of the town of Vichy, France, is the only water source used in its formulations. The company was founded in 1931.\n" +
                "The company is one of two 'active cosmetics' brands in the L'Oreal group, the other being La Roche-Posay. Vichy is most popular in Europe, and the company is known for their 'Dermablend' range of foundations and concealers. Vichy is one of the biggest brands in the European skincare market. Some of their leading lines are:\n" +
                "LiftActiv (Anti-Aging), Neovadiol, Normaderm (Oily, blemish-prone skin), Dercos, Aqualia Thermal, Capital Soleil, Essentielles (Basic line of cheaper products), Vichy Homme (Men's shaving and cleansing products). \n" +
                "The tagline of Vichy is \"Health is beautiful\"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        cosmeticsInfo.add("Garnier, is a mass market cosmetics brand of French cosmetics company L'Oréal. It produces hair care and skin care products.");


        ArrayList<String> perfume = new ArrayList<>();
        perfume.add("Chanel");
        perfume.add("Christian Dior");
        perfume.add("Clarins");
        perfume.add("Drakkar Noir");
        perfume.add("Fahrenheit");
        perfume.add("Lancome");

        ArrayList<String> perfumeInfo = new ArrayList<>();
        perfumeInfo.add("Chanel, is a French fashion house that focuses on women's high fashion and ready-to-wear clothes, luxury goods and accessories. The company is owned by Alain Wertheimer and Gérard Wertheimer, grandsons of Pierre Wertheimer, who was an early business partner of the couturière Coco Chanel. In her youth, Gabrielle Chanel gained the nickname \"Coco\" from her time as a chanteuse. As a fashion designer, Coco Chanel catered to women's taste for elegance in dress, with blouses, suits, trousers, dresses, and jewellery (gemstone and bijouterie) of simple design, that replaced the opulent, over-designed, and constrictive clothes and accessories of 19th-century fashion. \n" +
                "The Chanel product brands have been personified by male and female fashion models, idols and actresses, including Inès de La Fressange, Catherine Deneuve, Carole Bouquet, Vanessa Paradis, Nicole Kidman, Jackie Kennedy, Anna Mouglalis, Audrey Tautou, Keira Knightley, Kristen Stewart, Pharrell Williams, Jennie Kim, Cara Delevingne, and Marilyn Monroe.\n"+"\n"+"\n"+"\n"+"\n");
        perfumeInfo.add("Christian Dior SE commonly known as Dior, is a French luxury goods company controlled and chaired by French businessman Bernard Arnault, who also heads LVMH, the world's largest luxury group. Dior itself holds 42.36% shares of and 59.01% voting rights within LVMH. \n" +
                "The company was founded in 1946 by French fashion designer Christian Dior, who was originally from Normandy. This brand just sells shoes and clothing and can only be bought in Dior stores. haute-couture under the Christian Dior Couture division. Pietro Beccari has been the CEO of Christian Dior Couture since 2018.\n" +
                "The Christian Dior label remains largely for women's offerings, although the company also operates the Dior Homme division for men and the baby Dior label for children's wear. Products are sold throughout its portfolio of retail stores worldwide, as well as through its online store.\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        perfumeInfo.add("Clarins Group, trading as Clarins, is a French luxury skin care, cosmetics and perfume company, which manufactures and sells products, usually through high-end department store counters and selected pharmacies.");
        perfumeInfo.add("Drakkar Noir, is a perfume brand produced under the license of L'Oréal Group, created by Guy Laroche in 1982.");
        perfumeInfo.add("Fahrenheit, is a perfume for men produced by Parfums Christian Dior. The fragrance was introduced in 1988. It was created by the perfumers Maurice Roger and Jean-Louis Sieuzac.");
        perfumeInfo.add("Lancôme, is a French luxury perfumes and cosmetics house that distributes products internationally. Lancôme is part of the L'Oréal Luxury Products division, which is its parent company and offers luxury skin care, fragrances, and makeup at higher-end prices.");


        ArrayList<String> skinCare = new ArrayList<>();
        skinCare.add("Clarins");
        skinCare.add("Guerlain");

        ArrayList<String> skinCareInfo = new ArrayList<>();
        skinCareInfo.add("Clarins Group, trading as Clarins, is a French luxury skin care, cosmetics and perfume company, which manufactures and sells products, usually through high-end department store counters and selected pharmacies.");
        skinCareInfo.add("Guerlain, is a French perfume, cosmetics and skincare house, which is among the oldest in the world. Many traditional Guerlain fragrances are characterized by a common olfactory accord known as the \"Guerlinade\" (fr). The house was founded in Paris in 1828 by the perfumer Pierre-François Pascal Guerlain. It was run by the Guerlain family until 1994, when it was bought by the French multinational company LVMH. Its flagship store is 68, Avenue des Champs-Elysées in Paris. ");

        ArrayList<String> construction = new ArrayList<>();
        construction.add("Onduline");
        construction.add("Lafarge");
        construction.add("Chryso");
        construction.add("Weber Markem");

        ArrayList<String> constructionInfo = new ArrayList<>();
        constructionInfo.add("Onduline was launched on the French market in 1944 by Gaston Gromier. Onduline was a family-owned business until it was sold to private equity funds in 2006. Specialized in innovative roofing systems, the group has been growing since then, until leading the market of lightweight roofing systems.");
        constructionInfo.add("Lafarge S.A., was a French industrial company specialising in three major products: cement, construction aggregates, and concrete. It was founded in 1833 by Joseph-Auguste Pavin de Lafarge. ");
        constructionInfo.add("Chryso provides chemical products. The Company manufactures building construction materials such as concrete, cement, cope, plasters, and other goods. Chryso is a company based in France.");
        constructionInfo.add("Weber Markem, is a brand within Saint-Gobain founded in 1665. It produces parts such as industrial mortars, plaster, insulation material, pipes, exterior products, flat glass, high performance products, abrasives, plastic, glass fiber.");



        ArrayList<String> travelIndustry = new ArrayList<>();
        travelIndustry.add("Air France");

        ArrayList<String> travelIndustryInfo = new ArrayList<>();
        travelIndustryInfo.add("Air France, is the flag carrier of France headquartered in Tremblay-en-France. It is a subsidiary of the Air France–KLM Group and a founding member of the SkyTeam global airline alliance. The airline's global hub is at Charles de Gaulle Airport with Orly Airport as the primary domestic hub. Air France's corporate headquarters, previously in Montparnasse, Paris, are located on the grounds of Charles de Gaulle Airport, north of Paris.");


        ArrayList<String> lighter = new ArrayList<>();
        lighter.add("BIC");
        lighter.add("Cartier");

        ArrayList<String> lighterInfo = new ArrayList<>();
        lighterInfo.add("Société Bic S.A., commonly referred to simply as Bic and stylized as BiC, is a manufacturing corporation based in Clichy, France, best known for making disposable consumer products such as lighters, razors and pens. It was founded in 1945 by Marcel Bich.");
        lighterInfo.add("Cartier International SNC, or simply Cartier, is a French luxury goods conglomerate which designs, manufactures, distributes, and sells jewellery and watches.");



        ArrayList<String> sportsEquipment = new ArrayList<>();
        sportsEquipment.add("Le coq sportif");
        sportsEquipment.add("Decathlon");

        ArrayList<String> sportsEquipmentInfo = new ArrayList<>();
        sportsEquipmentInfo.add("Le Coq Sportif, is a French manufacturing company of sports equipment. Founded in 1882 by Émile Camuset and located in Entzheim, the company first issued items branded with its now-famous rooster trademark in 1948. The company's name and trademark are derived from the Gallic rooster, a national symbol of France. In present days, Le Coq Sportif is a subsidiary of \"Airesis S.A.\", a Switzerland-based investment company that holds a 69%-stake of LCS. Current range of products by Le Coq Sportif includes sportswear, casual clothing, and footwear."+"\n"+"\n");
        sportsEquipmentInfo.add("Decathlon S.A., is a French sporting goods retailer. Founded by Michel Leclercq in 1976, Decathlon started with a store in Lille, France. It is the largest sporting goods retailer in the world. Decathlon Group markets its products under more than 20 brands. Its research and development facilities are located across France, where the conpany develops its product designs, registering up to 40 patents per year.");


        ArrayList<String> fuelOil = new ArrayList<>();
        fuelOil.add("Total");
        fuelOil.add("Elf");

        ArrayList<String> fuelOilInfo = new ArrayList<>();
        fuelOilInfo.add("Total SE,  is a French multinational integrated oil and gas company founded in 1924 and one of the seven \"Supermajor\" oil companies in the world. Its businesses cover the entire oil and gas chain, from crude oil and natural gas exploration and production to power generation, transportation, refining, petroleum product marketing, and international crude oil and product trading. Total is also a large scale chemicals manufacturer.\n" +
                "Total has its head office in the Tour Total in La Défense district in Courbevoie, west of Paris. The company is a component of the Euro Stoxx 50 stock market index. In the 2020 Forbes Global 2000, Total S.A was ranked as the 29th -largest public company in the world.\n" +
                "Total is responsible for 0.95% of global industrial greenhouse gas emissions from 1988 to 2015.\n"+"\n"+"\n"+"\n"+"\n");
        fuelOilInfo.add("Elf is a brand of Total, the world’s 5th international oil company, which is present in 150 countries.");


        ArrayList<String> pharmaceuticalCompany = new ArrayList<>();
        pharmaceuticalCompany.add("Servier");
        pharmaceuticalCompany.add("Guerbet");
        pharmaceuticalCompany.add("Pierre Fabre Medicament");
        pharmaceuticalCompany.add("Allegra");
        pharmaceuticalCompany.add("Benzac");
        pharmaceuticalCompany.add("Benzagel");
        pharmaceuticalCompany.add("Benzamycin");
        pharmaceuticalCompany.add("Novalgin");

        ArrayList<String> pharmaceuticalCompanyInfo = new ArrayList<>();
        pharmaceuticalCompanyInfo.add("Servier Laboratories (French: Laboratoires Servier, often abbreviated to Servier) is an international pharmaceutical company with its headquarters in France (Suresnes). Servier is the second largest French pharmaceutical company worldwide. It has branches in 149 countries, achieving 82% of its sales outside France.");
        pharmaceuticalCompanyInfo.add("Guerbet is a France-based manufacturer of contrast agents used in medical imaging. The company was founded in 1926 by André Guerbet, the son of Marcel Guerbet who in 1901 discovered Lipiodol – the first iodinated X-ray contrast agent.\n" +
                "In 2017 Guerbet’s revenues were €807.1 million. It employs over 2,700 people worldwide and has manufacturing facilities in France, Ireland, Canada, the United States and Brazil. The company’s headquarters are located in Villepinte, a suburb of Paris and its shares are included in the CAC Small stock index.\n"+"\n"+"\n"+"\n");
        pharmaceuticalCompanyInfo.add("Laboratoires Pierre Fabre is a French multinational pharmaceutical and cosmetics company. The company had a consolidated turnover of 1.978 billion euros in 2012 (including 54% international). It is headquartered in the city of Castres, Midi-Pyrénées, France.");
        pharmaceuticalCompanyInfo.add("Allegra (fexofenadine) is an antihistamine that reduces the effects of natural chemical histamine in the body. Histamine can produce symptoms of sneezing, itching, watery eyes, and runny nose. Allegra is used to treat the symptoms of seasonal allergies (hay fever) in adults and children. It originates in France.");
        pharmaceuticalCompanyInfo.add("Benzac AC is used to treat mild to moderate acne. It may be used in combination with other acne treatments. When applied to the skin, benzoyl peroxide works by reducing the amount of acne-causing bacteria and by causing the skin to dry and peel.");
        pharmaceuticalCompanyInfo.add("Benzagel (benzoyl peroxide gel) is a topical (for the skin) antibacterial agent that also has a mild drying effect used to treat acne. Benzagel is available in generic form.");
        pharmaceuticalCompanyInfo.add("Benzamycin is a topical gel containing of 5% benzoyl peroxide and 3% erythromycin. Developed and manufactured by Dermik Laboratories, its main usage is to fight acne. Benzamycin is a prescription medication.");
        pharmaceuticalCompanyInfo.add("Novalgin, is a drug that commonly used for many different types of pain such as tumors pain, pain after surgery or injuries.");



        ArrayList<String> industrialAndElectricity = new ArrayList<>();
        industrialAndElectricity.add("Areva");
        industrialAndElectricity.add("Groupe Schneider");
        industrialAndElectricity.add("Legrand");
        industrialAndElectricity.add("Duval Messien");
        industrialAndElectricity.add("Franklin France");
        industrialAndElectricity.add("Merlin Gerin");
        industrialAndElectricity.add("Telemechanique");
        industrialAndElectricity.add("Square-D");

        ArrayList<String> industrialAndElectricityInfo = new ArrayList<>();
        industrialAndElectricityInfo.add("Areva S.A. was a French multinational group specializing in nuclear power and renewable energy headquartered in Paris, France. Before its 2016 corporate restructuring, Areva was majority-owned by the French state through the French Alternative Energies and Atomic Energy Commission (54.37%), Banque publique d'investissement (3.32%), and Agence des participations de l'État (28.83%). Électricité de France, of which the French government has a majority ownership stake, owned 2.24%; Kuwait Investment Authority owned 4.82% as the second largest shareholder after the French state. The company was listed at the European stock exchange Euronext.\n" +
                "As a part of the restructuring program following its insolvency, Areva sold out or discontinued its renewable energy businesses, sold its reactors business subsidiary Areva NP (now: Framatome) to EDF and its nuclear propulsion and research reactors subsidiary Areva TA (now: Technicatome) to Agence des participations de l'État, and separated its nuclear cycle business into a separate company New Areva (later: Orano). As a result, Areva S.A. became wholly state-owned by the French government, remaining responsible only for the liabilities related to the Olkiluoto 3 project in Finland and holding a 40% stake in Orano.\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        industrialAndElectricityInfo.add("Schneider Electric is a European multinational company providing energy and automation digital solutions for efficiency and sustainability. It addresses homes, buildings, data centers, infrastructure and industries, by combining energy technologies, real-time automation, software and services. The company has operations in over 100 countries and employs 135,000+ people. ");
        industrialAndElectricityInfo.add("Legrand is a French industrial group historically based in Limoges in the Limousin region.  The company's origins date back to 1865, when a Limoges porcelain (in France's Limousin region) workshop was set up on the route to Lyon originally making porcelain dishes. In 1904, the company was taken over by Frédéric Legrand, who gave his name to the company. In 1919, the company entered into partnership with Jean Mondot, an artisan from Limoges who had started, a small factory, in Exideuil making electric switches using porcelain and boxwood.\n" +
                "Manufacturing subsequently gradually diversified into electrical equipment. At that time, before the appearance of plastic, porcelain was the best available insulating material. In 1949, following a factory fire, the decision was made to concentrate exclusively on electrical wiring devices (switches and sockets). The company very quickly extended its range to cover protection products (fuse-holders and circuit breakers, etc.), cable management products (trunking and mini-trunking), emergency lighting, etc.\n" +
                "Since then, Legrand has made 120 targeted acquisitions becoming a large, diversified maker of electrical wiring devices with more than 150,000 product items. As of 2006, it has bases in 70 countries and sales in 180 countries.\n"+"\n"+"\n"+"\n"+"\n");
        industrialAndElectricityInfo.add("Duval Messien is an electrical firm that specialized in lightning protection and grounding.");
        industrialAndElectricityInfo.add("Franklin France is an electric company that manufactures lightning rods for lightning protection.");
        industrialAndElectricityInfo.add("Merlin Gerin is an electricity distribution company that founded in 1920. Merlin Gerin provides manufacture and supply of high, medium and low voltage products for the distribution, protection, control and management of electrical systems. It was acquired by the Schneider Group.");
        industrialAndElectricityInfo.add("Telemecanique Sensors is a sensor technologies brand that was founded in 1924.");
        industrialAndElectricityInfo.add("Square D is an American manufacturer of electrical equipment headquartered in Andover, Massachusetts. Square D is a flagship brand of Schneider Electric, which acquired Square D in 1991.");


        ArrayList<String> marine = new ArrayList<>();
        marine.add("Beneteau");

        ArrayList<String> marineInfo = new ArrayList<>();
        marineInfo.add("Beneteau or Bénéteau is a French sail and motor boat manufacturer, with production facilities in France and in the United States. The company is a large and recognized boat builder, commanding a substantial worldwide market. With the holding company Beneteau Group now also holding brands such as Jeanneau and its multihull subsidiary Lagoon since 1995.");


        ArrayList<String> finance = new ArrayList<>();
        finance.add("Societe Generale");
        finance.add("BNP Paribas");

        ArrayList<String> financeInfo = new ArrayList<>();
        financeInfo.add("Société Générale S.A., often nicknamed \"SocGen\" is a French multinational investment bank and financial services company headquartered in Paris, France. The company is a universal bank and has divisions supporting French Networks, Global Transaction Banking, International Retail Banking, Financial Services, Corporate and Investment Banking, Private Banking, Asset Management and Securities Services.\n" +
                "Société Générale is France's third largest bank by total assets, seventh largest in Europe or seventeenth by market capitalization. The company is a component of the Euro Stoxx 50 stock market index. It is known as one of the Trois Vieilles (\"Old Three\") of French banking, along with BNP Paribas and Crédit Lyonnais.\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        financeInfo.add("BNP Paribas S.A. is a French international banking group. It is the world's 8th largest bank by total assets, and currently operates with a presence in 75 countries. It was formed through the merger of Banque Nationale de Paris (BNP) and Paribas in 2000, but has a corporate identity stretching back to its first foundation in 1848 as a national bank. It is one of three major international French banks, along with Société Générale and Crédit Agricole. The group is listed on the first market of Euronext Paris and a component of the Euro Stoxx 50 stock market index, while it also included in the French CAC 40 index."+"\n"+"\n"+"\n"+"\n");


        ArrayList<String> foodProcessing = new ArrayList<>();
        foodProcessing.add("Group Doux");

        ArrayList<String> foodProcessingInfo = new ArrayList<>();
        foodProcessingInfo.add("Doux Group, founded in 1955 and headquartered in Châteaulin, Finistère (France), is a French food processing company in the industrial poultry production business, exporting poultry-based processed products. In 2014, it was ranked as the largest producer of poultry in Europe, and the third largest in the world.");

        ArrayList<String> hotel = new ArrayList<>();
        hotel.add("Ibis");
        hotel.add("Sofitel");
        hotel.add("Accor");
        hotel.add("Novotel");

        ArrayList<String> hotelInfo = new ArrayList<>();
        hotelInfo.add("Ibis (or Ibis Red; stylised as ibis) is a brand of economy hotels owned by Accor. Created in 1974, Ibis became Accor's economy megabrand in 2011 with the launch of Ibis Styles and Ibis Budget. As of December 2019, there were 1,218 hotels under the Ibis brand (excluding Styles and Budget hotels), with 155,678 rooms in total across 67 countries.");
        hotelInfo.add("Sofitel Hotels & Resorts are a chain of luxury hotels based in Paris, France, and owned by Accor since 1980.\n" +
                "\n" +
                "Founded in 1964 in France, Sofitel quickly developed worldwide to reach more than 200 properties. In 2008, Sofitel became a brand of luxury hotels only, downsized its property count to 89, and created new brands.");
        hotelInfo.add("Accor S.A. is a French multinational hospitality company that owns, manages and franchises hotels, resorts and vacation properties. It is the single largest hospitality company in Europe, and the sixth largest worldwide.\n" +
                "\n" +
                "Accor operates in 100 countries, with more than 4,800 hotels and 280,000 employees worldwide. Its total capacity is approximately 704,000 rooms. Accor owns and operates brands that cover every segment of hospitality, such as luxury segment (which includes Raffles, Fairmont and Sofitel), premium segment (which includes MGallery, Pullman and Swissôtel), midscale segment (which includes Novotel, Mercure and Adagio) and economy segment (which includes ibis and hotelF1). Accor also owns companies specialized in digital hospitality and event organization, such as onefinestay, D-Edge, ResDiary, John Paul, and Potel & Chabot.\n" +
                "\n" +
                "The company is headquartered in Issy-les-Moulineaux, France, and is a constituent of the CAC 40 index in the Paris stock exchange."+"\n"+"\n"+"\n"+"\n"+"\n");
        hotelInfo.add("Novotel is a midscale hotel brand focused on modern, natural and intuitive design, and owned by Accor, as well as the company's flagship property. Created in 1967 in France, the company grew into what became the Accor group in 1983, and Novotel remained a pillar brand of Accor's multi-brand strategy. According to 2018 datas, Novotel manages 492 hotels in 59 countries.");

        ArrayList<String> telecommunication = new ArrayList<>();
        telecommunication.add("Orange");

        ArrayList<String> telecommunicationInfo = new ArrayList<>();
        telecommunicationInfo.add("Orange S.A. , formerly France Télécom S.A. (stylized as france telecom) , is a French multinational telecommunications corporation. It has 266 million customers worldwide and employs 89,000 people in France, and 59,000 elsewhere. It is the tenth largest mobile network operator in the world and the fourth largest in Europe after Vodafone, Telefónica and VEON. In 2015, the group had revenue of €40 billion. The company's head office is located in the 15th arrondissement of Paris. The current CEO is Stéphane Richard. The company is a component of the Euro Stoxx 50 stock market index.\n" +
                "\n" +
                "Orange has been the company's main brand for mobile, landline, internet and IPTV services since 2006. It originated in 1994 when Hutchison Whampoa acquired a controlling stake in Microtel Communications during the early 1990s and rebranded it as \"Orange\". It became a subsidiary of Mannesmann in 1999 and was acquired by France Télécom in 2000. The company was rebranded as Orange on 1 July 2013."+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");

        ArrayList<String>  informatics = new ArrayList<>();
        informatics.add("Capgemini");

        ArrayList<String> informaticsInfo = new ArrayList<>();
        informaticsInfo.add("Capgemini SE is a French multinational corporation that provides consulting, technology, professional, and outsourcing services. It is headquartered in Paris, France. Capgemini has over 270,000 employees in over 50 countries, of whom nearly 120,000 are in India.");

        ArrayList<String> cigarette = new ArrayList<>();
        cigarette.add("Gauloises");

        ArrayList<String> cigaretteInfo = new ArrayList<>();
        cigaretteInfo.add("Gauloises, \"Gaul women\" in French (\"cigarette\" is feminine in French), is a brand of cigarette of French manufacture. It is produced by the company Imperial Tobacco following its acquisition of Altadis in January 2008 in most countries, but produced and sold by Reemtsma in Germany.");


        brandList.put("Kitchen",kitchen);
        brandList.put("Insurance",insurance);
        brandList.put("Hair Care",hairCare);
        brandList.put("Clothing",clothing);
        brandList.put("Supermarket",supermarket);
        brandList.put("Dairy Products",dairyProducts);
        brandList.put("Bottled Water",bottledWater);
        brandList.put("Automobile Industry",automobileIndustry);
        brandList.put("Infant Formula/Clothing/Toys",infantClothingAndFormula);
        brandList.put("Cosmetics",cosmetics);
        brandList.put("Perfume",perfume);
        brandList.put("Skin Care",skinCare);
        brandList.put("Construction",construction);
        brandList.put("Travel Industry",travelIndustry);
        brandList.put("Lighter",lighter);
        brandList.put("Sports Equipment",sportsEquipment);
        brandList.put("Fuel Oil",fuelOil);
        brandList.put("Pharmaceutical Company",pharmaceuticalCompany);
        brandList.put("Industrial / Electricity",industrialAndElectricity);
        brandList.put("Marine",marine);
        brandList.put("Finance",finance);
        brandList.put("Hotel",hotel);
        brandList.put("Telecommunication",telecommunication);
        brandList.put("Informatics",informatics);
        brandList.put("Cigarette",cigarette);
        brandList.put("Food Processing",foodProcessing);


        detailList.put("Kitchen",kitchenInfo);
        detailList.put("Insurance",insuranceInfo);
        detailList.put("Hair Care",hairCareInfo);
        detailList.put("Clothing",clothingInfo);
        detailList.put("Supermarket",supermarketInfo);
        detailList.put("Dairy Products",dairyProductsInfo);
        detailList.put("Bottled Water",bottledWaterInfo);
        detailList.put("Automobile Industry",automobileIndustryInfo);
        detailList.put("Infant Formula/Clothing/Toys",infantClothingAndFormulaInfo);
        detailList.put("Cosmetics",cosmeticsInfo);
        detailList.put("Perfume",perfumeInfo);
        detailList.put("Skin Care",skinCareInfo);
        detailList.put("Construction",constructionInfo);
        detailList.put("Travel Industry",travelIndustryInfo);
        detailList.put("Lighter",lighterInfo);
        detailList.put("Sports Equipment",sportsEquipmentInfo);
        detailList.put("Fuel Oil",fuelOilInfo);
        detailList.put("Pharmaceutical Company",pharmaceuticalCompanyInfo);
        detailList.put("Industrial / Electricity",industrialAndElectricityInfo);
        detailList.put("Marine",marineInfo);
        detailList.put("Finance",financeInfo);
        detailList.put("Hotel",hotelInfo);
        detailList.put("Telecommunication",telecommunicationInfo);
        detailList.put("Informatics",informaticsInfo);
        detailList.put("Cigarette",cigaretteInfo);
        detailList.put("Food Processing",foodProcessingInfo);


        expandableListView2.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                Intent intent = new Intent(MainActivity2.this,DetailsActivity2.class);
                intent.putExtra("productName",brandList.get(categoryList.get(i)).get(i1));
                intent.putExtra("productDetail",detailList.get(categoryList.get(i)).get(i1));
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
        menuInflater.inflate(R.menu.options_menu,menu);

        MenuItem  menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Search Product");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                final ArrayList<String> arrayList = new ArrayList<>();


                    for(String categories : categoryList) {

                        for(String products: brandList.get(categories)){

                            if (!s.isEmpty()) {
                                if (products.toLowerCase().contains(s.toLowerCase())) {

                                    arrayList.add(products + "  ("+ categories+")");

                                    expandableListView2.setVisibility(View.INVISIBLE);
                                }
                            }

                            else {
                                expandableListView2.setVisibility(View.VISIBLE);
                            }


                        }
                    }


                    final ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity2.this, R.layout.list_product,R.id.productText,arrayList);
                    listView.setAdapter(arrayAdapter);


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            StyleableToast.makeText(MainActivity2.this,"You can get detailed information about the product by clicking its name under the category.",R.style.exampleToast).show();
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

        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(MainActivity2.this,SettingsActivity.class);
            startActivity(intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeListener);
    }
}