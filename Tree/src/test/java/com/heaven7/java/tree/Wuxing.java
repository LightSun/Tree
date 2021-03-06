package com.heaven7.java.tree;

import com.heaven7.java.base.util.SparseArrayDelegate;
import com.heaven7.java.base.util.SparseFactory;
import com.heaven7.java.tree.util.PinyinUtils;
import com.heaven7.java.visitor.ResultVisitor;
import com.heaven7.java.visitor.collection.VisitServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汉字的五行
 */
public final class Wuxing {

    public static final int MARK_GLOD  = 1;
    public static final int MARK_WOOD  = 2;
    public static final int MARK_WATER = 3;
    public static final int MARK_FIRE  = 4;
    public static final int MARK_EARTH = 5;

    public static class Def{
        public int bihuaCount;
        public int mark;
        public String word;

        public Def(int bihuaCount, int mark, String word) {
            this.bihuaCount = bihuaCount;
            this.mark = mark;
            this.word = word;
        }
    }

    private static final SparseArrayDelegate<List<Def>> sGlodWords;
    private static final SparseArrayDelegate<List<Def>> sWoodWords;
    private static final SparseArrayDelegate<List<Def>> sWaterWords;
    private static final SparseArrayDelegate<List<Def>> sFireWords;
    private static final SparseArrayDelegate<List<Def>> sEarthWords;
    private static final Map<Character, Integer> sWuxingMap;
    private static final Map<Character, Integer> sBihuaMap;

