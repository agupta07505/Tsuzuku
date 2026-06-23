/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

/**
 * Single source of truth for all motivational quotes used across the app.
 * Each quote has an English translation, Japanese text, and optional Romaji pronunciation.
 */
data class Quote(
    val english: String,
    val japanese: String,
    val romaji: String = ""
)

object Quotes {
    val all: List<Quote> = listOf(
        Quote(
            english = "Consistency builds strength.",
            japanese = "続けることが、力になる。",
            romaji = "Tsudukeru koto ga, chikara ni naru."
        ),
        Quote(
            english = "Small steps every day, big change always.",
            japanese = "毎日の小さな一歩が、大きな変化を生む。",
            romaji = "Mainichi no chiisana ippo ga, tsune ni ookina henka o umu."
        ),
        Quote(
            english = "Fall seven times, stand up eight.",
            japanese = "七転び八起き。",
            romaji = "Nanakorobi yaoki."
        ),
        Quote(
            english = "With patience, the grass becomes milk.",
            japanese = "忍耐があれば、草もミルクになる。",
            romaji = "Nintai ga areba, kusa mo miruku ni naru."
        ),
        Quote(
            english = "The water is clean, the mind is clear, the day is new.",
            japanese = "水は清く、心は澄み、日は新しい。",
            romaji = "Mizu wa kiyoku, kokoro wa sumi, hi wa atarashii."
        ),
        Quote(
            english = "Beginning is easy, continuing is hard.",
            japanese = "始めるのは容易、続けるのは困難。",
            romaji = "Hajimeru no wa yōi, tsudukeru no wa konnan."
        ),
        Quote(
            english = "One day or day one — you decide.",
            japanese = "いつかか、今日からか。あなたが決める。",
            romaji = "Itsuka ka, kyō kara ka. Anata ga kimeru."
        ),
        Quote(
            english = "Even a thousand-mile journey begins with a single step.",
            japanese = "千里の道も一歩から。",
            romaji = "Senri no michi mo ippo kara."
        ),
        Quote(
            english = "Do not wait for the perfect moment. Take the moment and make it perfect.",
            japanese = "完璧な瞬間を待つな。今この瞬間を完璧にせよ。",
            romaji = "Kanpeki na shunkan o matsu na. Ima kono shunkan o kanpeki ni seyo."
        ),
        Quote(
            english = "Discipline is the bridge between goals and accomplishment.",
            japanese = "規律は、目標と達成をつなぐ橋である。",
            romaji = "Kiritsu wa, mokuhyō to tassei o tsunagu hashi de aru."
        ),
        Quote(
            english = "Progress, not perfection.",
            japanese = "完璧ではなく、前進を。",
            romaji = "Kanpeki de wa naku, zenshin o."
        ),
        Quote(
            english = "You don't have to be great to start, but you have to start to be great.",
            japanese = "始めるために偉大である必要はない。偉大になるために始めなければならない。",
            romaji = "Hajimeru tame ni idai de aru hitsuyō wa nai. Idai ni naru tame ni hajimenakereba naranai."
        ),
        Quote(
            english = "The secret of getting ahead is getting started.",
            japanese = "前進の秘訣は、まず始めることだ。",
            romaji = "Zenshin no hiketsu wa, mazu hajimeru koto da."
        ),
        Quote(
            english = "Your habits shape your identity.",
            japanese = "あなたの習慣が、あなた自身を形作る。",
            romaji = "Anata no shūkan ga, anata jishin o katachizukuru."
        ),
        Quote(
            english = "Motivation gets you started. Habit keeps you going.",
            japanese = "やる気が始まりをくれる。習慣が続けさせてくれる。",
            romaji = "Yaruki ga hajimari o kureru. Shūkan ga tsudukesasete kureru."
        ),
        Quote(
            english = "Each day is a new opportunity to grow.",
            japanese = "毎日が、成長する新しい機会だ。",
            romaji = "Mainichi ga, seichō suru atarashii kikai da."
        ),
        Quote(
            english = "Be the person you want to become.",
            japanese = "なりたい自分に、今日からなれ。",
            romaji = "Naritai jibun ni, kyō kara nare."
        ),
        Quote(
            english = "Slow progress is still progress.",
            japanese = "ゆっくりでも、前に進んでいる。",
            romaji = "Yukkuri demo, mae ni susunde iru."
        ),
        Quote(
            english = "The pain of discipline is less than the pain of regret.",
            japanese = "規律の痛みは、後悔の痛みより軽い。",
            romaji = "Kiritsu no itami wa, kōkai no itami yori karui."
        ),
        Quote(
            english = "Win the morning, win the day.",
            japanese = "朝を制する者は、一日を制する。",
            romaji = "Asa o seisuru mono wa, ichinichi o seisuru."
        ),
        Quote(
            english = "Success is the sum of small efforts repeated daily.",
            japanese = "成功とは、毎日繰り返す小さな努力の積み重ねだ。",
            romaji = "Seikō to wa, mainichi kurikaesu chiisana doryoku no tsumikasane da."
        ),
        Quote(
            english = "Do something today that your future self will thank you for.",
            japanese = "未来の自分が感謝するようなことを、今日やれ。",
            romaji = "Mirai no jibun ga kansha suru yō na koto o, kyō yare."
        ),
        Quote(
            english = "You are what you repeatedly do.",
            japanese = "あなたは、繰り返し行うことそのものだ。",
            romaji = "Anata wa, kurikaeshi okonau koto sono mono da."
        ),
        Quote(
            english = "Greatness is not in never falling, but in rising every time you fall.",
            japanese = "偉大さとは、決して倒れないことではなく、倒れるたびに立ち上がることだ。",
            romaji = "Idaisa to wa, kesshite taorenai koto de wa naku, taoreru tabi ni tachiagaru koto da."
        ),
        Quote(
            english = "The expert was once a beginner.",
            japanese = "専門家も、かつては初心者だった。",
            romaji = "Senmonka mo, katsute wa shoshinsha datta."
        ),
        Quote(
            english = "Believe you can and you're halfway there.",
            japanese = "できると信じれば、もう半分は達成している。",
            romaji = "Dekiru to shinjireba, mō hanbun wa tassei shite iru."
        ),
        Quote(
            english = "Take care of your body. It's the only place you have to live.",
            japanese = "体を大切にしろ。それだけが、あなたの住む場所だ。",
            romaji = "Karada o taisetsu ni shiro. Sore dake ga, anata no sumu basho da."
        ),
        Quote(
            english = "Every action you take is a vote for the person you want to become.",
            japanese = "あなたの行動のすべてが、なりたい自分への一票だ。",
            romaji = "Anata no kōdō no subete ga, naritai jibun e no ippyō da."
        ),
        Quote(
            english = "Don't count the days. Make the days count.",
            japanese = "日数を数えるな。一日一日を意味あるものにせよ。",
            romaji = "Nissū o kazoeru na. Ichinichi ichinichi o imi aru mono ni seyo."
        ),
        Quote(
            english = "What you do today can improve all of your tomorrows.",
            japanese = "今日やることが、明日以降のすべてを良くする。",
            romaji = "Kyō yaru koto ga, ashita ikō no subete o yoku suru."
        ),
        Quote(
            english = "Push yourself, because no one else is going to do it for you.",
            japanese = "自分を奮い立たせろ。他の誰もやってくれない。",
            romaji = "Jibun o furitatase ro. Hoka no dare mo yatte kurenai."
        ),
        Quote(
            english = "The only bad workout is the one that didn't happen.",
            japanese = "唯一の悪いトレーニングは、しなかったトレーニングだ。",
            romaji = "Yuiitsu no warui torēningu wa, shinakatta torēningu da."
        ),
        Quote(
            english = "Focus on progress, not perfection.",
            japanese = "完璧ではなく、進歩に集中せよ。",
            romaji = "Kanpeki de wa naku, shinpo ni shūchū seyo."
        ),
        Quote(
            english = "A year from now you will wish you had started today.",
            japanese = "一年後、今日始めておけば良かったと思うだろう。",
            romaji = "Ichinengo, kyō hajimete okeba yokatta to omou darō."
        ),
        Quote(
            english = "It always seems impossible until it's done.",
            japanese = "やり遂げるまで、いつも不可能に見える。",
            romaji = "Yaritogeru made, itsumo fukanō ni mieru."
        ),
        Quote(
            english = "The difference between who you are and who you want to be is what you do.",
            japanese = "今の自分となりたい自分の差は、行動が決める。",
            romaji = "Ima no jibun to naritai jibun no sa wa, kōdō ga kimeru."
        ),
        Quote(
            english = "One step at a time.",
            japanese = "一歩一歩、着実に。",
            romaji = "Ippo ippo, chakujitsu ni."
        ),
        Quote(
            english = "Your future is created by what you do today, not tomorrow.",
            japanese = "あなたの未来は、明日ではなく今日の行動が作る。",
            romaji = "Anata no mirai wa, ashita de wa naku kyō no kōdō ga tsukuru."
        ),
        Quote(
            english = "Hard work beats talent when talent doesn't work hard.",
            japanese = "才能が努力しないとき、努力が才能に勝る。",
            romaji = "Sainō ga doryoku shinai toki, doryoku ga sainō ni masaru."
        ),
        Quote(
            english = "Strive for progress, not perfection.",
            japanese = "完璧ではなく、前進を目指せ。",
            romaji = "Kanpeki de wa naku, zenshin o mezase."
        ),
        Quote(
            english = "You are stronger than you think.",
            japanese = "あなたは自分が思っているより強い。",
            romaji = "Anata wa jibun ga omotteiru yori tsuyoi."
        ),
        Quote(
            english = "Keep going. Everything you need will come to you at the perfect time.",
            japanese = "続けろ。必要なものはすべて、完璧なタイミングでやってくる。",
            romaji = "Tsudukero. Hitsuyō na mono wa subete, kanpeki na taimingu de yatte kuru."
        ),
        Quote(
            english = "The body achieves what the mind believes.",
            japanese = "心が信じることを、体は成し遂げる。",
            romaji = "Kokoro ga shinjiru koto o, karada wa nashitogeru."
        ),
        Quote(
            english = "Wake up with determination, go to bed with satisfaction.",
            japanese = "決意を持って目覚め、満足して眠れ。",
            romaji = "Ketsui o motte mezame, manzoku shite nemure."
        ),
        Quote(
            english = "The comeback is always stronger than the setback.",
            japanese = "復活は、挫折よりも常に強い。",
            romaji = "Fukkatsu wa, zasetsu yori mo tsune ni tsuyoi."
        ),
        Quote(
            english = "Dream big, start small, act now.",
            japanese = "大きく夢見て、小さく始め、今すぐ動け。",
            romaji = "Ōkiku yumemite, chiisaku hajime, ima sugu ugoke."
        ),
        Quote(
            english = "Discipline is choosing between what you want now and what you want most.",
            japanese = "規律とは、今欲しいものと最も欲しいものの間の選択だ。",
            romaji = "Kiritsu to wa, ima hoshii mono to mottomo hoshii mono no aida no sentaku da."
        ),
        Quote(
            english = "The only limit is the one you set yourself.",
            japanese = "唯一の限界は、あなた自身が設けるものだ。",
            romaji = "Yuiitsu no genkai wa, anata jishin ga moueru mono da."
        ),
        Quote(
            english = "Make today count.",
            japanese = "今日を意味あるものにせよ。",
            romaji = "Kyō o imi aru mono ni seyo."
        ),
        Quote(
            english = "Habits are the compound interest of self-improvement.",
            japanese = "習慣は、自己成長の複利だ。",
            romaji = "Shūkan wa, jiko seichō no fukuri da."
        ),
        Quote(
            english = "Be patient with yourself. Self-growth is tender.",
            japanese = "自分に辛抱強くあれ。自己成長は繊細なものだ。",
            romaji = "Jibun ni shinbōzuyoku are. Jiko seichō wa sensai na mono da."
        ),
        Quote(
            english = "Today is the first day of the rest of your life.",
            japanese = "今日は、残りの人生の最初の日だ。",
            romaji = "Kyō wa, nokori no jinsei no saisho no hi da."
        ),
        Quote(
            english = "Small wins lead to big victories.",
            japanese = "小さな勝利が、大きな勝利につながる。",
            romaji = "Chiisana shōri ga, ōkina shōri ni tsunagaru."
        ),
        Quote(
            english = "You don't need to see the whole staircase, just take the first step.",
            japanese = "階段全体を見る必要はない。ただ最初の一歩を踏み出せ。",
            romaji = "Kaidan zentai o miru hitsuyō wa nai. Tada saisho no ippo o fumidase."
        ),
        Quote(
            english = "Work hard in silence. Let success make the noise.",
            japanese = "静かに努力せよ。成功が語ってくれる。",
            romaji = "Shizuka ni doryoku seyo. Seikō ga katatte kureru."
        ),
        Quote(
            english = "Don't wish for it. Work for it.",
            japanese = "願うな。働け。",
            romaji = "Negau na. Hatarake."
        ),
        Quote(
            english = "Embrace the process.",
            japanese = "プロセスを受け入れよ。",
            romaji = "Purosesu o ukeirere yo."
        ),
        Quote(
            english = "Every morning is a chance to start over.",
            japanese = "毎朝は、やり直すチャンスだ。",
            romaji = "Maiasa wa, yarinaosu chansu da."
        ),
        Quote(
            english = "Courage is not the absence of fear but the decision that something else is more important.",
            japanese = "勇気とは恐れのないことではなく、それより大切なものがあると決断することだ。",
            romaji = "Yūki to wa osore no nai koto de wa naku, sore yori taisetsu na mono ga aru to ketsudan suru koto da."
        ),
        Quote(
            english = "Rest if you must, but don't you quit.",
            japanese = "休む必要があれば休め。しかし、やめるな。",
            romaji = "Yasumu hitsuyō ga areba yasume. Shikashi, yameru na."
        ),
        Quote(
            english = "The grind is part of the glory.",
            japanese = "苦労は、栄光の一部だ。",
            romaji = "Kurō wa, eikō no ichibu da."
        ),
        Quote(
            english = "Your habits are your future.",
            japanese = "あなたの習慣が、あなたの未来だ。",
            romaji = "Anata no shūkan ga, anata no mirai da."
        ),
        Quote(
            english = "The more you sweat in training, the less you bleed in battle.",
            japanese = "練習で汗をかくほど、戦場での血は少なくなる。",
            romaji = "Renshū de ase o kaku hodo, senjō de no chi wa sukunaku naru."
        ),
        Quote(
            english = "A strong morning routine sets the tone for everything.",
            japanese = "朝のルーティンが、すべての土台を作る。",
            romaji = "Asa no rūtin ga, subete no dodai o tsukuru."
        ),
        Quote(
            english = "Don't stop when you're tired. Stop when you're done.",
            japanese = "疲れたからといって止まるな。終わったら止まれ。",
            romaji = "Tsukareta kara to itte tomaru na. Owattara tomare."
        ),
        Quote(
            english = "Clarity comes from action, not thought.",
            japanese = "明確さは、思考ではなく行動から生まれる。",
            romaji = "Meikakusa wa, shikō de wa naku kōdō kara umareru."
        ),
        Quote(
            english = "Make it a habit, not a chore.",
            japanese = "義務ではなく、習慣にせよ。",
            romaji = "Gimu de wa naku, shūkan ni seyo."
        ),
        Quote(
            english = "Invest in yourself. It pays the best interest.",
            japanese = "自分に投資せよ。それが最高の利息をもたらす。",
            romaji = "Jibun ni tōshi seyo. Sore ga saikō no risoku o motarasu."
        ),
        Quote(
            english = "A little progress each day adds up to big results.",
            japanese = "毎日の少しの進歩が、大きな結果につながる。",
            romaji = "Mainichi no sukoshi no shinpo ga, ōkina kekka ni tsunagaru."
        ),
        Quote(
            english = "The hardest step is the one out the door.",
            japanese = "最も難しい一歩は、ドアの外へ出ることだ。",
            romaji = "Mottomo muzukashii ippo wa, doa no soto e deru koto da."
        ),
        Quote(
            english = "Show up even when you don't feel like it.",
            japanese = "気が乗らなくても、現れろ。",
            romaji = "Ki ga noranakute mo, arawareru o."
        ),
        Quote(
            english = "Growth happens outside your comfort zone.",
            japanese = "成長は、コンフォートゾーンの外で起きる。",
            romaji = "Seichō wa, konfo-to zōn no soto de okiru."
        ),
        Quote(
            english = "Excellence is not a destination but a continuous journey.",
            japanese = "卓越とは、目的地ではなく続く旅だ。",
            romaji = "Takuetsu to wa, mokutekichi de wa naku tsuduku tabi da."
        ),
        Quote(
            english = "Build the life you want, one habit at a time.",
            japanese = "一つの習慣ずつ、望む人生を築け。",
            romaji = "Hitotsu no shūkan zutsu, nozomu jinsei o kizuke."
        ),
        Quote(
            english = "Consistency is more important than intensity.",
            japanese = "継続は、激しさよりも大切だ。",
            romaji = "Keizoku wa, hageshisa yori mo taisetsu da."
        ),
        Quote(
            english = "Eat well, move daily, sleep deeply.",
            japanese = "しっかり食べ、毎日動き、深く眠れ。",
            romaji = "Shikkari tabe, mainichi ugoki, fukaku nemure."
        ),
        Quote(
            english = "You will never always be motivated. You must learn to be disciplined.",
            japanese = "常にやる気があるわけではない。規律を身につけなければならない。",
            romaji = "Tsune ni yaruki ga aru wake de wa nai. Kiritsu o mi ni tsukenakereba naranai."
        ),
        Quote(
            english = "Take it one breath at a time.",
            japanese = "一呼吸ずつ、進め。",
            romaji = "Hito kokyū zutsu, susume."
        ),
        Quote(
            english = "Your only competition is who you were yesterday.",
            japanese = "あなたの唯一のライバルは、昨日の自分だ。",
            romaji = "Anata no yuiitsu no raibaru wa, kinō no jibun da."
        ),
        Quote(
            english = "Persist. The world bends to those who persist.",
            japanese = "諦めるな。世界は、諦めない者に屈する。",
            romaji = "Akirameru na. Sekai wa, akiramenai mono ni kussu suru."
        ),
        Quote(
            english = "Tiny habits, massive results.",
            japanese = "小さな習慣が、大きな結果を生む。",
            romaji = "Chiisana shūkan ga, ōkina kekka o umu."
        ),
        Quote(
            english = "The mind is everything. What you think, you become.",
            japanese = "心がすべてだ。思ったとおりの人間になる。",
            romaji = "Kokoro ga subete da. Omotta tōri no ningen ni naru."
        ),
        Quote(
            english = "There is no elevator to success. You have to take the stairs.",
            japanese = "成功へのエレベーターはない。階段を使うしかない。",
            romaji = "Seikō e no erebētā wa nai. Kaidan o tsukau shika nai."
        ),
        Quote(
            english = "Run your own race.",
            japanese = "自分のレースを走れ。",
            romaji = "Jibun no rēsu o hashire."
        ),
        Quote(
            english = "Do it scared.",
            japanese = "怖くてもやれ。",
            romaji = "Kowakute mo yare."
        ),
        Quote(
            english = "The reward for hard work is more opportunity.",
            japanese = "努力の報酬は、さらなるチャンスだ。",
            romaji = "Doryoku no hōshū wa, saranaru chansu da."
        ),
        Quote(
            english = "Never miss twice.",
            japanese = "二度サボるな。",
            romaji = "Nido saboru na."
        ),
        Quote(
            english = "Start before you're ready.",
            japanese = "準備が整う前に始めろ。",
            romaji = "Junbi ga totonou mae ni hajimero."
        ),
        Quote(
            english = "Showing up is half the battle.",
            japanese = "現れることが、戦いの半分だ。",
            romaji = "Arawareru koto ga, tatakai no hanbun da."
        ),
        Quote(
            english = "Breathe. You've got this.",
            japanese = "深呼吸しろ。あなたならできる。",
            romaji = "Shinkokyū shiro. Anata nara dekiru."
        ),
        Quote(
            english = "Little by little, a little becomes a lot.",
            japanese = "少しずつ、少しが多くになる。",
            romaji = "Sukoshi zutsu, sukoshi ga ōku ni naru."
        ),
        Quote(
            english = "Don't let perfect be the enemy of good.",
            japanese = "完璧を、良いことの敵にするな。",
            romaji = "Kanpeki o, yoi koto no teki ni suru na."
        ),
        Quote(
            english = "You are capable of more than you know.",
            japanese = "あなたは、自分が知っている以上のことができる。",
            romaji = "Anata wa, jibun ga shitte iru ijō no koto ga dekiru."
        ),
        Quote(
            english = "Nourish your mind as you nourish your body.",
            japanese = "体を養うように、心も養え。",
            romaji = "Karada o yashinau yō ni, kokoro mo yashinaeru."
        ),
        Quote(
            english = "Every champion was once a contender who refused to give up.",
            japanese = "すべてのチャンピオンは、かつて諦めなかった挑戦者だった。",
            romaji = "Subete no chanpion wa, katsute akiramenakatta chōsensha datta."
        ),
        Quote(
            english = "Do the hard things first.",
            japanese = "難しいことを先にやれ。",
            romaji = "Muzukashii koto o saki ni yare."
        ),
        Quote(
            english = "The best time to start was yesterday. The next best time is now.",
            japanese = "最高のスタートは昨日だった。次に良いのは今だ。",
            romaji = "Saikō no sutāto wa kinō datta. Tsugi ni yoi no wa ima da."
        ),
        Quote(
            english = "Live fully, strive honestly, share freely.",
            japanese = "充実して生き、誠実に努め、惜しみなく与えよ。",
            romaji = "Jūjitsu shite iki, seijitsu ni tsutome, oshiminaku ataere yo."
        ),
        Quote(
            english = "Strengthen your roots so you can weather any storm.",
            japanese = "根を強くせよ。どんな嵐にも耐えられるように。",
            romaji = "Ne o tsuyoku seyo. Donna arashi ni mo taerareru yō ni."
        ),
        Quote(
            english = "Peace begins with a single breath.",
            japanese = "平和は、一度の深呼吸から始まる。",
            romaji = "Heiwa wa, ichido no shinkokyū kara hajimaru."
        ),
        Quote(
            english = "Kindness to yourself is the first kindness.",
            japanese = "自分への優しさが、最初の優しさだ。",
            romaji = "Jibun e no yasashisa ga, saisho no yasashisa da."
        ),
        Quote(
            english = "A calm mind brings inner strength and confidence.",
            japanese = "穏やかな心が、内なる強さと自信をもたらす。",
            romaji = "Odayaka na kokoro ga, uchi naru tsuyosa to jishin o motarasu."
        ),
        Quote(
            english = "The flower that blooms in adversity is the rarest and most beautiful of all.",
            japanese = "逆境に咲く花は、最も希少で美しい。",
            romaji = "Gyakkyō ni saku hana wa, mottomo kishō de utsukushii."
        ),
        Quote(
            english = "Let go of what you cannot change.",
            japanese = "変えられないものは手放せ。",
            romaji = "Kaerarenai mono wa tebanase."
        ),
        Quote(
            english = "Today's discomfort is tomorrow's strength.",
            japanese = "今日の不快が、明日の力になる。",
            romaji = "Kyō no fukai ga, ashita no chikara ni naru."
        ),
        Quote(
            english = "Plant seeds of effort. Harvest a life of purpose.",
            japanese = "努力の種を蒔け。目的ある人生を収穫せよ。",
            romaji = "Doryoku no tane o make. Mokuteki aru jinsei o shūkaku seyo."
        ),
        Quote(
            english = "You are built for this.",
            japanese = "あなたはこのために作られた。",
            romaji = "Anata wa kono tame ni tsukurareta."
        )
    )

    /** Get a random quote. */
    fun random(): Quote = all.random()

    /** Get a quote by cycling through the list based on an index (e.g. hour of day). */
    fun byIndex(index: Int): Quote = all[index % all.size]
}