    static {
        sWuxingMap = new HashMap<>();
        sBihuaMap = new HashMap<>();

        sGlodWords = SparseFactory.newSparseArray(8);
        sWoodWords = SparseFactory.newSparseArray(8);
        sWaterWords = SparseFactory.newSparseArray(8);
        sFireWords = SparseFactory.newSparseArray(8);
        sEarthWords = SparseFactory.newSparseArray(8);

        int bihua = 1;
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "一乙"));

        bihua ++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "匕刀人入厶"));
        //sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "匕刀人入厶"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "勹卜乜"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "刁丁二力了"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "又"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "才叉亍川寸千刃三上尸士夕小"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "干工弓丌及孑巾久口廿乞彡已"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "凡亡下子"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "彳大孓女勺巳乇幺弋丈之"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "己山土丸兀丫也尢于"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "仇乏戈仁仍冗少升什氏手殳四兮心刈仄爪"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "卞丐公勾介今斤亢孔木牛亓欠犬牙元月匀"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "巴比不歹反方分夫父互户化幻毛爿匹片攴壬卅水文毋勿"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "尺丑丹吊仃斗火井仂内日太天屯午爻仉支止中"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "厄切王卬夭尹引尤友予曰允"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "册叱斥出叼刊尻仟且仞申生失石史矢世仕市示甩司玊仙乍占正主"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "本尕甘功古瓜宄卉加甲叫句巨卡可叩卯疒巧丘囚去外未五仡玉札"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "半叭白包北必閟弁卟布匆兄弗付夯禾弘乎矛民皿末母仫目丕皮氕平叵仨玄疋印匝"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "丙代旦叨氐叮冬叻立尥令另奶尼奴冉他它田仝仗召只左"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "凹瓦戊矽央以永用由右幼孕仔"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "臣丞舛此次汆存丢而匈吏列任扔如色舌式守妁死寺夙凸刎西吸先囟旬曳再在早吒州舟字"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "朳朵尬各共乩吉伎*幵囝件交臼伉考匡夼企犰曲戎朽旭仰吁聿朱竹"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "扒百冰并凼伐刑行凶休帆泛犯仿妃份缶伏亥好合冱回米糸名牟仳牝乒收汀危向血汁"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "吃弛打忉氘多耳旮亘光尖匠她决旯老耒劣六甪氖囡年乓全肉同氽佤妄吆宅兆旨至仲自"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "吖安充地戍圪艮圭灰圾岌圮屺似吐圩仵伍戌伢羊伊衣圯夷亦屹因有宇羽圳"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "扱岔吵车成赤氚串吹忖兑束判七吣忍妊礽删劭卲佘伸身吮私伺佀兕姒宋忒吻伭辛秀序巡酉皂卮吱伫助妆壮孜走佐作坐"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "杓材岑杈床村杜呃杏伽改杆杠告更攻估谷庋呙囯旱何吼肓妓忌夹见角疖劫妗究局姖妜君佧扛壳克扣困伲你杞弃扦羌劬却杉我吴吾扤杌匣吓言吟杖"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "伴吧夿弝贝伻皀佊吡妣庇别兵伯孛吥步汊池呆形泛妨彷吠吩佛否呋孚甫汞佝含罕汗亨宏囫弧汲即江戒况冷忙尨每芈妙尿妞伾屁汝汕汜忘尾污汐希孝汛妤妘"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "犴呈辵呔甙但低弟佃甸玎疔盯豆妒囤吝伶免旰灸牢玏李里利良吕卵男呐佞弄努求忐忑町廷佟彤吞托佗妥巫妖佁占豸妐住灼姊足"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "坂岙岜坌辰妑坊坩均坎坑牡圻岐岍坍秃完位圬氙岘呀岈延冶矣佚役邑吲甬攸卣佑余欤玙址"));

        //8 9 10
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "侘昌抄弨扯忱承忡初垂佌刺儿姓庚刮戋金净侃刻孥妻戕青取叁刹姗疝尚舍社侁呻使始事受抒叔刷祀忪怂所兔昔穸些刖甾昃怎咋轧姃侄忮周妯咒宙侏抓宗卒"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "板昂枊杯杵东妸扼林杪杷芎枋斧秆杲疙供咕姑孤固呱乖官果杭忽昏肌亟佶技季佳肩艽佼届卺京纠赳玖疚居咀具卷咔咖忾抗肯空快侩狂枚艿呢杻枇其奇歧穹虬屈券枘松枉卧析呷欣厓兖杳宜礿枕枝竺杼"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "版扮姅岸八把爸扳攽孢卑屄沘彼畀忭拚汳汴表秉幷帛瓝沉沌幸汹泛房放非氛汾忿奉扶府咐阜侅冈汩卦沆呵劾和佫呼虎或泐盲牦没妹门氓孟汨宓明命殁沫侔姆沐牧忸扭狃抛咆庖呸沛佩帔朋批沏汔汽沁沙沈汰汪味汶沃武物弦冼享协忻沂雨沅咂沚状"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "哎佰长炒坼侈炊佽徂耷妲沓岱宕到的狄底玓典店耵定咚侗抖咄剁佴囹昉炅昊戽姐咎抉炕昆剌来佬肋例戾两冽呤坴侣仑旻奈侽呶妮念弩疟妾炔乳侍帑弢忝佻帖投罔昕炎佯易找折争知直制帙炙忠隹卓"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "艾坳垇坻坫爬帕矾附矸岣岵岬坷岢坤垃峁岷坶坭坢坯坪坡坦坨宛往旺委忤岫盱亚奄肴夜依抑佾咏呦侑於盂臾昀狁"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "臿查姹差拆怊伡疢宬怵穿舡春殂促毒度耏性钆宫剐剞刭俓枯前怯侵秋纫肜柔砂衫舢厍姺哂矧甚牲省施食室是首姝耍帅闩思娀叟俗剃祆籼庠削信星咻庥胥叙宣页钇俞俣哉咱昝蚤则眨咤怔咫峙肘拄拙咨姿俎昨怍"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "柲柄柴柢芏俄柃芇枹尜肝柑竿疳肛缸纥革虼哏狗枸牯故冠咣皈轨癸柜哄訇虹芨咭级急纪既枷叚架建牮姜姣皆界疥蚧矜劲扃九韭拘狙拒军看柯科咳客哐柳咯芒怩昵拈柈芃枰祈芑契恰芊俏俅酋畎芍柿柁柝芄玩侠狎柙相枵俆彦奕弈疫羿胤柚禺竽芋栅柘芝枳柱斫柞"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "拌疤拔拜保抱背祊甭泵毖扁窆拚便昪波泊勃哺怖沲法泠沔勉眇秒拍哌沭畈飞沸狒玢风封凫怫拂俘氟罘拊讣负泔沽哈孩河很红泓侯后厚狐怙徊奂宦皇虺哕计沮炬姥泪泖昴冒玫眉美昧虻咪弭泌咩玟抿泯抹哞某拇泥眅泮叛盼狍泡疱怦抨披毗姘品屏泼匍柒泣泅泉染娆泗沱咸香巷泄卸泫泶妍沿衍泱盈泳油沾沼治注"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "炳抶抽怛待怠殆眈抵帝酊订段祋盹盾哆哚拎赴拐曷烀咴姞柬炯玦俊拉厘俚俐怜亮咧律哪娜柰耐南怒虐炮炱泰炭畋殄亭突凃拖拓歪纨肟炫紃殃徉咬映昱怨灾炸招昭者贞政祉盅重纣胄炷籽秭耔奏"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "哀垵拗砭垞衩昶垤垌峒肚砘垛垩垡趴怕型垓垢垲砍奎盆砒垧哇娃威韦畏胃瓮屋侮砉峋押砑咽匽怏姚要咿怡咦姨舣姻音垠俑勇幽疣羑囿宥纡舁禹垣爰约窀垚玥"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "剥财睬仓敇豺刬伥倡鬯晁眧耖唓宸乘蚩持翅刍俶纯祠脆厝凋钉珐刚罡怪借峻钌倪钋剖倩挈邛讱轫衽狨辱弱珊闪讪剡扇哨射珅娠神眚师十时拾狩书纾殊衰拴素祟孙隼唆索恸剜紊唏息席掀宵笑修修訏徐玹痃眩殉栽宰奘唣哳痄窄钊针真畛疹拯症纸指酎疰拽酌租珇祖祚唑座"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "梆桉芭笆柏栢苄栟屙婀苊耙秫芳芬粉芙迀酐绀羔高哥格鬲哿根耕哽肱恭蚣躬拱贡玽股骨罟挂倌桄鬼桂衮核桁讧笏花桓恢唧姬屐笄笈脊记芰珈家痂恝兼豇狡讦拮桀芥衿肼弪径柩桕疽桔矩俱倨娟倦桊隽拷栲珂疴恪倥恐芤哭库框括栝栳栗匿臬芘栖芪耆岂起气虔肷芡衾芩芹祛拳缺桑芟栓凇笋桃桐砼桅芴奚哮校芯栩芽芫唁苡倚痈邕娱圄峪原纭芸笊祗芷桎株桌"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "舨氨粑呗班般豹趵倍倴畚秕俾舭毕珌髟俵病玻砵亳庯秤臭泚洞娥洱眠眄俳派肪纺舫肥匪肺纷峰俸服祓蚨俯釜害氦蚶邗函航蚝耗盍狠恨哼恒洪候祜洹洄恚活洎浃津酒洌洛马邙旄们勐洣敉秘珉秣毪亩纽畔袢旁配倗疲蚍拼娉俜洴玶珀哱圃凄讫迄洽洳洒*纱娑洮洼洧纹蚊务洗效胁绁泄屑恤洫恂洵训洋洇耘拶洲洙浊"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "哧耻翀娖玳耽疸紞岛倒娣玷爹瓞冻恫蚪趸哦恕耿烘恍晄疾晋珏倔烤朗烙哩娌俩凉料烈玲瓴凌留旅挛伦倮耄拿纳肭衲孬能娘恧衄秦恁朊芮蚋偌晒晌朔趿肽唐倘讨套特疼屉倜恬甜挑条庭挺徒彖庹挖挝倭乌娭夏畜烜讯迅秧烊窈舀旃展站珍朕肢值晊秩致舯衷冢罜祝倬笫恣"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "啊唉埃砹鹌俺按案盎敖芺峬城埕砥峨恩砝砩个埂埚砬埒埋砰破埔砌峭窃容埏砷堍砣娓軎翁唔阢峡轩蚜氩恹胭宴晏氧恙眙酏益殷氤蚓佑迂邘育彧眢员袁砸砟砧肫准"));

        //11- 15
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "偲彩参曹侧钗产娼常徜唱巢晨趻偁琤瓻匙豉舂崇紬偢处绌啜船钏玼疵瓷粗啐挫得钓孰邢钒副寂祭剪旌勘馗率捏钕佩阡钎氢圊悫雀蚺唼啥钐商捎绍奢蛇设赦绅胂售庶唰爽悚讼宿狻捅偷钍问悉欷觋徙细舷祥斜偰邤欣衅羞袖酗旋悦责舴扎蚱砦粘胗栀胝趾终昼珠蛀专着紫族组胙做"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "茇苞苯笨苾梐彬梣茬趁笞茌崔笪笛兜轭啉苓偶术梵芾苻桴符苷敢舸梗珙苟笱蛄蛊梏牿胍规匦国悍捍偈寄笳袈戛蛱胛徦假坚枧趼健皎教秸婕堇近婧竟救苴趄苣捐眷桷捃胩康苛氪啃寇苦梡眶悝盔悃捆婪笠茅茆茂梅苠茉苜旎苤笸启乾悄茄卿顷筇蚯区蛆朐娶悛圈痊苒若啬苫梢苕笙倏梳笥崧桫梭苔梯笤梃桶偓梧悟晤狭厢枭偕械许珝研厣眼悒挹翊茚英唷圉庾苑笮苎棁茁梓"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "绊邦浜捌豝胈败胞狈被偝偪逼闭狴婢庳敝贬匾徧彪婊邠斌浡舶捕涔唇讹冕喵苗徘婞訩返贩访啡酚唪趺麸绂绋浮匐艴妇够海酣浛毫浩盒蚵痕珩唿唬扈瓠患凰悔彗晦婚货洚浸泾涓浚邟浪流麻麦脉曼袤浼眯觅密敏眸涅胖脬袍匏胚旆烹啤偏殍票贫婆粕浦渠涩涉涑挲涕涂涒晚望偎浯浠习涎消邪挟虚雪珢涌鱼雩浴浙浞"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "欸捭蛃晡眵敕从凑绐带袋聃胆啖蛋盗羝顶啶动敚舵厄珥烽晗焓焊斛将狷诀觖翋徕狼勒梨狸犁猁珕唳粒梁聊羚翎聆蛉娄卤鹿略囵捋珞那婥讷您胬戚软晟胎酞贪袒啕剔悌粜烃停珽屠豚唾娲袜烷挽焐曦烯珣珧斩张章帐啁侦振执痔窒舳捉啄眦偬"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "挨庵唵埯崩埠堾埭岽硐堆啪硎岗硌崮硅崞胡基崛崆堀崃硭埝鸟培堋埤崎畦牵埽眭堂窕眺婉唯帷伟尉迕捂牾硒崤勖琊崖哑痖讶迓垭娅崦焉闫偃痒野痍移异埸翌狺寅迎茔庸恿悠蚰蚴狳域欲峥埴蛭"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "钣钯钸猜裁残伧厕嗏觇孱猖惝敞怅钞超朝抻牚胵啻惆喘窗创捶词猝酢悴毳皴嵯痤矬措貂掉钭堵钝贰疏舒黍述胸钫琈钙割钩辜壶戟绝钧竣剀钠甯钮掊裒钤钦禽情氰然韧绒伞散丧扫嫂痧跚善稍邵猞畲肾胜甥盛剩视授瘦税顺舜丝斯俟竦嗖酥诉粟飧睃钛替童推惜傒晰犀粞舾饩舄羡象琇须婿絮喧绚喻钥凿枣迮曾喳诈掌诏挣狰帧脂殖絷轵众蛛贮注兹诅尊阼"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "棒笔草策茶枨茺楮篅荈棰茈茨答等第棣迭栋筏棉荇悻棼茯尴皋胳袼给茛轱觚酤诂雇棺贯胱晷贵棍聒猓椁皓闳喉荒茴荟嵇极戢棘殛集几掎悸迦袷跏间犍荐绛茭椒蛟绞窖喈街杰结筋荩惊阱景痉窘啾厩掬椐莒讵惧距犋掘珺喀开凯闶钪轲棵控筐贶傀喟蛞棱荔椋络荦荬茗猊棚椑期栖欺祁棋掐掮茜嵌强羟乔邱球诎蛐荃筌荏茸茹阮森筛耜覃棠珶茼统筒椭皖稀厦筅琄荀桠雅掩雁尧傜荑椅茵硬哟驭饫寓栈棹植茱椎兹棕最"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "阪湴绑跋傍棓报悲邶备贲絣琫诐邲弼皕赑揙猋幖邴啵博跛钚瓿淳淙淬淡发淋幂黾描淼排牌淑涬雄番邡防婓扉淝悱斐粪冯稃跗涪袱幅复傅富淦蛤涫胲顸邯涵寒喊琀绗淏诃喝涸訸贺惚淮唤徨蛔惠混耠惑渐荆涞凉淩渌沦傌买茫蛑贸帽媒媚寐扪闷猛脒闵淖跑彭捧邳痞胼评迫普淇浅清渃脎深涮淞淌淘添淟涴雯涡无淅喜闲现项淆徇涯淹液欹淤淯渊云粥涿淄"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "掰焙采场焯掣程塍嗒傣贷单氮悼登迪觌诋邸睇掂阽惦跌喋耋痘短惇敦掇迩焚钬焦接嗟晶就厥吭焜啦喇琅稂劳喱犂理傈痢詈晾量捩裂趔琌琉硫虏脔掠抡捺喃赧婻捻傩晴闰婼邰跆毯探掏啼腆掭祧迢贴婷痛饨跎酡惋惘喔窝幄欻寻循巽焱蛘轺轶媛哲蛰诊轸证臸彘智痣轴粢"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "捱媕啽晻胺媪傲奡堡堛嵖砗堤奠堞恶费黑堠画黄堪喹岚塄嵋垴蛙崴为围帏惟喂硪婺痦翕硖翔硝砚堰崵崾揶掖猗壹诒迤贻胰诒喑堙喁釉嵛鼋粤越崽跖"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "铋钵钹铂踩粲恻插诧琛嗔脭絺饬傺愁稠酬蜍楚揣歂蠢跐琮催瘁搓脞钿毹暑鼠蜀钴郝钾剿捷靖钜钶铃铆钼铌刨剻铍钷铅钳蜣惬嗪跫饪揉塞搔琗裟歃煞伤艄蛸诜蜃诗邿狮势试轼睡嗍嗣肆送搜肃嗉睢岁嗦羧唢铊钽酮剸媳郄酰嫌蚬跣详想绡新歆惺猩貅绣嗅煦揎暄铉驯询铀铕愉揄蝓伛钰裕愈钺载贼揸闸债斟钲睁黹酯诛邾瘃庄装琸资揍傶阻"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "嗄荸箅筴搽猹槎茝椿榱戥荻椴莪蛾愕琳娩筢竖枫莩该陔赅戤概感干筻嗝塥跟绠诟彀痼诖琯诡跪嗬荷猴逅畸嫉楫麂荚嫁拣笕减楗毽酱郊跤脚敫揭诘睫解仅禁靳经茎睛胫敬迥揪舅琚雎榉绢筠揩慨楷戡莰稞窠嗑筘窟夸蒯诓揆暌琨髡廓莨楞莉莅廉楝莽莓楣募楠逆睨榀莆颀琦琪祺佥愆箝呛樯愀琴勤倾楸诠辁裙群嗓莎筲椹筮颂荽莛荼莞莴斡珷皙暇莶苋楔歇莘楦靴傿筵罨杨椰业义肄楹莜莸莠愚榆瘐预御楂桢孳罪"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "颁斑斒靶稖雹陂琲迸哔愊愎痹辟惼飑禀摒脖渤补测滁渡渺琶湃饭沨蜂脯溉港傼颃嗥号合貉郈轷湖猢琥郇换涣豢惶湟挥晖汇会贿喙毁浑贾湔茳湫较粳鸠渴雷粱妈吗湄猸渼盟迷湣愍酩莫貊湳脲湓傰琵媲睥犏剽聘郱瓶裘惹绥汤琠湍微湋渭渥熙湘渫溆渲湮渶游渝郁渣湛浈滞渚煮"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "稗煲煏煸裎嗤媸驰传搭靼迨亶当砀嗲电殿揲牒鼎督煅顿躲惰跺赁烦觥煳焕煌晃幌诙迹煎睐啷廊酪诔傫愣蜊炼零旒偻赂辂琭禄路乱煤睦乃揇猱恼农暖逄稔塔痰逃绨提跳蜓艇嗵退煺蜕脱驮陀顽脘畹煨炜蜗熄煊烟琰扬炀阳徭虞煜詹盏照罩蜇郅置雉追惴琢赀觜趑訾"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "阿矮爱嗌揞暗嗷嶅廒奥碑碚碘碉碇碓痱话觟嵴碱块跬袅硼圣嵊嵩塑碎塌塘填琬碗嵬琟猥痿艉猬温嗡握呜蜈坞诩勋埙睚衙揠蜒爷揖饴诣意裔饮佣雍蛹犹猷瘀琙园圆援塬氲恽晕愠轾稚嵫"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "铵艑彩嘈察瘥僝嫦玚綝称诚铖酲铳搊绸裯殠搐搋僢怆慈雌粹翠铞铥睹铒罚阀署铬铪划铰精聚劂铐铑闾铭陧齐綮慊戗腔抢劁寝蜻铨逡认铷瑞搡瘙铯僧绱韶赊慎酾逝誓寿绶陎说搠嗽诵嗾瞍速愫僳觫酸狲损铫铜僮骰途酴僖蜥铣屣郤禊衔线限像逍需睻铘铱劓铟银腴瑜窬臧造啧帻甄赈蜘摭碡帚铢综腙粽僔"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "榜菝菢菶萆菜菖苌尝篪椽菙萃萏菪凳摁箅菽菲榧菔嘎盖赶纲睾膏搞槁诰郜歌搿个郠构菇菰箍鼓褂管逛绲帼蜾菡赫瘊槐萑夥箕暨跽嘉郏瘕笺菅搛戬僭降僬侥饺酵截竭诫骱紧廑菁腈兢迳僦裾菊榘皲菌郡犒裉箜骷酷筷魁睽匮愧匮莱榔菱榴杩樠萌墓幕萘槈裴菩桤萋嘁萁旗綦绮葺搴歉枪敲侨诮箧轻箐逑赇巯蜷绻榷荣榕箬瑟槊菘算榫榻萄萜菟菀伪萎菥箫榍榭菸厌酽疑瘗蜴萤郢萸语妪箢瑗愿菑榨寨肇榛筝菹"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "搬蝂饱悖绷嘣鼻币滗裨弊碧馝稨滮裱宾菠驳搏僰箔逋沧滀涤滇嘧蜜绵瞄熊绯蜚腓翡偾疯逢凤孵郛福辅腑滏腐阁沟嘏寡滚嗨豪滈阂菏瑚华滑猾痪滉珲诲魂溷祸溘滥漓溧溜犸唛嘛幔髦瑁瞀么酶艋蜢灭闽鸣冥溟暝嫫麽陌寞溺滂搒脾罴蜱嘌嫖萍颇仆溥蜞溱溶溽飒饲溲溯溻溏滔网瘟闻郚舞郗溪携溴踅熏窨溢荥源滋滓"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "熬畅尘逞瞅绰瘩捣嘀嫡递腚胨郖逗端对裰夺尔粼裹伙奖尽恺瘌辣罱郎嫪嫘酹嘞嫠奁连踉僚寥廖绫领熘绺喽陋绿纶裸雒瑙嫩宁喏搦炝熔煽裳台态摊叹搪耥趟慆慝滕逖惕裼舔蜩通透图团箨蜿绾腕诶鞅疡摇荧毓搌绽嫜彰胀幛赵肈这祯志种逐缀缁"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "肮獒塝碥碴墋诞碲垫碟呕塾砜闺监碣境逵壸墚嵝墁碰堑岖墒墅硕碳维玮诬误寤瑕顼嘘墟碹腌嫣耶腋祎旖夤瑛墉踊诱与鸢冤猿殒翟崭嶂坠"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "锕钡惭惨艖掺掺婵鋋廛谄肠厂麨瞋漦瘛冲摴厨锄諔嘬疮摐噇踳赐醋摧锉嘟锇熟陉锋敷锆刽刿铗缄剑节靓锔剧锞刳锒锂锍锊劈铺噙锓请趣髯糅锐腮毵磉傻陕殇赏审谂实蚀驶奭艏数腧摔谁咝缌嘶驷艘螋谇琐锑铤腿鋈嘻陷腺线哓销锌腥锈绪儇缊糌驵噪锃稹帜陟挚皱嘱翥幢谆诼踪诹陬"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "葆萹箯标槽箣郴樗枞葱稻噔蒂腭面耦葩樊葑橄稿葛赓巩谷广妫瑰郭掴荭篌糇葫槲篁蝗叽缉赍稽瘠挤稷葭价驾稼俭翦贱腱箭僵桨娇胶噍颉羯槿儆獍阄驹屦踞蒈慷靠颏瞌蝌课缂抠侉宽款诳葵醌阃楼模篇葡槭葜悭椠庆穷茕萩蝤葚枢葰樘葶葳苇蒍妩葸瞎贤缃葙箱蝎萱样仪谊毅莹媵萭窳葬樟箴荮著箸醉"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "瘢魬鲃魃罢褒鸨褓暴辈褙骳奔陛腷駜髲编蝙褊缏麃摽憋饼葧踣餔部漕浐蝽漘醇滴凛缅缈沤幡范鲂诽肤幞蝠驸赋腹蝜蝮澉缑缑盥虢憨汉撖颌褐滹浒沪哗踝逭漶辉麾慧浆漤涟漏漉履落玛码祃劢卖鞔满慢漫漭猫蝥貌霉魅庙缗瞑摸摩漠墨慕暮蒎盘醅赔喷嘭郫陴翩漂魄噗漆憩渗漱漽霆万逶嬉虾饷霄勰写漩演漾漪颍渔漳涨震渍"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "皑僾熛噌层彻踟齿憏憃除褚踔逴辍腠褡逮儋弹德敌骶缔踮调蝶董陡缎饵噢缓践瑾进摎噘赉阆唠乐黎厉练谅辆嘹寮撂刘瘤搂鲁逯戮虑轮论脶骆熳鼐腩蝻脑闹馁辇侬驽挪僻热熵踏骀瘫谈郯赕羰瑭躺铽踢缇髫抟褪驼腽辋腰瑶熠熨暂摘獐账辄摺阵鸩征诤质觯肿驻缒禚辎"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "腤鞍璈墺嶓嶒墀磁磋嶝墩堕嶙欧殴怄废坟磙嘿糊蝴峤磕蝰崂磊碾嬲磐嵚确豌纬诿卫慰庑娴鞋糈鸦养噎叶靥亿逸影慵忧邮鱿蝣牖谀缘院阅增磔徵"));

        //16 - 20
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "锛餐憯穇舱糙蹅侪幨阊氅鋹谌踸赪褫憧瘳踹遄陲锤輴錞糍璁璀蹉鹾错锝雕锭鄃输醒辐钢锢锪辑锦静锯锩锟铼钔锰穆锫凭錡钱锖嫱揿瘸蹂儒缛褥睿噻嬗膳蛳撕稣锬颓羲螅阋锨髹谖谒逾觎谕憎甑瘵战缜铮整郮绉诸麈撞锥锱郰撙"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "蓓荜筚蓖篦蔀篰苍橙篘莼笃饿谔鄂阏萼遏噩蒽树篚噶篙糕缟膈骼鸪毂掼龟辊过蒿嚆横黉骺鲎桦慌隍荤机畿墼蒺剂冀髻颊缣蒹谏踺强耩犟挢徼缙噤颈憬橘举踽窭鄄橛麇瞰眍裤哙窥愦篥梦蒲朴器褰黔橇桥憔樵撬鞘亲擒檎螓檠磬遒鼽糗趋鸲磲桡蓉蓐鄀穑蒴蒜荪蓑蓊樨县橡筱啸谐蓄阎魇谚窑缢荫萦蓥嬴颖阈遇圜樾蓁蒸筑篆嘴樽"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "办澳鲅鲍虣惫糒甏嬖觱鮅鞞遍辨瘭傧拨饽播鲌膊馞潺潮澈澄霖霏奋愤讽抚鲋骸骇颔翰翮醐寰遑潢讳阍馄霍涧浇洁噱溃潦涝蚂骂瞒螨醚悯螟瘼默谋霓凝潘螃耪陪霈澎膨骈蹁谝瓢瞟撇瞥频鲆扑氆潜润撒霎潸潲渑澍澌潭烫潼隈沩涠阌宪廨兴学浔鄅沄"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "撤陈撑鸱炽俦辏撮达殚掸惮导道灯谛谍蹀都赌憝吨炖遁踱廪燔积撅獗赖褴螂捞擂缡璃罹历琏撩獠燎陵遛龙瘘卢陆录焖遖挠鲇哝诺逎燃烧遂鲐昙糖螗绦陶蹄醍头暾鸵橐熹晓璇谑焰鸯晔烨燚燠璋瘴赭臻踵猪撰赘谘髭燊"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "嗳嫒谙聱螯懊磅壁碜瓯惯衡垦垮磨碛墙融坛违谓怃歙遐鸭阉燕噫颐峄殪阴壅馀豫鸳螈运郓酝砖"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "锿嚓擦縩操艚螬懆馇锸禅偿韔鼌帱憷歜黜膗聪独镀锻锷锅锾徽蹇饯键骏锴链镅镁縻鍪遣跄锹锲嚅孺鄏赛糁缫擅声谥蟀瞬锶耸锼谡虽隋缩锡膝蟋谿戏鲜痫猃馅谢鸺逊翼舆糟罾铡斋毡锗鍼锺诌瞩总邹"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "蒡蔽檦檗蔡柽苁蔟簇档瞪懂蔸篼檩擀鸽篝购媾鸹馆簋蝈馘癀桧豁击玑激哜觊艰鞯捡检謇讲蒋鲛矫阶鲒鞠鞫据飓糠颗髁恳蔻挎狯亏栏檑莲联敛蓼蒌篓簏蔓懋甍蔑篾茑蓬蹊谦瞧擎罄麯蕖阒篸蔌簌檀蔚檄蓰辖罅芗魈蓿蔫檐营狱岳箦蔗栉赚桩"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "癍帮谤跸臂褾豳擘澹点淀谧璠繁鼢缝缚醢鼾韩憾撼嚎鸿觳浣擐璜隳浍诨阔蒗澧濂潞嬷缦蟒蟊弥谜糜摹膜浓蟠貔缥螵嫔皤璞霜潚濉禧霞乡鲞亵懈獬鲟澡泽澶"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "暧餲灿龀瞠骋黛担瘅挡蹈队鸸鲕临瞵磷懔烩绩琎爵阑痨缧儡励隶裢殓魉疗隆耧蝼缕鸾螺麋缪黏咛騃燧遢蹋饧膛螳醣誊嚏瞳疃臀襄燮谣遥繇燥择辗蟑褶鸷膣螽烛纵"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "癌隘闇鮟醠遨謷磴礅岭鲑壕壑磺矶礁圹硗嵘闱鲔邬压阳嶷忆怿翳应婴膺拥优黝隅屿辕远龠郧"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "翱鏊镑鎞饆璨蝉繟儭艟懤雏幮储蹙窜擤鄜镐镉环秽劐镓铠镏镎聂啮镍拧狞阕阙镕鞣铩缮蟮觞婶双飕锁钨雟燹蟓镒遭赜缯膪镇织颛鬃"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "槟檫蒇槌箪簦簟蕫鹅额颚蔬蕃搁隔鲠遘觏鹄瞽归鲧簧蟥蕙获犄蕺虮鲫鲣睑裥简谫槛糨蕉谨觐旧瞿鹃蕨骒蒉篑聩拦瞢拟腻柠骐骑荨襁鄡荞窍翘苘躯璩觑鬈荛绕蕤蕊梼檮隗魏芜黠蕈颜蝇鹆蜮簪蕞"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "鼥鞴鄙毙濞髀奰鞭飚蹩滨摈殡袯鹁馎鵏闯荡翻黻赙覆馥濠阖鄠鲩缋蟪济谩鄤鄚朦鄍谟馍貘泞蹒蟛癖鯆濮濡鲨湿穗涛潍隙獯滢杂濯"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "痴虫戳丛戴焘鞮癜断怼膦懦曙丰烬醪耢鹂厘礼鲤粮缭噜侓辘璐谬蛲耨适抬鹈题阗餮魍曛曜瞻障遮谪职贽掷踬转骓擢"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "碍瑷盫袄蹦璧础讴硷礓垒韪鄢医黟彝癔鄞鄘鼬陨韫"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "鏖镚镖襜蟾铲鲳惩迟宠畴辞蹴祷镝颠牍犊锘摅璹镜铿鎏镂镘锚镆迁锵谯鲭鹊颡*臊膻鄯绳识兽馊擞镗醯系暹馐选镟赞錾躁谮鲰镞遵"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "薜簸橱薋蹬椟薯臌关犷薅薨蕻谎讥蓟缰缴轿醮襟馑鲸鬏绔胯脍旷鲲扩蕾枥栎橹麓难鲵攀麒髂签蔷跷缲麴黢醛萨薇蕹萧肖撷薤蟹薪薛赝遗蚁薏蓣籀"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "瓣鹎襞瀌瘪薄醭簿瀍鹑渎湎鹋蹯鲱羹鞲鲴缳绘嚯溅猎浏泺懑懵蠓祢靡鹏骗鄱谱蹼瀑扰渖雾泻霪"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "薆摆爆蹭嘲蛏歠骴哒裆邓鲷鸫胴蹲遴辚齑际谲蹶羸类离丽呖帘臁蠊脸裣辽邻龄鲮馏咙撸庐氇蠃蟆鲶撵脓庞曝蹻烁谭韬鼗玺鄩绎赠鄣辙郑骘鲻"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "爊礤坏疆坜垄垅垆稳鹉骛嬿臆繶臃韵"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "镡犨触鰆镫镦鏻鐇镄铧鏸锏鐎镢鐍锎铹镣聍镤镨缱镪黥謦铣蝾襦繻蠕鳃馓鳋骟释孀铴璺霰馨续译谵钟驺鐏"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "藊藏榇筹篡鹗鳄藐藁鳇攉蠖籍继舰藉警竞龃遽醵觉阚喾跨郐纩馈蓝篮栊栌檬篷脐蛴荠骞琼鳅劝薷薹牺献悬薰严邀议橼黦槠橥纂"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "膀龅鲾避襣躄鳊辫穮缤濒蠙膑黼鳆瀚鹕怀还瀖濑沥泷泸鬕迈颟馒鹛蠛魔譬嚷瀜邂瀣潆瀛潴"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "宝阐郸党鲽窦糯嚼矍懒黧醴疠骝胧拢炉掳罗飘赡獭挞腾龆鼍曦耀赢瓆躅"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "骜巉矿岿砾壤鼯鹜邺瘾嘤罂"));
        //21 - 30
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "鐾骖鸧虿搀忏羼韂衬铛躇呲鹚骢铎镌镭镰鏴嗫谴蛩麝随隧邃铁燨险镱儹踯属镯鄹"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "藨藕鹘顾鳏颢饥鸡歼鹣茧赆夔藜鞒驱饶薮藤嚣药艺龈莺樱蕴"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "黯霸辩骠膘飙襮腼邈藩瀿瀵鹤轰护瀐澜潋露猕霹鼙鳍瀼攘潇醺瀷瀹灂"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "缠踌跻爝腊蜡癞斓览烂累俪疬珑髅瓐骡曩鳎鲦鳐鹞灶啭馔龇"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "鳌礴蠡砺砻碌礞巍撄誉跃"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "镔镲躔冁摛蹰撺镬鉴慑袭隰骁癣骣铸"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "蔼蔺龚瓘蘅篯骄邝籁苈茏笼芦蘑孽苹蕲氍权苏俨瘿龉鬻"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "边鳔鳖沣灌骅欢獾霁霾鳗艨鳘耱瓤穰滠响飨鳕藻"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "颤龊籴叠读驎赎龛邋鳓粝鲢躐鹨聋癃窿胪舻孪囊摄傥饕听弯鹧"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "巅鸥峦懿隐璎鳙饔"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "镳黪髑镥銎颥鳝铄鸶髓鼷鱚纤鹇攒脏齄鳟"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "欑蘩鳜藿鹪鹫蠲兰蔹椤蓦蘖蘧癯藓鼹验驿鹬"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "鷩变鲼鬟禳滩"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "雠鳞麟蛎恋鹩轳栾猡体显"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "娈岩缨"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "镈蚕谗谶矗鑫瓉瓒骤"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "霭簖赣羁搅蓠篱酿衢龋鹰攥"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "蚌髌鬓辔颦躞"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "螭鞑癫蠹灵攫谰鳢雳陇鹭让闼厅龌鳣"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "坝罐盐艳呓"));
        bihua++; //25
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "鑶镵鲿蹿镧蹑璛镶赃"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "观鲚髋榄萝箩蘸缵"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "灞酆灏灏鹱漯蛮蘼襻"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "叆纛揽鬣酃颅摞囔"));

        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "馋镩骥镊跹趱"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "蠼躜"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "湾"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "逦郦驴逻瘰攮"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "黩銮锣颞钻"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "颧谳"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "滦缬"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "谠缆鸬鲈骧"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "齼"));
        sWoodWords.put(bihua, createWords(bihua, MARK_WOOD, "笾戆棂"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "滟戅"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "魑轹跞"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "鹦"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "镙"));
        sFireWords.put(bihua, createWords(bihua, MARK_FIRE, "躏骊"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "鹳"));
        bihua++;
        sGlodWords.put(bihua, createWords(bihua, MARK_GLOD, "爨"));
        sWaterWords.put(bihua, createWords(bihua, MARK_WATER, "骉"));
        sEarthWords.put(bihua, createWords(bihua, MARK_EARTH, "鲡馕"));
    }

    private static List<Def> createWords(int bihuaCount, int mark, String words) {
        List<String> actWords = new ArrayList<>();
        //only use simple chinese
        for (char ch : words.toCharArray()){
            if(PinyinUtils.isSimpleChinese(String.valueOf(ch))){
                actWords.add(String.valueOf(ch));
            }
        }
        return VisitServices.from(actWords).map(new ResultVisitor<String, Def>() {
            @Override
            public Def visit(String s, Object param) {
                return new Def(bihuaCount, mark, s);
            }
        }).getAsList();
    }
    public static int getMark(char word){
        Integer bihua = sWuxingMap.get(word);
        if(bihua != null){
            return bihua;
        }
        Def def = findWord(sGlodWords, word);
        if(def == null){
            def = findWord(sWoodWords, word);
        }
        if(def == null){
            def = findWord(sWaterWords, word);
        }
        if(def == null){
            def = findWord(sFireWords, word);
        }
        if(def == null){
            def = findWord(sEarthWords, word);
        }
        bihua = def != null ? def.mark : -1;
        sWuxingMap.put(word, bihua);
        return bihua;
    }
    public static int getBihua(char word){
        Integer bihua = sWuxingMap.get(word);
        if(bihua != null){
            return bihua;
        }
        Def def = findWord(sGlodWords, word);
        if(def == null){
            def = findWord(sWoodWords, word);
        }
        if(def == null){
            def = findWord(sWaterWords, word);
        }
        if(def == null){
            def = findWord(sFireWords, word);
        }
        if(def == null){
            def = findWord(sEarthWords, word);
        }
        bihua = def != null ? def.bihuaCount : -1;
        sWuxingMap.put(word, bihua);
        return bihua;
    }

    private static Def findWord(SparseArrayDelegate<List<Def>> sa,char word) {
        int size = sa.size();
        for (int i = 0 ; i < size ; i ++){
            for (Def def : sa.valueAt(i)){
                if(def.word.charAt(0) == word){
                    return def;
                }
            }
        }
        return null;
    }

    public static List<Def> getWords(int bihuaCount, int mark){
        if(mark == 0){
            List<Def> list = new ArrayList<>();
            list.addAll(sGlodWords.get(bihuaCount));
            list.addAll(sWoodWords.get(bihuaCount));
            list.addAll(sWaterWords.get(bihuaCount));
            list.addAll(sFireWords.get(bihuaCount));
            list.addAll(sEarthWords.get(bihuaCount));
            return list;
        }else {
            List<Def> list;
            switch (mark){
                case MARK_GLOD:
                    list = sGlodWords.get(bihuaCount);
                    break;
                case MARK_WOOD:
                    list = sWoodWords.get(bihuaCount);
                    break;
                case MARK_WATER:
                    list = sWaterWords.get(bihuaCount);
                    break;
                case MARK_FIRE:
                    list = sFireWords.get(bihuaCount);
                    break;
                case MARK_EARTH:
                    list = sEarthWords.get(bihuaCount);
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
            return  list;
        }
    }
    /*
     * 中国汉字五行-查询（按笔画）
     *
     *      一画
     *       五行属“土”的字有：一乙
     *
     *       二画
     *       匕刀人入厶
     *       五行属“水”的字有：勹卜乜
     *       五行属“火”的字有：刁丁二力了
     *       五行属“土”的字有：又
     *
     *       三画
     *       才叉亍川寸千刃三上尸士夕小
     *       干工弓丌及孑巾久口廿乞彡已
     *       五行属“水”的字有：凡亡下子
     *       五行属“火”的字有：彳大孓女勺巳乇幺弋丈之
     *       五行属“土”的字有：己山土丸兀丫也尢于
     *
     *       四画
     *       仇乏戈仁仍冗少升什氏手殳四兮心刈仄爪
     *       卞丐公勾介今斤亢孔木牛亓欠犬牙元月匀
     *       五行属“水”的字有：巴比不歹反方分夫父互户化幻毛爿匹片攴壬卅水文毋勿
     *       五行属“火”的字有：尺丑丹吊仃斗火井仂内日太天屯午爻仉支止中
     *       五行属“土”的字有：厄切王卬夭尹引尤友予曰允
     *
     *       五画
     *       册叱斥出叼刊尻仟且仞申生失石史矢世仕市示甩司玊仙乍占正主
     *       本尕甘功古瓜宄卉加甲叫句巨卡可叩卯疒巧丘囚去外未五仡玉札
     *       五行属“水”的字有：半叭白包北必閟弁卟布匆兄弗付夯禾弘乎矛民皿末母仫目丕皮氕平叵仨玄疋印匝
     *       五行属“火”的字有：丙代旦叨氐叮冬叻立尥令另奶尼奴冉他它田仝仗召只左
     *       五行属“土”的字有：凹瓦戊矽央以永用由右幼孕仔
     *
     *       六画
     *       臣丞舛此次汆存丢而匈吏列任扔如色舌式守妁死寺夙凸刎西吸先囟旬曳再在早吒州舟字
     *       朳朵尬各共乩吉伎*幵囝件交臼伉考匡夼企犰曲戎朽旭仰吁聿朱竹
     *       五行属“水”的字有：扒百冰并凼伐刑行凶休帆泛犯仿妃份缶伏亥好合冱回米糸名牟仳牝乒收汀危向血汁
     *       五行属“火”的字有：吃弛打忉氘多耳旮亘光尖匠她决旯老耒劣六甪氖囡年乓全肉同氽佤妄吆宅兆旨至仲自
     *       五行属“土”的字有：吖安充地戍圪艮圭灰圾岌圮屺似吐圩仵伍戌伢羊伊衣圯夷亦屹因有宇羽圳
     *
     *       七画
     *       扱岔吵车成赤氚串吹忖兑束判七吣忍妊礽删劭卲佘伸身吮私伺佀兕姒宋忒吻伭辛秀序巡酉皂卮吱伫助妆壮孜走佐作坐
     *       杓材岑杈床村杜呃杏伽改杆杠告更攻估谷庋呙囯旱何吼肓妓忌夹见角疖劫妗究局姖妜君佧扛壳克扣困伲你杞弃扦羌劬却杉我吴吾扤杌匣吓言吟杖
     *       五行属“水”的字有：伴吧夿弝贝伻皀佊吡妣庇别兵伯孛吥步汊池呆形泛妨彷吠吩佛否呋孚甫汞佝含罕汗亨宏囫弧汲即江戒况冷忙尨每芈妙尿妞伾屁汝汕汜忘尾污汐希孝汛妤妘
     *       五行属“火”的字有：犴呈辵呔甙但低弟佃甸玎疔盯豆妒囤吝伶免旰灸牢玏李里利良吕卵男呐佞弄努求忐忑町廷佟彤吞托佗妥巫妖佁占豸妐住灼姊足
     *       五行属“土”的字有：坂岙岜坌辰妑坊坩均坎坑牡圻岐岍坍秃完位圬氙岘呀岈延冶矣佚役邑吲甬攸卣佑余欤玙址
     *
     *       八画
     *       侘昌抄弨扯忱承忡初垂佌刺儿姓庚刮戋金净侃刻孥妻戕青取叁刹姗疝尚舍社侁呻使始事受抒叔刷祀忪怂所兔昔穸些刖甾昃怎咋轧姃侄忮周妯咒宙侏抓宗卒
     *       板昂枊杯杵东妸扼林杪杷芎枋斧秆杲疙供咕姑孤固呱乖官果杭忽昏肌亟佶技季佳肩艽佼届卺京纠赳玖疚居咀具卷咔咖忾抗肯空快侩狂枚艿呢杻枇其奇歧穹虬屈券枘松枉卧析呷欣厓兖杳宜礿枕枝竺杼
     *       五行属“水”的字有：版扮姅岸八把爸扳攽孢卑屄沘彼畀忭拚汳汴表秉幷帛瓝沉沌幸汹泛房放非氛汾忿奉扶府咐阜侅冈汩卦沆呵劾和佫呼虎或泐盲牦没妹门氓孟汨宓明命殁沫侔姆沐牧忸扭狃抛咆庖呸沛佩帔朋批沏汔汽沁沙沈汰汪味汶沃武物弦冼享协忻沂雨沅咂沚状
     *       五行属“火”的字有：哎佰长炒坼侈炊佽徂耷妲沓岱宕到的狄底玓典店耵定咚侗抖咄剁佴囹昉炅昊戽姐咎抉炕昆剌来佬肋例戾两冽呤坴侣仑旻奈侽呶妮念弩疟妾炔乳侍帑弢忝佻帖投罔昕炎佯易找折争知直制帙炙忠隹卓
     *       五行属“土”的字有：艾坳垇坻坫爬帕矾附矸岣岵岬坷岢坤垃峁岷坶坭坢坯坪坡坦坨宛往旺委忤岫盱亚奄肴夜依抑佾咏呦侑於盂臾昀狁
     *
     *       九画
     *       臿查姹差拆怊伡疢宬怵穿舡春殂促毒度耏性钆宫剐剞刭俓枯前怯侵秋纫肜柔砂衫舢厍姺哂矧甚牲省施食室是首姝耍帅闩思娀叟俗剃祆籼庠削信星咻庥胥叙宣页钇俞俣哉咱昝蚤则眨咤怔咫峙肘拄拙咨姿俎昨怍
     *       柲柄柴柢芏俄柃芇枹尜肝柑竿疳肛缸纥革虼哏狗枸牯故冠咣皈轨癸柜哄訇虹芨咭级急纪既枷叚架建牮姜姣皆界疥蚧矜劲扃九韭拘狙拒军看柯科咳客哐柳咯芒怩昵拈柈芃枰祈芑契恰芊俏俅酋畎芍柿柁柝芄玩侠狎柙相枵俆彦奕弈疫羿胤柚禺竽芋栅柘芝枳柱斫柞
     *       五行属“水”的字有：拌疤拔拜保抱背祊甭泵毖扁窆拚便昪波泊勃哺怖沲法泠沔勉眇秒拍哌沭畈飞沸狒玢风封凫怫拂俘氟罘拊讣负泔沽哈孩河很红泓侯后厚狐怙徊奂宦皇虺哕计沮炬姥泪泖昴冒玫眉美昧虻咪弭泌咩玟抿泯抹哞某拇泥眅泮叛盼狍泡疱怦抨披毗姘品屏泼匍柒泣泅泉染娆泗沱咸香巷泄卸泫泶妍沿衍泱盈泳油沾沼治注
     *       五行属“火”的字有：炳抶抽怛待怠殆眈抵帝酊订段祋盹盾哆哚拎赴拐曷烀咴姞柬炯玦俊拉厘俚俐怜亮咧律哪娜柰耐南怒虐炮炱泰炭畋殄亭突凃拖拓歪纨肟炫紃殃徉咬映昱怨灾炸招昭者贞政祉盅重纣胄炷籽秭耔奏
     *       五行属“土”的字有：哀垵拗砭垞衩昶垤垌峒肚砘垛垩垡趴怕型垓垢垲砍奎盆砒垧哇娃威韦畏胃瓮屋侮砉峋押砑咽匽怏姚要咿怡咦姨舣姻音垠俑勇幽疣羑囿宥纡舁禹垣爰约窀垚玥
     *
     *       十画
     *       剥财睬仓敇豺刬伥倡鬯晁眧耖唓宸乘蚩持翅刍俶纯祠脆厝凋钉珐刚罡怪借峻钌倪钋剖倩挈邛讱轫衽狨辱弱珊闪讪剡扇哨射珅娠神眚师十时拾狩书纾殊衰拴素祟孙隼唆索恸剜紊唏息席掀宵笑修修訏徐玹痃眩殉栽宰奘唣哳痄窄钊针真畛疹拯症纸指酎疰拽酌租珇祖祚唑座
     *       梆桉芭笆柏栢苄栟屙婀苊耙秫芳芬粉芙迀酐绀羔高哥格鬲哿根耕哽肱恭蚣躬拱贡玽股骨罟挂倌桄鬼桂衮核桁讧笏花桓恢唧姬屐笄笈脊记芰珈家痂恝兼豇狡讦拮桀芥衿肼弪径柩桕疽桔矩俱倨娟倦桊隽拷栲珂疴恪倥恐芤哭库框括栝栳栗匿臬芘栖芪耆岂起气虔肷芡衾芩芹祛拳缺桑芟栓凇笋桃桐砼桅芴奚哮校芯栩芽芫唁苡倚痈邕娱圄峪原纭芸笊祗芷桎株桌
     *       五行属“水”的字有：舨氨粑呗班般豹趵倍倴畚秕俾舭毕珌髟俵病玻砵亳庯秤臭泚洞娥洱眠眄俳派肪纺舫肥匪肺纷峰俸服祓蚨俯釜害氦蚶邗函航蚝耗盍狠恨哼恒洪候祜洹洄恚活洎浃津酒洌洛马邙旄们勐洣敉秘珉秣毪亩纽畔袢旁配倗疲蚍拼娉俜洴玶珀哱圃凄讫迄洽洳洒*纱娑洮洼洧纹蚊务洗效胁绁泄屑恤洫恂洵训洋洇耘拶洲洙浊
     *       五行属“火”的字有：哧耻翀娖玳耽疸紞岛倒娣玷爹瓞冻恫蚪趸哦恕耿烘恍晄疾晋珏倔烤朗烙哩娌俩凉料烈玲瓴凌留旅挛伦倮耄拿纳肭衲孬能娘恧衄秦恁朊芮蚋偌晒晌朔趿肽唐倘讨套特疼屉倜恬甜挑条庭挺徒彖庹挖挝倭乌娭夏畜烜讯迅秧烊窈舀旃展站珍朕肢值晊秩致舯衷冢罜祝倬笫恣
     *       五行属“土”的字有：啊唉埃砹鹌俺按案盎敖芺峬城埕砥峨恩砝砩个埂埚砬埒埋砰破埔砌峭窃容埏砷堍砣娓軎翁唔阢峡轩蚜氩恹胭宴晏氧恙眙酏益殷氤蚓佑迂邘育彧眢员袁砸砟砧肫准
     *
     *       十一画
     *       偲彩参曹侧钗产娼常徜唱巢晨趻偁琤瓻匙豉舂崇紬偢处绌啜船钏玼疵瓷粗啐挫得钓孰邢钒副寂祭剪旌勘馗率捏钕佩阡钎氢圊悫雀蚺唼啥钐商捎绍奢蛇设赦绅胂售庶唰爽悚讼宿狻捅偷钍问悉欷觋徙细舷祥斜偰邤欣衅羞袖酗旋悦责舴扎蚱砦粘胗栀胝趾终昼珠蛀专着紫族组胙做
     *       茇苞苯笨苾梐彬梣茬趁笞茌崔笪笛兜轭啉苓偶术梵芾苻桴符苷敢舸梗珙苟笱蛄蛊梏牿胍规匦国悍捍偈寄笳袈戛蛱胛徦假坚枧趼健皎教秸婕堇近婧竟救苴趄苣捐眷桷捃胩康苛氪啃寇苦梡眶悝盔悃捆婪笠茅茆茂梅苠茉苜旎苤笸启乾悄茄卿顷筇蚯区蛆朐娶悛圈痊苒若啬苫梢苕笙倏梳笥崧桫梭苔梯笤梃桶偓梧悟晤狭厢枭偕械许珝研厣眼悒挹翊茚英唷圉庾苑笮苎棁茁梓
     *       五行属“水”的字有：绊邦浜捌豝胈败胞狈被偝偪逼闭狴婢庳敝贬匾徧彪婊邠斌浡舶捕涔唇讹冕喵苗徘婞訩返贩访啡酚唪趺麸绂绋浮匐艴妇够海酣浛毫浩盒蚵痕珩唿唬扈瓠患凰悔彗晦婚货洚浸泾涓浚邟浪流麻麦脉曼袤浼眯觅密敏眸涅胖脬袍匏胚旆烹啤偏殍票贫婆粕浦渠涩涉涑挲涕涂涒晚望偎浯浠习涎消邪挟虚雪珢涌鱼雩浴浙浞
     *       五行属“火”的字有：欸捭蛃晡眵敕从凑绐带袋聃胆啖蛋盗羝顶啶动敚舵厄珥烽晗焓焊斛将狷诀觖翋徕狼勒梨狸犁猁珕唳粒梁聊羚翎聆蛉娄卤鹿略囵捋珞那婥讷您胬戚软晟胎酞贪袒啕剔悌粜烃停珽屠豚唾娲袜烷挽焐曦烯珣珧斩张章帐啁侦振执痔窒舳捉啄眦偬
     *       五行属“土”的字有：挨庵唵埯崩埠堾埭岽硐堆啪硎岗硌崮硅崞胡基崛崆堀崃硭埝鸟培堋埤崎畦牵埽眭堂窕眺婉唯帷伟尉迕捂牾硒崤勖琊崖哑痖讶迓垭娅崦焉闫偃痒野痍移异埸翌狺寅迎茔庸恿悠蚰蚴狳域欲峥埴蛭
     *
     *       十二画
     *       钣钯钸猜裁残伧厕嗏觇孱猖惝敞怅钞超朝抻牚胵啻惆喘窗创捶词猝酢悴毳皴嵯痤矬措貂掉钭堵钝贰疏舒黍述胸钫琈钙割钩辜壶戟绝钧竣剀钠甯钮掊裒钤钦禽情氰然韧绒伞散丧扫嫂痧跚善稍邵猞畲肾胜甥盛剩视授瘦税顺舜丝斯俟竦嗖酥诉粟飧睃钛替童推惜傒晰犀粞舾饩舄羡象琇须婿絮喧绚喻钥凿枣迮曾喳诈掌诏挣狰帧脂殖絷轵众蛛贮注兹诅尊阼
     *       棒笔草策茶枨茺楮篅荈棰茈茨答等第棣迭栋筏棉荇悻棼茯尴皋胳袼给茛轱觚酤诂雇棺贯胱晷贵棍聒猓椁皓闳喉荒茴荟嵇极戢棘殛集几掎悸迦袷跏间犍荐绛茭椒蛟绞窖喈街杰结筋荩惊阱景痉窘啾厩掬椐莒讵惧距犋掘珺喀开凯闶钪轲棵控筐贶傀喟蛞棱荔椋络荦荬茗猊棚椑期栖欺祁棋掐掮茜嵌强羟乔邱球诎蛐荃筌荏茸茹阮森筛耜覃棠珶茼统筒椭皖稀厦筅琄荀桠雅掩雁尧傜荑椅茵硬哟驭饫寓栈棹植茱椎兹棕最
     *       五行属“水”的字有：阪湴绑跋傍棓报悲邶备贲絣琫诐邲弼皕赑揙猋幖邴啵博跛钚瓿淳淙淬淡发淋幂黾描淼排牌淑涬雄番邡防婓扉淝悱斐粪冯稃跗涪袱幅复傅富淦蛤涫胲顸邯涵寒喊琀绗淏诃喝涸訸贺惚淮唤徨蛔惠混耠惑渐荆涞凉淩渌沦傌买茫蛑贸帽媒媚寐扪闷猛脒闵淖跑彭捧邳痞胼评迫普淇浅清渃脎深涮淞淌淘添淟涴雯涡无淅喜闲现项淆徇涯淹液欹淤淯渊云粥涿淄
     *       五行属“火”的字有：掰焙采场焯掣程塍嗒傣贷单氮悼登迪觌诋邸睇掂阽惦跌喋耋痘短惇敦掇迩焚钬焦接嗟晶就厥吭焜啦喇琅稂劳喱犂理傈痢詈晾量捩裂趔琌琉硫虏脔掠抡捺喃赧婻捻傩晴闰婼邰跆毯探掏啼腆掭祧迢贴婷痛饨跎酡惋惘喔窝幄欻寻循巽焱蛘轺轶媛哲蛰诊轸证臸彘智痣轴粢
     *       五行属“土”的字有：捱媕啽晻胺媪傲奡堡堛嵖砗堤奠堞恶费黑堠画黄堪喹岚塄嵋垴蛙崴为围帏惟喂硪婺痦翕硖翔硝砚堰崵崾揶掖猗壹诒迤贻胰诒喑堙喁釉嵛鼋粤越崽跖
     *
     *       十三画
     *       铋钵钹铂踩粲恻插诧琛嗔脭絺饬傺愁稠酬蜍楚揣歂蠢跐琮催瘁搓脞钿毹暑鼠蜀钴郝钾剿捷靖钜钶铃铆钼铌刨剻铍钷铅钳蜣惬嗪跫饪揉塞搔琗裟歃煞伤艄蛸诜蜃诗邿狮势试轼睡嗍嗣肆送搜肃嗉睢岁嗦羧唢铊钽酮剸媳郄酰嫌蚬跣详想绡新歆惺猩貅绣嗅煦揎暄铉驯询铀铕愉揄蝓伛钰裕愈钺载贼揸闸债斟钲睁黹酯诛邾瘃庄装琸资揍傶阻
     *       嗄荸箅筴搽猹槎茝椿榱戥荻椴莪蛾愕琳娩筢竖枫莩该陔赅戤概感干筻嗝塥跟绠诟彀痼诖琯诡跪嗬荷猴逅畸嫉楫麂荚嫁拣笕减楗毽酱郊跤脚敫揭诘睫解仅禁靳经茎睛胫敬迥揪舅琚雎榉绢筠揩慨楷戡莰稞窠嗑筘窟夸蒯诓揆暌琨髡廓莨楞莉莅廉楝莽莓楣募楠逆睨榀莆颀琦琪祺佥愆箝呛樯愀琴勤倾楸诠辁裙群嗓莎筲椹筮颂荽莛荼莞莴斡珷皙暇莶苋楔歇莘楦靴傿筵罨杨椰业义肄楹莜莸莠愚榆瘐预御楂桢孳罪
     *       五行属“水”的字有：颁斑斒靶稖雹陂琲迸哔愊愎痹辟惼飑禀摒脖渤补测滁渡渺琶湃饭沨蜂脯溉港傼颃嗥号合貉郈轷湖猢琥郇换涣豢惶湟挥晖汇会贿喙毁浑贾湔茳湫较粳鸠渴雷粱妈吗湄猸渼盟迷湣愍酩莫貊湳脲湓傰琵媲睥犏剽聘郱瓶裘惹绥汤琠湍微湋渭渥熙湘渫溆渲湮渶游渝郁渣湛浈滞渚煮
     *       五行属“火”的字有：稗煲煏煸裎嗤媸驰传搭靼迨亶当砀嗲电殿揲牒鼎督煅顿躲惰跺赁烦觥煳焕煌晃幌诙迹煎睐啷廊酪诔傫愣蜊炼零旒偻赂辂琭禄路乱煤睦乃揇猱恼农暖逄稔塔痰逃绨提跳蜓艇嗵退煺蜕脱驮陀顽脘畹煨炜蜗熄煊烟琰扬炀阳徭虞煜詹盏照罩蜇郅置雉追惴琢赀觜趑訾
     *       五行属“土”的字有：阿矮爱嗌揞暗嗷嶅廒奥碑碚碘碉碇碓痱话觟嵴碱块跬袅硼圣嵊嵩塑碎塌塘填琬碗嵬琟猥痿艉猬温嗡握呜蜈坞诩勋埙睚衙揠蜒爷揖饴诣意裔饮佣雍蛹犹猷瘀琙园圆援塬氲恽晕愠轾稚嵫
     *
     *       十四画
     *       铵艑彩嘈察瘥僝嫦玚綝称诚铖酲铳搊绸裯殠搐搋僢怆慈雌粹翠铞铥睹铒罚阀署铬铪划铰精聚劂铐铑闾铭陧齐綮慊戗腔抢劁寝蜻铨逡认铷瑞搡瘙铯僧绱韶赊慎酾逝誓寿绶陎说搠嗽诵嗾瞍速愫僳觫酸狲损铫铜僮骰途酴僖蜥铣屣郤禊衔线限像逍需睻铘铱劓铟银腴瑜窬臧造啧帻甄赈蜘摭碡帚铢综腙粽僔
     *       榜菝菢菶萆菜菖苌尝篪椽菙萃萏菪凳摁箅菽菲榧菔嘎盖赶纲睾膏搞槁诰郜歌搿个郠构菇菰箍鼓褂管逛绲帼蜾菡赫瘊槐萑夥箕暨跽嘉郏瘕笺菅搛戬僭降僬侥饺酵截竭诫骱紧廑菁腈兢迳僦裾菊榘皲菌郡犒裉箜骷酷筷魁睽匮愧匮莱榔菱榴杩樠萌墓幕萘槈裴菩桤萋嘁萁旗綦绮葺搴歉枪敲侨诮箧轻箐逑赇巯蜷绻榷荣榕箬瑟槊菘算榫榻萄萜菟菀伪萎菥箫榍榭菸厌酽疑瘗蜴萤郢萸语妪箢瑗愿菑榨寨肇榛筝菹
     *       五行属“水”的字有：搬蝂饱悖绷嘣鼻币滗裨弊碧馝稨滮裱宾菠驳搏僰箔逋沧滀涤滇嘧蜜绵瞄熊绯蜚腓翡偾疯逢凤孵郛福辅腑滏腐阁沟嘏寡滚嗨豪滈阂菏瑚华滑猾痪滉珲诲魂溷祸溘滥漓溧溜犸唛嘛幔髦瑁瞀么酶艋蜢灭闽鸣冥溟暝嫫麽陌寞溺滂搒脾罴蜱嘌嫖萍颇仆溥蜞溱溶溽飒饲溲溯溻溏滔网瘟闻郚舞郗溪携溴踅熏窨溢荥源滋滓
     *       五行属“火”的字有：熬畅尘逞瞅绰瘩捣嘀嫡递腚胨郖逗端对裰夺尔粼裹伙奖尽恺瘌辣罱郎嫪嫘酹嘞嫠奁连踉僚寥廖绫领熘绺喽陋绿纶裸雒瑙嫩宁喏搦炝熔煽裳台态摊叹搪耥趟慆慝滕逖惕裼舔蜩通透图团箨蜿绾腕诶鞅疡摇荧毓搌绽嫜彰胀幛赵肈这祯志种逐缀缁
     *       五行属“土”的字有：肮獒塝碥碴墋诞碲垫碟呕塾砜闺监碣境逵壸墚嵝墁碰堑岖墒墅硕碳维玮诬误寤瑕顼嘘墟碹腌嫣耶腋祎旖夤瑛墉踊诱与鸢冤猿殒翟崭嶂坠
     *
     *       十五画
     *       锕钡惭惨艖掺掺婵鋋廛谄肠厂麨瞋漦瘛冲摴厨锄諔嘬疮摐噇踳赐醋摧锉嘟锇熟陉锋敷锆刽刿铗缄剑节靓锔剧锞刳锒锂锍锊劈铺噙锓请趣髯糅锐腮毵磉傻陕殇赏审谂实蚀驶奭艏数腧摔谁咝缌嘶驷艘螋谇琐锑铤腿鋈嘻陷腺线哓销锌腥锈绪儇缊糌驵噪锃稹帜陟挚皱嘱翥幢谆诼踪诹陬
     *       葆萹箯标槽箣郴樗枞葱稻噔蒂腭面耦葩樊葑橄稿葛赓巩谷广妫瑰郭掴荭篌糇葫槲篁蝗叽缉赍稽瘠挤稷葭价驾稼俭翦贱腱箭僵桨娇胶噍颉羯槿儆獍阄驹屦踞蒈慷靠颏瞌蝌课缂抠侉宽款诳葵醌阃楼模篇葡槭葜悭椠庆穷茕萩蝤葚枢葰樘葶葳苇蒍妩葸瞎贤缃葙箱蝎萱样仪谊毅莹媵萭窳葬樟箴荮著箸醉
     *       五行属“水”的字有：瘢魬鲃魃罢褒鸨褓暴辈褙骳奔陛腷駜髲编蝙褊缏麃摽憋饼葧踣餔部漕浐蝽漘醇滴凛缅缈沤幡范鲂诽肤幞蝠驸赋腹蝜蝮澉缑缑盥虢憨汉撖颌褐滹浒沪哗踝逭漶辉麾慧浆漤涟漏漉履落玛码祃劢卖鞔满慢漫漭猫蝥貌霉魅庙缗瞑摸摩漠墨慕暮蒎盘醅赔喷嘭郫陴翩漂魄噗漆憩渗漱漽霆万逶嬉虾饷霄勰写漩演漾漪颍渔漳涨震渍
     *       五行属“火”的字有：皑僾熛噌层彻踟齿憏憃除褚踔逴辍腠褡逮儋弹德敌骶缔踮调蝶董陡缎饵噢缓践瑾进摎噘赉阆唠乐黎厉练谅辆嘹寮撂刘瘤搂鲁逯戮虑轮论脶骆熳鼐腩蝻脑闹馁辇侬驽挪僻热熵踏骀瘫谈郯赕羰瑭躺铽踢缇髫抟褪驼腽辋腰瑶熠熨暂摘獐账辄摺阵鸩征诤质觯肿驻缒禚辎
     *       五行属“土”的字有：腤鞍璈墺嶓嶒墀磁磋嶝墩堕嶙欧殴怄废坟磙嘿糊蝴峤磕蝰崂磊碾嬲磐嵚确豌纬诿卫慰庑娴鞋糈鸦养噎叶靥亿逸影慵忧邮鱿蝣牖谀缘院阅增磔徵
     *
     *       十六画
     *       锛餐憯穇舱糙蹅侪幨阊氅鋹谌踸赪褫憧瘳踹遄陲锤輴錞糍璁璀蹉鹾错锝雕锭鄃输醒辐钢锢锪辑锦静锯锩锟铼钔锰穆锫凭錡钱锖嫱揿瘸蹂儒缛褥睿噻嬗膳蛳撕稣锬颓羲螅阋锨髹谖谒逾觎谕憎甑瘵战缜铮整郮绉诸麈撞锥锱郰撙
     *       蓓荜筚蓖篦蔀篰苍橙篘莼笃饿谔鄂阏萼遏噩蒽树篚噶篙糕缟膈骼鸪毂掼龟辊过蒿嚆横黉骺鲎桦慌隍荤机畿墼蒺剂冀髻颊缣蒹谏踺强耩犟挢徼缙噤颈憬橘举踽窭鄄橛麇瞰眍裤哙窥愦篥梦蒲朴器褰黔橇桥憔樵撬鞘亲擒檎螓檠磬遒鼽糗趋鸲磲桡蓉蓐鄀穑蒴蒜荪蓑蓊樨县橡筱啸谐蓄阎魇谚窑缢荫萦蓥嬴颖阈遇圜樾蓁蒸筑篆嘴樽
     *       五行属“水”的字有：办澳鲅鲍虣惫糒甏嬖觱鮅鞞遍辨瘭傧拨饽播鲌膊馞潺潮澈澄霖霏奋愤讽抚鲋骸骇颔翰翮醐寰遑潢讳阍馄霍涧浇洁噱溃潦涝蚂骂瞒螨醚悯螟瘼默谋霓凝潘螃耪陪霈澎膨骈蹁谝瓢瞟撇瞥频鲆扑氆潜润撒霎潸潲渑澍澌潭烫潼隈沩涠阌宪廨兴学浔鄅沄
     *       五行属“火”的字有：撤陈撑鸱炽俦辏撮达殚掸惮导道灯谛谍蹀都赌憝吨炖遁踱廪燔积撅獗赖褴螂捞擂缡璃罹历琏撩獠燎陵遛龙瘘卢陆录焖遖挠鲇哝诺逎燃烧遂鲐昙糖螗绦陶蹄醍头暾鸵橐熹晓璇谑焰鸯晔烨燚燠璋瘴赭臻踵猪撰赘谘髭燊
     *       五行属“土”的字有：嗳嫒谙聱螯懊磅壁碜瓯惯衡垦垮磨碛墙融坛违谓怃歙遐鸭阉燕噫颐峄殪阴壅馀豫鸳螈运郓酝砖
     *
     *       十七画
     *       锿嚓擦縩操艚螬懆馇锸禅偿韔鼌帱憷歜黜膗聪独镀锻锷锅锾徽蹇饯键骏锴链镅镁縻鍪遣跄锹锲嚅孺鄏赛糁缫擅声谥蟀瞬锶耸锼谡虽隋缩锡膝蟋谿戏鲜痫猃馅谢鸺逊翼舆糟罾铡斋毡锗鍼锺诌瞩总邹
     *       蒡蔽檦檗蔡柽苁蔟簇档瞪懂蔸篼檩擀鸽篝购媾鸹馆簋蝈馘癀桧豁击玑激哜觊艰鞯捡检謇讲蒋鲛矫阶鲒鞠鞫据飓糠颗髁恳蔻挎狯亏栏檑莲联敛蓼蒌篓簏蔓懋甍蔑篾茑蓬蹊谦瞧擎罄麯蕖阒篸蔌簌檀蔚檄蓰辖罅芗魈蓿蔫檐营狱岳箦蔗栉赚桩
     *       五行属“水”的字有：癍帮谤跸臂褾豳擘澹点淀谧璠繁鼢缝缚醢鼾韩憾撼嚎鸿觳浣擐璜隳浍诨阔蒗澧濂潞嬷缦蟒蟊弥谜糜摹膜浓蟠貔缥螵嫔皤璞霜潚濉禧霞乡鲞亵懈獬鲟澡泽澶
     *       五行属“火”的字有：暧餲灿龀瞠骋黛担瘅挡蹈队鸸鲕临瞵磷懔烩绩琎爵阑痨缧儡励隶裢殓魉疗隆耧蝼缕鸾螺麋缪黏咛騃燧遢蹋饧膛螳醣誊嚏瞳疃臀襄燮谣遥繇燥择辗蟑褶鸷膣螽烛纵
     *       五行属“土”的字有：癌隘闇鮟醠遨謷磴礅岭鲑壕壑磺矶礁圹硗嵘闱鲔邬压阳嶷忆怿翳应婴膺拥优黝隅屿辕远龠郧
     *
     *       十八画
     *       翱鏊镑鎞饆璨蝉繟儭艟懤雏幮储蹙窜擤鄜镐镉环秽劐镓铠镏镎聂啮镍拧狞阕阙镕鞣铩缮蟮觞婶双飕锁钨雟燹蟓镒遭赜缯膪镇织颛鬃
     *       槟檫蒇槌箪簦簟蕫鹅额颚蔬蕃搁隔鲠遘觏鹄瞽归鲧簧蟥蕙获犄蕺虮鲫鲣睑裥简谫槛糨蕉谨觐旧瞿鹃蕨骒蒉篑聩拦瞢拟腻柠骐骑荨襁鄡荞窍翘苘躯璩觑鬈荛绕蕤蕊梼檮隗魏芜黠蕈颜蝇鹆蜮簪蕞
     *       五行属“水”的字有：鼥鞴鄙毙濞髀奰鞭飚蹩滨摈殡袯鹁馎鵏闯荡翻黻赙覆馥濠阖鄠鲩缋蟪济谩鄤鄚朦鄍谟馍貘泞蹒蟛癖鯆濮濡鲨湿穗涛潍隙獯滢杂濯
     *       五行属“火”的字有：痴虫戳丛戴焘鞮癜断怼膦懦曙丰烬醪耢鹂厘礼鲤粮缭噜侓辘璐谬蛲耨适抬鹈题阗餮魍曛曜瞻障遮谪职贽掷踬转骓擢
     *       五行属“土”的字有：碍瑷盫袄蹦璧础讴硷礓垒韪鄢医黟彝癔鄞鄘鼬陨韫
     *
     *       十九画
     *       鏖镚镖襜蟾铲鲳惩迟宠畴辞蹴祷镝颠牍犊锘摅璹镜铿鎏镂镘锚镆迁锵谯鲭鹊颡*臊膻鄯绳识兽馊擞镗醯系暹馐选镟赞錾躁谮鲰镞遵
     *       薜簸橱薋蹬椟薯臌关犷薅薨蕻谎讥蓟缰缴轿醮襟馑鲸鬏绔胯脍旷鲲扩蕾枥栎橹麓难鲵攀麒髂签蔷跷缲麴黢醛萨薇蕹萧肖撷薤蟹薪薛赝遗蚁薏蓣籀
     *       五行属“水”的字有：瓣鹎襞瀌瘪薄醭簿瀍鹑渎湎鹋蹯鲱羹鞲鲴缳绘嚯溅猎浏泺懑懵蠓祢靡鹏骗鄱谱蹼瀑扰渖雾泻霪
     *       五行属“火”的字有：薆摆爆蹭嘲蛏歠骴哒裆邓鲷鸫胴蹲遴辚齑际谲蹶羸类离丽呖帘臁蠊脸裣辽邻龄鲮馏咙撸庐氇蠃蟆鲶撵脓庞曝蹻烁谭韬鼗玺鄩绎赠鄣辙郑骘鲻
     *       五行属“土”的字有：爊礤坏疆坜垄垅垆稳鹉骛嬿臆繶臃韵
     *
     *       二十画
     *       镡犨触鰆镫镦鏻鐇镄铧鏸锏鐎镢鐍锎铹镣聍镤镨缱镪黥謦铣蝾襦繻蠕鳃馓鳋骟释孀铴璺霰馨续译谵钟驺鐏
     *       藊藏榇筹篡鹗鳄藐藁鳇攉蠖籍继舰藉警竞龃遽醵觉阚喾跨郐纩馈蓝篮栊栌檬篷脐蛴荠骞琼鳅劝薷薹牺献悬薰严邀议橼黦槠橥纂
     *       五行属“水”的字有：膀龅鲾避襣躄鳊辫穮缤濒蠙膑黼鳆瀚鹕怀还瀖濑沥泷泸鬕迈颟馒鹛蠛魔譬嚷瀜邂瀣潆瀛潴
     *       五行属“火”的字有：宝阐郸党鲽窦糯嚼矍懒黧醴疠骝胧拢炉掳罗飘赡獭挞腾龆鼍曦耀赢瓆躅
     *       五行属“土”的字有：骜巉矿岿砾壤鼯鹜邺瘾嘤罂
     *
     *       二十一画
     *       鐾骖鸧虿搀忏羼韂衬铛躇呲鹚骢铎镌镭镰鏴嗫谴蛩麝随隧邃铁燨险镱儹踯属镯鄹
     *       藨藕鹘顾鳏颢饥鸡歼鹣茧赆夔藜鞒驱饶薮藤嚣药艺龈莺樱蕴
     *       五行属“水”的字有：黯霸辩骠膘飙襮腼邈藩瀿瀵鹤轰护瀐澜潋露猕霹鼙鳍瀼攘潇醺瀷瀹灂
     *       五行属“火”的字有：缠踌跻爝腊蜡癞斓览烂累俪疬珑髅瓐骡曩鳎鲦鳐鹞灶啭馔龇
     *       五行属“土”的字有：鳌礴蠡砺砻碌礞巍撄誉跃
     *
     *       二十二画
     *       镔镲躔冁摛蹰撺镬鉴慑袭隰骁癣骣铸
     *       蔼蔺龚瓘蘅篯骄邝籁苈茏笼芦蘑孽苹蕲氍权苏俨瘿龉鬻
     *       五行属“水”的字有：边鳔鳖沣灌骅欢獾霁霾鳗艨鳘耱瓤穰滠响飨鳕藻
     *       五行属“火”的字有：颤龊籴叠读驎赎龛邋鳓粝鲢躐鹨聋癃窿胪舻孪囊摄傥饕听弯鹧
     *       五行属“土”的字有：巅鸥峦懿隐璎鳙饔
     *
     *       二十三画
     *       镳黪髑镥銎颥鳝铄鸶髓鼷鱚纤鹇攒脏齄鳟
     *       欑蘩鳜藿鹪鹫蠲兰蔹椤蓦蘖蘧癯藓鼹验驿鹬
     *       五行属“水”的字有：鷩变鲼鬟禳滩
     *       五行属“火”的字有：雠鳞麟蛎恋鹩轳栾猡体显
     *       五行属“土”的字有：娈岩缨
     *
     *       二十四画
     *       镈蚕谗谶矗鑫瓉瓒骤
     *       霭簖赣羁搅蓠篱酿衢龋鹰攥
     *       五行属“水”的字有：蚌髌鬓辔颦躞
     *       五行属“火”的字有：螭鞑癫蠹灵攫谰鳢雳陇鹭让闼厅龌鳣
     *       五行属“土”的字有：坝罐盐艳呓
     *
     *       二十五画
     *       鑶镵鲿蹿镧蹑璛镶赃
     *       观鲚髋榄萝箩蘸缵
     *       五行属“水”的字有：灞酆灏灏鹱漯蛮蘼襻
     *       五行属“火”的字有：叆纛揽鬣酃颅摞囔
     *
     *       二十六画
     *       馋镩骥镊跹趱　　
     *       蠼躜　　
     *       五行属“水”的字有：湾　　　　　　
     *       五行属“火”的字有：逦郦驴逻瘰攮
     *
     *       二十七画
     *       黩銮锣颞钻　　
     *       颧谳
     *       五行属“水”的字有：滦缬　　　　　　
     *       五行属“火”的字有：谠缆鸬鲈骧
     *
     *       二十八画
     *       齼　　
     *       笾戆棂
     *       五行属“水”的字有：滟戅　　
     *       五行属“火”的字有：魑轹跞
     *       五行属“土”的字有：鹦
     *
     *       二十九画
     *       镙　　
     *       五行属“火”的字有：躏骊　　
     *       五行属“土”的字有：鹳
     *
     *       三十画
     *       爨　　
     *       五行属“水”的字有：骉　　
     *       五行属“火”的字有：鲡馕
     */
}
