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
        // ── Original 100 ──────────────────────────────────────────────────────────
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
        ),

        // ── New Quotes 101–600+ ────────────────────────────────────────────────
        Quote(
            english = "Action is the antidote to despair.",
            japanese = "行動こそが、絶望の解毒剤だ。",
            romaji = "Kōdō koso ga, zetsubō no gedokuzai da."
        ),
        Quote(
            english = "Energy flows where attention goes.",
            japanese = "注意が向かうところに、エネルギーが流れる。",
            romaji = "Chūi ga mukau tokoro ni, enerugī ga nagareru."
        ),
        Quote(
            english = "Your potential is endless.",
            japanese = "あなたの可能性は無限だ。",
            romaji = "Anata no kanōsei wa mugen da."
        ),
        Quote(
            english = "Silence speaks louder than excuses.",
            japanese = "沈黙は、言い訳よりも雄弁だ。",
            romaji = "Chinmoku wa, iiwake yori mo yūben da."
        ),
        Quote(
            english = "The strongest people are not those who show strength in front of us, but those who win battles we know nothing about.",
            japanese = "最も強い人は、強さを見せる人ではなく、私たちが知らない戦いに勝っている人だ。",
            romaji = "Mottomo tsuyoi hito wa, tsuyosa o miseru hito de wa naku, watashitachi ga shiranai tatakai ni katte iru hito da."
        ),
        Quote(
            english = "First, master the fundamentals.",
            japanese = "まず、基礎を極めよ。",
            romaji = "Mazu, kiso o kiwame yo."
        ),
        Quote(
            english = "Think big, act bold.",
            japanese = "大きく考え、大胆に動け。",
            romaji = "Ōkiku kangae, daitan ni ugoke."
        ),
        Quote(
            english = "Every setback is a setup for a comeback.",
            japanese = "すべての挫折は、復活への準備だ。",
            romaji = "Subete no zasetsu wa, fukkatsu e no junbi da."
        ),
        Quote(
            english = "Challenge yourself; it is the only path that leads to growth.",
            japanese = "自分に挑め。それだけが、成長への道だ。",
            romaji = "Jibun ni idome. Sore dake ga, seichō e no michi da."
        ),
        Quote(
            english = "Stop wishing. Start doing.",
            japanese = "願うのをやめ、行動を始めよ。",
            romaji = "Negau no o yame, kōdō o hajime yo."
        ),
        Quote(
            english = "Be so good they can't ignore you.",
            japanese = "無視できないほど優れた存在になれ。",
            romaji = "Mushi dekinai hodo sugureta sonzai ni nare."
        ),
        Quote(
            english = "The people who are crazy enough to think they can change the world are the ones who do.",
            japanese = "世界を変えられると信じるほど無謀な人こそが、実際に変える人だ。",
            romaji = "Sekai o kaerareru to shinjiru hodo mubō na hito koso ga, jissai ni kaeru hito da."
        ),
        Quote(
            english = "Today I will do what others won't so tomorrow I can do what others can't.",
            japanese = "今日、他の人がしないことをすれば、明日、他の人にできないことができる。",
            romaji = "Kyō, hoka no hito ga shinai koto o sureba, ashita, hoka no hito ni dekinai koto ga dekiru."
        ),
        Quote(
            english = "Your time is limited; don't waste it living someone else's life.",
            japanese = "あなたの時間は限られている。他人の人生を生きることに無駄にするな。",
            romaji = "Anata no jikan wa kagirarete iru. Tanin no jinsei o ikiru koto ni muda ni suru na."
        ),
        Quote(
            english = "Do what you can with what you have, where you are.",
            japanese = "今いる場所で、あるものを使って、できることをやれ。",
            romaji = "Ima iru basho de, aru mono o tsukatte, dekiru koto o yare."
        ),
        Quote(
            english = "Strength does not come from physical capacity; it comes from indomitable will.",
            japanese = "強さは肉体的な能力からではなく、不屈の意志から来る。",
            romaji = "Tsuyosa wa nikutaiteki na nōryoku kara de wa naku, fukutsu no ishi kara kuru."
        ),
        Quote(
            english = "Stay hungry. Stay foolish.",
            japanese = "貪欲であれ。愚かであれ。",
            romaji = "Donyoku de are. Oroka de are."
        ),
        Quote(
            english = "The secret to living is giving.",
            japanese = "生きることの秘訣は、与えることだ。",
            romaji = "Ikiru koto no hiketsu wa, ataeru koto da."
        ),
        Quote(
            english = "Become addicted to constant and never-ending self-improvement.",
            japanese = "絶え間ない自己改善の虜になれ。",
            romaji = "Taema nai jiko kaizen no toriko ni nare."
        ),
        Quote(
            english = "Live as if you were to die tomorrow. Learn as if you were to live forever.",
            japanese = "明日死ぬかのように生きよ。永遠に生きるかのように学べ。",
            romaji = "Ashita shinu ka no yō ni iki yo. Eien ni ikiru ka no yō ni manabe."
        ),
        Quote(
            english = "Character is the result of two things: mental attitude and the way we spend our time.",
            japanese = "性格は二つのことの結果だ。精神的な姿勢と、時間の使い方だ。",
            romaji = "Seikaku wa futatsu no koto no kekka da. Seishinteki na shisei to, jikan no tsukaikata da."
        ),
        Quote(
            english = "Be yourself; everyone else is already taken.",
            japanese = "自分自身であれ。他の人は全員すでに取られている。",
            romaji = "Jibun jishin de are. Hoka no hito wa zen'in sude ni torarete iru."
        ),
        Quote(
            english = "Nothing is impossible; the word itself says 'I'm possible'.",
            japanese = "不可能なことはない。その言葉自体が「可能だ」と言っている。",
            romaji = "Fukanō na koto wa nai. Sono kotoba jitai ga 'kanō da' to itte iru."
        ),
        Quote(
            english = "I am not a product of my circumstances; I am a product of my decisions.",
            japanese = "私は状況の産物ではない。私は決断の産物だ。",
            romaji = "Watashi wa jōkyō no sanbutsu de wa nai. Watashi wa ketsudan no sanbutsu da."
        ),
        Quote(
            english = "The way to get started is to quit talking and begin doing.",
            japanese = "始める方法は、話すのをやめて行動を始めることだ。",
            romaji = "Hajimeru hōhō wa, hanasu no o yamete kōdō o hajimeru koto da."
        ),
        Quote(
            english = "Wealth is not about having a lot of money; it's about having a lot of options.",
            japanese = "豊かさとは多くのお金を持つことではなく、多くの選択肢を持つことだ。",
            romaji = "Yutakasa to wa ōku no okane o motsu koto de wa naku, ōku no sentakushi o motsu koto da."
        ),
        Quote(
            english = "Obstacles are those frightful things you see when you take your eyes off the goal.",
            japanese = "障害とは、目標から目を離したときに見える恐ろしいものだ。",
            romaji = "Shōgai to wa, mokuhyō kara me o hanashita toki ni mieru osoroshii mono da."
        ),
        Quote(
            english = "The future belongs to those who believe in the beauty of their dreams.",
            japanese = "未来は、夢の美しさを信じる人のものだ。",
            romaji = "Mirai wa, yume no utsukushisa o shinjiru hito no mono da."
        ),
        Quote(
            english = "If you want something you've never had, you must be willing to do something you've never done.",
            japanese = "これまで持ったことのないものが欲しければ、これまでしたことのないことをする覚悟が必要だ。",
            romaji = "Kore made motta koto no nai mono ga hoshikereba, kore made shita koto no nai koto o suru kakugo ga hitsuyō da."
        ),
        Quote(
            english = "Inaction breeds doubt and fear. Action breeds confidence and courage.",
            japanese = "行動しないことは、疑いと恐れを生む。行動することは、自信と勇気を生む。",
            romaji = "Kōdō shinai koto wa, utagai to osore o umu. Kōdō suru koto wa, jishin to yūki o umu."
        ),
        Quote(
            english = "Failure is the condiment that gives success its flavor.",
            japanese = "失敗は、成功に風味を与える調味料だ。",
            romaji = "Shippai wa, seikō ni fūmi o ataeru chōmiryō da."
        ),
        Quote(
            english = "Imperfect action is better than perfect inaction.",
            japanese = "不完全な行動は、完璧な無行動よりも優れている。",
            romaji = "Fukanzen na kōdō wa, kanpeki na mu kōdō yori mo sugurete iru."
        ),
        Quote(
            english = "Don't be afraid to give up the good to go for the great.",
            japanese = "良いものを手放して、偉大なものを目指すことを恐れるな。",
            romaji = "Yoi mono o tebanashite, idai na mono o mezasu koto o osoreru na."
        ),
        Quote(
            english = "If it doesn't challenge you, it won't change you.",
            japanese = "あなたに挑戦しないものは、あなたを変えない。",
            romaji = "Anata ni chōsen shinai mono wa, anata o kaenai."
        ),
        Quote(
            english = "Only those who dare to fail greatly can ever achieve greatly.",
            japanese = "大きく失敗する勇気のある者だけが、大きく達成できる。",
            romaji = "Ōkiku shippai suru yūki no aru mono dake ga, ōkiku tassei dekiru."
        ),
        Quote(
            english = "The most difficult thing is the decision to act; the rest is merely tenacity.",
            japanese = "最も難しいのは行動を決断することだ。あとは単なる粘り強さだ。",
            romaji = "Mottomo muzukashii no wa kōdō o ketsudan suru koto da. Ato wa tan naru nebari zuyosa da."
        ),
        Quote(
            english = "Nothing will work unless you do.",
            japanese = "あなたが動かなければ、何も動かない。",
            romaji = "Anata ga ugokanakere ba, nani mo ugokanai."
        ),
        Quote(
            english = "The secret of success is to do the common thing uncommonly well.",
            japanese = "成功の秘訣は、ありふれたことを並外れてうまくやることだ。",
            romaji = "Seikō no hiketsu wa, arifureta koto o namihazurete umaku yaru koto da."
        ),
        Quote(
            english = "The more I learn, the more I realize how much I don't know.",
            japanese = "学べば学ぶほど、自分がいかに無知であるかを実感する。",
            romaji = "Manabe ba manabuhodo, jibun ga ika ni muchi de aru ka o jikkan suru."
        ),
        Quote(
            english = "Work smarter, not just harder.",
            japanese = "ただ懸命にではなく、賢く働け。",
            romaji = "Tada kenmei ni de wa naku, kashikoku hatarake."
        ),
        Quote(
            english = "The road to success is dotted with many tempting parking spaces.",
            japanese = "成功への道は、魅力的な駐車スペースで点在している。",
            romaji = "Seikō e no michi wa, miryokuteki na chūsha supēsu de tenzai shite iru."
        ),
        Quote(
            english = "Quality is not an act; it is a habit.",
            japanese = "品質は行為ではなく、習慣だ。",
            romaji = "Hinshitsu wa kōi de wa naku, shūkan da."
        ),
        Quote(
            english = "In order to succeed, we must first believe that we can.",
            japanese = "成功するためには、まず自分にできると信じなければならない。",
            romaji = "Seikō suru tame ni wa, mazu jibun ni dekiru to shinji nakereba naranai."
        ),
        Quote(
            english = "Don't watch the clock; do what it does. Keep going.",
            japanese = "時計を見るな。時計がするように行動せよ。前進し続けろ。",
            romaji = "Tokei o miru na. Tokei ga suru yō ni kōdō se yo. Zenshin shi tsuzukero."
        ),
        Quote(
            english = "Either you run the day or the day runs you.",
            japanese = "あなたが一日を支配するか、一日があなたを支配するかだ。",
            romaji = "Anata ga ichinichi o shihai suru ka, ichinichi ga anata o shihai suru ka da."
        ),
        Quote(
            english = "Believe in yourself and all that you are.",
            japanese = "自分自身とあなたのすべてを信じろ。",
            romaji = "Jibun jishin to anata no subete o shinjiro."
        ),
        Quote(
            english = "Change your thoughts and you change your world.",
            japanese = "思考を変えれば、世界が変わる。",
            romaji = "Shikō o kaereba, sekai ga kawaru."
        ),
        Quote(
            english = "Whatever the mind can conceive and believe, it can achieve.",
            japanese = "心が思い描き、信じられることは、何でも達成できる。",
            romaji = "Kokoro ga omoiegaki, shinjirarereru koto wa, nandemo tassei dekiru."
        ),
        Quote(
            english = "Everything you've ever wanted is on the other side of fear.",
            japanese = "あなたがこれまでずっと望んでいたものは、すべて恐れの向こう側にある。",
            romaji = "Anata ga kore made zutto nozonde ita mono wa, subete osore no mukō gawa ni aru."
        ),
        Quote(
            english = "It is during our darkest moments that we must focus to see the light.",
            japanese = "最も暗い瞬間こそ、光を見つめなければならない。",
            romaji = "Mottomo kurai shunkan koso, hikari o mitsume nakereba naranai."
        ),
        Quote(
            english = "Keep your eyes on the stars and your feet on the ground.",
            japanese = "星に目を向け、地に足をつけろ。",
            romaji = "Hoshi ni me o muke, chi ni ashi o tsukero."
        ),
        Quote(
            english = "Don't limit your challenges. Challenge your limits.",
            japanese = "挑戦を制限するな。限界に挑め。",
            romaji = "Chōsen o seigen suru na. Genkai ni idome."
        ),
        Quote(
            english = "It's not whether you get knocked down; it's whether you get up.",
            japanese = "倒されるかどうかではなく、立ち上がるかどうかだ。",
            romaji = "Taosareru ka dō ka de wa naku, tachiagaru ka dō ka da."
        ),
        Quote(
            english = "Excellence is doing ordinary things extraordinarily well.",
            japanese = "卓越とは、普通のことを並外れてうまくやることだ。",
            romaji = "Takuetsu to wa, futsū no koto o namihazurete umaku yaru koto da."
        ),
        Quote(
            english = "Every day is a second chance.",
            japanese = "毎日が、再挑戦のチャンスだ。",
            romaji = "Mainichi ga, saichōsen no chansu da."
        ),
        Quote(
            english = "In the middle of every difficulty lies opportunity.",
            japanese = "すべての困難の中に、チャンスがある。",
            romaji = "Subete no konnan no naka ni, chansu ga aru."
        ),
        Quote(
            english = "The only way to do great work is to love what you do.",
            japanese = "偉大な仕事をする唯一の方法は、自分の仕事を愛することだ。",
            romaji = "Idai na shigoto o suru yuiitsu no hōhō wa, jibun no shigoto o aisuru koto da."
        ),
        Quote(
            english = "Success usually comes to those who are too busy to be looking for it.",
            japanese = "成功はたいてい、それを探す暇もないほど忙しい人のもとに来る。",
            romaji = "Seikō wa taitei, sore o sagasu hima mo nai hodo isogashii hito no moto ni kuru."
        ),
        Quote(
            english = "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.",
            japanese = "心の恐れに押しまわされるな。心の夢に導かれろ。",
            romaji = "Kokoro no osore ni oshi mawarasa reru na. Kokoro no yume ni michibi karero."
        ),
        Quote(
            english = "Life is 10% what happens to you and 90% how you react to it.",
            japanese = "人生は10%があなたに起こることで、90%があなたの反応だ。",
            romaji = "Jinsei wa 10% ga anata ni okoru koto de, 90% ga anata no han'nō da."
        ),
        Quote(
            english = "What lies behind you and what lies in front of you pales in comparison to what lies inside you.",
            japanese = "あなたの後ろにあるものと前にあるものは、あなたの内側にあるものに比べれば取るに足らない。",
            romaji = "Anata no ushiro ni aru mono to mae ni aru mono wa, anata no uchigawa ni aru mono ni kurabere ba toru ni taranai."
        ),
        Quote(
            english = "Choose to be optimistic. It feels better.",
            japanese = "楽観的であることを選べ。その方が気分がいい。",
            romaji = "Rakkanteki de aru koto o erabe. Sono hō ga kibun ga ii."
        ),
        Quote(
            english = "There are no shortcuts to any place worth going.",
            japanese = "行く価値のある場所への近道はない。",
            romaji = "Iku kachi no aru basho e no chikamichi wa nai."
        ),
        Quote(
            english = "The greatest glory in living lies not in never falling, but in rising every time we fall.",
            japanese = "生きることの最大の栄光は、決して倒れないことではなく、倒れるたびに立ち上がることにある。",
            romaji = "Ikiru koto no saidai no eikō wa, kesshite taorenai koto de wa naku, taoreru tabi ni tachiagaru koto ni aru."
        ),
        Quote(
            english = "You miss 100% of the shots you don't take.",
            japanese = "打たないシュートは、100%外れる。",
            romaji = "Utanai shūto wa, 100% hazureru."
        ),
        Quote(
            english = "Always do your best. What you plant now you will harvest later.",
            japanese = "常に最善を尽くせ。今種まきしたものを、後で収穫する。",
            romaji = "Tsune ni saizen o tsukuse. Ima tanemaki shita mono o, nochi ni shūkaku suru."
        ),
        Quote(
            english = "Optimism is the faith that leads to achievement.",
            japanese = "楽観主義は、達成へと導く信念だ。",
            romaji = "Rakkanshugi wa, tassei e to michibiku shinnen da."
        ),
        Quote(
            english = "You can't go back and change the beginning, but you can start where you are and change the ending.",
            japanese = "始まりに戻って変えることはできないが、今いる場所から始めて、結末を変えることはできる。",
            romaji = "Hajimari ni modotte kaeru koto wa dekinai ga, ima iru basho kara hajimete, ketsumatsu o kaeru koto wa dekiru."
        ),
        Quote(
            english = "It does not matter how slowly you go as long as you do not stop.",
            japanese = "止まらない限り、どんなにゆっくり進んでも構わない。",
            romaji = "Tomaranai kagiri, donna ni yukkuri susunde mo kamawanai."
        ),
        Quote(
            english = "Perfection is not attainable, but if we chase perfection we can catch excellence.",
            japanese = "完璧は達成できないが、完璧を追えば卓越を手に入れられる。",
            romaji = "Kanpeki wa tassei dekinai ga, kanpeki o oeba takuetsu o te ni irerareru."
        ),
        Quote(
            english = "An unexamined life is not worth living.",
            japanese = "振り返らない人生は、生きる価値がない。",
            romaji = "Furikaera nai jinsei wa, ikiru kachi ga nai."
        ),
        Quote(
            english = "Life is not measured by the number of breaths we take, but by the moments that take our breath away.",
            japanese = "人生は息の数ではなく、息をのむような瞬間によって測られる。",
            romaji = "Jinsei wa iki no kazu de wa naku, iki o nomu yō na shunkan ni yotte hakarareru."
        ),
        Quote(
            english = "Spread love everywhere you go.",
            japanese = "行く先々で、愛を広めよ。",
            romaji = "Iku sakisaki de, ai o hiromeyo."
        ),
        Quote(
            english = "If life were predictable, it would cease to be life and be without flavor.",
            japanese = "人生が予測可能なら、それは人生でなくなり、味わいもなくなる。",
            romaji = "Jinsei ga yosoku kanō nara, sore wa jinsei de nakunari, ajiwai mo nakunaru."
        ),
        Quote(
            english = "If you look at what you have in life, you'll always have more.",
            japanese = "今持っているものに目を向ければ、いつもそれ以上のものがある。",
            romaji = "Ima motte iru mono ni me o mukereba, itsumo sore ijō no mono ga aru."
        ),
        Quote(
            english = "In the end, it's not the years in your life that count; it's the life in your years.",
            japanese = "最終的に、人生の年数ではなく、年数の中の人生が大切だ。",
            romaji = "Saishūteki ni, jinsei no nensū de wa naku, nensū no naka no jinsei ga taisetsu da."
        ),
        Quote(
            english = "Never let the fear of striking out keep you from playing the game.",
            japanese = "三振を恐れて、試合に出ることをやめてはいけない。",
            romaji = "Sanshin o osorete, shiai ni deru koto o yamete wa ikenai."
        ),
        Quote(
            english = "Many of life's failures are people who did not realize how close they were to success when they gave up.",
            japanese = "人生の失敗の多くは、諦めたとき成功にどれほど近かったか気づかなかった人たちだ。",
            romaji = "Jinsei no shippai no ōku wa, akirameta toki seikō ni dorehodo chikakatta ka kizuka nakatta hitotachi da."
        ),
        Quote(
            english = "You have brains in your head. You have feet in your shoes. You can steer yourself any direction you choose.",
            japanese = "頭には知恵がある。足には靴がある。好きな方向へ自分で進める。",
            romaji = "Atama ni wa chie ga aru. Ashi ni wa kutsu ga aru. Suki na hōkō e jibun de susumeru."
        ),
        Quote(
            english = "If you want to live a happy life, tie it to a goal, not to people or things.",
            japanese = "幸せな人生を送りたければ、それを目標に結びつけよ。人や物ではなく。",
            romaji = "Shiawase na jinsei o okuritakereba, sore o mokuhyō ni musubi tsuke yo. Hito ya mono de wa naku."
        ),
        Quote(
            english = "Never let the fear of failure get in your way.",
            japanese = "失敗への恐れが、あなたの邪魔をさせるな。",
            romaji = "Shippai e no osore ga, anata no jama o saseru na."
        ),
        Quote(
            english = "You have to fight through some bad days to earn the best days of your life.",
            japanese = "人生最高の日々を手に入れるために、いくつかの辛い日々を乗り越えなければならない。",
            romaji = "Jinsei saikō no hibi o te ni ireru tame ni, ikutsu ka no tsurai hibi o norikoe nakereba naranai."
        ),
        Quote(
            english = "Be not afraid of life. Believe that life is worth living.",
            japanese = "人生を恐れるな。人生は生きる価値があると信じろ。",
            romaji = "Jinsei o osoreru na. Jinsei wa ikiru kachi ga aru to shinjiro."
        ),
        Quote(
            english = "When everything seems to be going against you, remember that the airplane takes off against the wind, not with it.",
            japanese = "すべてがうまくいかないように思えるとき、飛行機は風に乗ってではなく、風に向かって離陸することを思い出せ。",
            romaji = "Subete ga umaku ikanai yō ni omoe ru toki, hikōki wa kaze ni notte de wa naku, kaze ni mukatte rikuriku suru koto o omoidase."
        ),
        Quote(
            english = "If you can dream it, you can do it.",
            japanese = "夢見ることができれば、実現できる。",
            romaji = "Yumemiru koto ga dekireba, jitsugen dekiru."
        ),
        Quote(
            english = "Life is either a daring adventure or nothing at all.",
            japanese = "人生は大胆な冒険か、さもなければ何もない。",
            romaji = "Jinsei wa daitan na bōken ka, samonakere ba nani mo nai."
        ),
        Quote(
            english = "All our dreams can come true if we have the courage to pursue them.",
            japanese = "それらを追い求める勇気があれば、すべての夢は実現できる。",
            romaji = "Sorera o oimotomeru yūki ga areba, subete no yume wa jitsugen dekiru."
        ),
        Quote(
            english = "Go confidently in the direction of your dreams.",
            japanese = "自信を持って、夢の方向へ進め。",
            romaji = "Jishin o motte, yume no hōkō e susume."
        ),
        Quote(
            english = "The best revenge is massive success.",
            japanese = "最高の復讐は、圧倒的な成功だ。",
            romaji = "Saikō no fukushū wa, attōteki na seikō da."
        ),
        Quote(
            english = "The harder you work for something, the greater you'll feel when you achieve it.",
            japanese = "何かのために懸命に働くほど、それを達成したときの喜びは大きい。",
            romaji = "Nanika no tame ni kenmei ni hataraku hodo, sore o tassei shita toki no yorokobi wa ōkii."
        ),
        Quote(
            english = "Dream bigger. Do bigger.",
            japanese = "もっと大きく夢見よ。もっと大きくやれ。",
            romaji = "Motto ōkiku yumemiyō. Motto ōkiku yare."
        ),
        Quote(
            english = "Don't tell people your plans. Show them your results.",
            japanese = "計画を人に話すな。結果を見せろ。",
            romaji = "Keikaku o hito ni hanasu na. Kekka o misero."
        ),
        Quote(
            english = "No pressure, no diamonds.",
            japanese = "プレッシャーなくして、ダイヤモンドなし。",
            romaji = "Puresshā naku shite, daiyamondo nashi."
        ),
        Quote(
            english = "If you get tired, learn to rest, not to quit.",
            japanese = "疲れたなら、休むことを学べ。諦めることではなく。",
            romaji = "Tsukareta nara, yasumu koto o manabe. Akirameru koto de wa naku."
        ),
        Quote(
            english = "We generate fears while we sit. We overcome them by action.",
            japanese = "座っている間に恐れを生む。行動によってそれを克服する。",
            romaji = "Suwatte iru aida ni osore o umu. Kōdō ni yotte sore o kokufuku suru."
        ),
        Quote(
            english = "Knowing is not enough; we must apply. Willing is not enough; we must do.",
            japanese = "知るだけでは不十分。実践しなければならない。意志があるだけでは不十分。行動しなければならない。",
            romaji = "Shiru dake de wa fujūbun. Jissen shi nakereba naranai. Ishi ga aru dake de wa fujūbun. Kōdō shi nakereba naranai."
        ),
        Quote(
            english = "The key to success is to focus on goals, not obstacles.",
            japanese = "成功の鍵は、障害ではなく目標に集中することだ。",
            romaji = "Seikō no kagi wa, shōgai de wa naku mokuhyō ni shūchū suru koto da."
        ),
        Quote(
            english = "If you are not willing to risk the usual, you will have to settle for the ordinary.",
            japanese = "普通のリスクを取る気がないなら、平凡に甘んじなければならない。",
            romaji = "Futsū no risuku o toru ki ga nai nara, heibon ni amanji nakereba naranai."
        ),
        Quote(
            english = "Trust yourself. You know more than you think you do.",
            japanese = "自分を信頼せよ。あなたは自分が思っているより多くのことを知っている。",
            romaji = "Jibun o shinrai se yo. Anata wa jibun ga omotteiru yori ōku no koto o shitte iru."
        ),
        Quote(
            english = "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.",
            japanese = "最大の弱さは、諦めることにある。成功への最も確実な方法は、常にもう一度試みることだ。",
            romaji = "Saidai no yowasa wa, akirameru koto ni aru. Seikō e no mottomo kakujitsu na hōhō wa, tsune ni mō ichido kokoromiru koto da."
        ),
        Quote(
            english = "Don't be afraid to stand for what you believe in, even if it means standing alone.",
            japanese = "自分の信念のために立つことを恐れるな。それが一人でも。",
            romaji = "Jibun no shinnen no tame ni tatsu koto o osoreru na. Sore ga hitori de mo."
        ),
        Quote(
            english = "A winner is a dreamer who never gives up.",
            japanese = "勝者とは、決して諦めない夢想家だ。",
            romaji = "Shōsha to wa, kesshite akiramenai musouka da."
        ),
        Quote(
            english = "Positive thinking will let you do everything better than negative thinking will.",
            japanese = "ポジティブ思考は、ネガティブ思考よりあらゆることをうまくさせてくれる。",
            romaji = "Pojithibu shikō wa, negatibu shikō yori arayuru koto o umaku sasete kureru."
        ),
        Quote(
            english = "The question isn't who is going to let me; it's who is going to stop me.",
            japanese = "問題は誰が私を許可するかではない。誰が私を止めるかだ。",
            romaji = "Mondai wa dare ga watashi o kyoka suru ka de wa nai. Dare ga watashi o tomeru ka da."
        ),
        Quote(
            english = "Work hard, stay humble.",
            japanese = "懸命に働き、謙虚であれ。",
            romaji = "Kenmei ni hataraki, kenkyo de are."
        ),
        Quote(
            english = "Doubt kills more dreams than failure ever will.",
            japanese = "疑いは、失敗よりも多くの夢を殺す。",
            romaji = "Utagai wa, shippai yori mo ōku no yume o korosu."
        ),
        Quote(
            english = "Set your goals high, and don't stop till you get there.",
            japanese = "高い目標を設定し、達成するまで止まるな。",
            romaji = "Takai mokuhyō o settei shi, tassei suru made tomaru na."
        ),
        Quote(
            english = "If you can't outplay them, outwork them.",
            japanese = "上手くプレーできなければ、より一層努力せよ。",
            romaji = "Umaku purē dekinakereba, yori issō doryoku se yo."
        ),
        Quote(
            english = "Energy and persistence conquer all things.",
            japanese = "エネルギーと粘り強さが、すべてを征服する。",
            romaji = "Enerugī to nebarizuyosa ga, subete o seifuku suru."
        ),
        Quote(
            english = "The difference between a successful person and others is not a lack of strength, not a lack of knowledge, but rather a lack of will.",
            japanese = "成功者と他者の違いは、力の不足でも知識の不足でもなく、意志の欠如だ。",
            romaji = "Seikōsha to taisha no chigai wa, chikara no fusoku demo chishiki no fusoku demo naku, ishi no ketsujyo da."
        ),
        Quote(
            english = "Happiness is not something you postpone for the future; it is something you design for the present.",
            japanese = "幸福は将来のために後回しにするものではなく、現在のために設計するものだ。",
            romaji = "Kōfuku wa shōrai no tame ni atomawashi ni suru mono de wa naku, genzai no tame ni sekkei suru mono da."
        ),
        Quote(
            english = "Don't downgrade your dream just to fit your reality. Upgrade your conviction to match your destiny.",
            japanese = "現実に合わせて夢を格下げするな。運命に合わせて信念を格上げせよ。",
            romaji = "Genjitsu ni awasete yume o kakusage suru na. Unmei ni awasete shinnen o kakuage se yo."
        ),
        Quote(
            english = "Develop an attitude of gratitude.",
            japanese = "感謝の姿勢を育てよ。",
            romaji = "Kansha no shisei o sodateyo."
        ),
        Quote(
            english = "We are what we think. All that we are arises with our thoughts.",
            japanese = "私たちは思考そのものだ。私たちのすべては、思考から生まれる。",
            romaji = "Watashitachi wa shikō sono mono da. Watashitachi no subete wa, shikō kara umareru."
        ),
        Quote(
            english = "What you get by achieving your goals is not as important as what you become by achieving your goals.",
            japanese = "目標を達成することで得るものより、目標を達成することであなたが何者になるかの方が大切だ。",
            romaji = "Mokuhyō o tassei suru koto de eru mono yori, mokuhyō o tassei suru koto de anata ga nani mono ni naru ka no hō ga taisetsu da."
        ),
        Quote(
            english = "To achieve greatness, start where you are, use what you have, do what you can.",
            japanese = "偉大さを達成するために、今いる場所から始め、持っているものを使い、できることをやれ。",
            romaji = "Idaisa o tassei suru tame ni, ima iru basho kara hajime, motte iru mono o tsukai, dekiru koto o yare."
        ),
        Quote(
            english = "Defeat is not bitter unless you swallow it.",
            japanese = "飲み込まない限り、敗北は苦くない。",
            romaji = "Nomikoma nai kagiri, haiboku wa nigaku nai."
        ),
        Quote(
            english = "You are never too old to set another goal or to dream a new dream.",
            japanese = "新たな目標を設定したり、新しい夢を見たりするには、年を取り過ぎることはない。",
            romaji = "Arata na mokuhyō o settei shitari, atarashii yume o mitari suru ni wa, toshi o torisugiru koto wa nai."
        ),
        Quote(
            english = "The will to win is nothing without the will to prepare.",
            japanese = "準備する意志がなければ、勝つ意志は何の意味もない。",
            romaji = "Junbi suru ishi ga nakereba, katsu ishi wa nan no imi mo nai."
        ),
        Quote(
            english = "Believe and act as if it were impossible to fail.",
            japanese = "失敗が不可能であるかのように信じ、行動せよ。",
            romaji = "Shippai ga fukanō de aru ka no yō ni shinjite, kōdō se yo."
        ),
        Quote(
            english = "With self-discipline, almost anything is possible.",
            japanese = "自己規律があれば、ほとんど何でも可能だ。",
            romaji = "Jiko kiritsu ga areba, hotondo nandemo kanō da."
        ),
        Quote(
            english = "To handle yourself, use your head; to handle others, use your heart.",
            japanese = "自分を制御するには頭を使え。他者を制御するには心を使え。",
            romaji = "Jibun o seigyo suru ni wa atama o tsukae. Taisha o seigyo suru ni wa kokoro o tsukae."
        ),
        Quote(
            english = "The only person you are destined to become is the person you decide to be.",
            japanese = "あなたがなるべき運命にある人は、あなたが決断する人だけだ。",
            romaji = "Anata ga naru beki unmei ni aru hito wa, anata ga ketsudan suru hito dake da."
        ),
        Quote(
            english = "Tough times never last, but tough people do.",
            japanese = "辛い時代は続かない。しかし、強い人は続く。",
            romaji = "Tsurai jidai wa tsuzukanai. Shikashi, tsuyoi hito wa tsuzuku."
        ),
        Quote(
            english = "Good things come to those who hustle.",
            japanese = "良いことは、懸命に動く人のもとへ来る。",
            romaji = "Yoi koto wa, kenmei ni ugoku hito no moto e kuru."
        ),
        Quote(
            english = "You become what you believe.",
            japanese = "信じたものになる。",
            romaji = "Shinjita mono ni naru."
        ),
        Quote(
            english = "The first step is you have to say that you can.",
            japanese = "最初の一歩は、できると言うことだ。",
            romaji = "Saisho no ippo wa, dekiru to iu koto da."
        ),
        Quote(
            english = "Even if you're on the right track, you'll get run over if you just sit there.",
            japanese = "正しい道にいても、ただ座っているだけでは轢かれる。",
            romaji = "Tadashii michi ni ite mo, tada suwatte iru dake de wa hikareru."
        ),
        Quote(
            english = "The successful warrior is the average man, with laser-like focus.",
            japanese = "成功した戦士とは、レーザーのような集中力を持つ普通の人だ。",
            romaji = "Seikō shita senshi to wa, rēzā no yō na shūchūryoku o motsu futsū no hito da."
        ),
        Quote(
            english = "Winning is not everything, but the will to win is everything.",
            japanese = "勝つことがすべてではないが、勝つ意志こそがすべてだ。",
            romaji = "Katsu koto ga subete de wa nai ga, katsu ishi koso ga subete da."
        ),
        Quote(
            english = "Never stop doing your best just because someone doesn't give you credit.",
            japanese = "誰かが評価してくれないからといって、最善を尽くすことをやめるな。",
            romaji = "Dare ka ga hyōka shite kurenai kara to itte, saizen o tsukusu koto o yameru na."
        ),
        Quote(
            english = "Your life does not get better by chance; it gets better by change.",
            japanese = "人生は偶然には良くならない。変化によって良くなる。",
            romaji = "Jinsei wa gūzen ni wa yoku naranai. Henka ni yotte yoku naru."
        ),
        Quote(
            english = "The secret of joy in work is contained in one word — excellence.",
            japanese = "仕事の喜びの秘密は一言に集約される。卓越だ。",
            romaji = "Shigoto no yorokobi no himitsu wa ichigon ni shūyaku sareru. Takuetsu da."
        ),
        Quote(
            english = "You are enough.",
            japanese = "あなたは十分だ。",
            romaji = "Anata wa jūbun da."
        ),
        Quote(
            english = "Pain is temporary. Quitting lasts forever.",
            japanese = "痛みは一時的だ。諦めは永遠だ。",
            romaji = "Itami wa ichijiteki da. Akirame wa eien da."
        ),
        Quote(
            english = "Great things never come from comfort zones.",
            japanese = "偉大なものは、コンフォートゾーンからは生まれない。",
            romaji = "Idai na mono wa, konfo-to zōn kara wa umarenai."
        ),
        Quote(
            english = "Make each day your masterpiece.",
            japanese = "毎日を傑作にせよ。",
            romaji = "Mainichi o kessaku ni seyo."
        ),
        Quote(
            english = "Passion is energy. Feel the power that comes from focusing on what excites you.",
            japanese = "情熱はエネルギーだ。自分を興奮させるものに集中することから来る力を感じよ。",
            romaji = "Jōnetsu wa enerugī da. Jibun o kōfun saseru mono ni shūchū suru koto kara kuru chikara o kanji yo."
        ),
        Quote(
            english = "The only journey is the journey within.",
            japanese = "唯一の旅は、内なる旅だ。",
            romaji = "Yuiitsu no tabi wa, uchi naru tabi da."
        ),
        Quote(
            english = "Begin anywhere.",
            japanese = "どこからでも始めよ。",
            romaji = "Doko kara demo hajimeyo."
        ),
        Quote(
            english = "Commit to the process.",
            japanese = "プロセスにコミットせよ。",
            romaji = "Purosesu ni komitto se yo."
        ),
        Quote(
            english = "Excellence is a habit.",
            japanese = "卓越は習慣だ。",
            romaji = "Takuetsu wa shūkan da."
        ),
        Quote(
            english = "The best preparation for tomorrow is doing your best today.",
            japanese = "明日への最高の準備は、今日最善を尽くすことだ。",
            romaji = "Ashita e no saikō no junbi wa, kyō saizen o tsukusu koto da."
        ),
        Quote(
            english = "Never give up on a dream just because of the time it will take to accomplish it.",
            japanese = "達成するのにかかる時間だけを理由に、夢を諦めるな。",
            romaji = "Tassei suru no ni kakaru jikan dake o riyū ni, yume o akirameru na."
        ),
        Quote(
            english = "Choose to shine.",
            japanese = "輝くことを選べ。",
            romaji = "Kagayaku koto o erabe."
        ),
        Quote(
            english = "Work until your idols become your rivals.",
            japanese = "あこがれの人があなたのライバルになるまで働け。",
            romaji = "Akogare no hito ga anata no raibaru ni naru made hatarake."
        ),
        Quote(
            english = "Hustle in silence and let your success be your noise.",
            japanese = "黙々と努力し、成功があなたの声になるようにせよ。",
            romaji = "Mokumoku to doryoku shi, seikō ga anata no koe ni naru yō ni se yo."
        ),
        Quote(
            english = "How you do anything is how you do everything.",
            japanese = "何かをやる方法が、すべてをやる方法だ。",
            romaji = "Nanika o yaru hōhō ga, subete o yaru hōhō da."
        ),
        Quote(
            english = "Be the energy you want to attract.",
            japanese = "引き寄せたいエネルギーそのものになれ。",
            romaji = "Hikiyosetai enerugī sono mono ni nare."
        ),
        Quote(
            english = "What you do today matters.",
            japanese = "今日やることは重要だ。",
            romaji = "Kyō yaru koto wa jūyō da."
        ),
        Quote(
            english = "Failure is not the opposite of success; it is part of success.",
            japanese = "失敗は成功の反対ではない。成功の一部だ。",
            romaji = "Shippai wa seikō no hantai de wa nai. Seikō no ichibu da."
        ),
        Quote(
            english = "Be stubborn about your goals and flexible about your methods.",
            japanese = "目標には頑固に、方法には柔軟に。",
            romaji = "Mokuhyō ni wa ganko ni, hōhō ni wa jūnan ni."
        ),
        Quote(
            english = "You don't find willpower; you create it.",
            japanese = "意志の力は見つけるものではなく、作るものだ。",
            romaji = "Ishi no chikara wa mitsukeru mono de wa naku, tsukuru mono da."
        ),
        Quote(
            english = "Champions train. Losers complain.",
            japanese = "チャンピオンは鍛える。敗者は不満を言う。",
            romaji = "Chanpion wa kitaeru. Haisha wa fuman o iu."
        ),
        Quote(
            english = "Adapt what is useful, reject what is useless.",
            japanese = "役立つものを取り入れ、役に立たないものを捨てよ。",
            romaji = "Yakudatsu mono o toriire, yaku ni tatanai mono o sute yo."
        ),
        Quote(
            english = "Commit more than you think you can deliver.",
            japanese = "できると思う以上のことにコミットせよ。",
            romaji = "Dekiru to omou ijō no koto ni komitto se yo."
        ),
        Quote(
            english = "Don't fear failure. Fear being in the exact same place next year as you are today.",
            japanese = "失敗を恐れるな。来年も今と全く同じ場所にいることを恐れよ。",
            romaji = "Shippai o osoreru na. Rainen mo ima to mattaku onaji basho ni iru koto o osoreyō."
        ),
        Quote(
            english = "Discipline is the soul of an army.",
            japanese = "規律は、軍の魂だ。",
            romaji = "Kiritsu wa, gun no tamashii da."
        ),
        Quote(
            english = "You were born to make an impact.",
            japanese = "あなたは影響を与えるために生まれた。",
            romaji = "Anata wa eikyō o ataeru tame ni umareta."
        ),
        Quote(
            english = "If not now, when?",
            japanese = "今でなければ、いつ？",
            romaji = "Ima de nakereba, itsu?"
        ),
        Quote(
            english = "Impossible is just an opinion.",
            japanese = "不可能とはただの意見だ。",
            romaji = "Fukanō to wa tada no iken da."
        ),
        Quote(
            english = "You cannot afford to wait for perfect conditions. Goal conditions are always imperfect.",
            japanese = "完璧な条件を待つ余裕はない。目標のための条件は常に不完全だ。",
            romaji = "Kanpeki na jōken o matsu yoyū wa nai. Mokuhyō no tame no jōken wa tsune ni fukanzen da."
        ),
        Quote(
            english = "Seek progress, not approval.",
            japanese = "承認ではなく、前進を求めよ。",
            romaji = "Shōnin de wa naku, zenshin o motomeyo."
        ),
        Quote(
            english = "Never let your memories be greater than your dreams.",
            japanese = "記憶が夢よりも偉大であることを決して許すな。",
            romaji = "Kioku ga yume yori mo idai de aru koto o kesshite yurusu na."
        ),
        Quote(
            english = "Your speed doesn't matter; forward is forward.",
            japanese = "スピードは関係ない。前進は前進だ。",
            romaji = "Supīdo wa kankei nai. Zenshin wa zenshin da."
        ),
        Quote(
            english = "Attitude is a little thing that makes a big difference.",
            japanese = "姿勢は、大きな違いを生む小さなものだ。",
            romaji = "Shisei wa, ōkina chigai o umu chiisana mono da."
        ),
        Quote(
            english = "Start each day with a grateful heart.",
            japanese = "感謝の心で毎日を始めよ。",
            romaji = "Kansha no kokoro de mainichi o hajimeyo."
        ),
        Quote(
            english = "Keep your face always toward the sunshine, and shadows will fall behind you.",
            japanese = "常に太陽の方に顔を向けよ。そうすれば影はあなたの後ろに落ちる。",
            romaji = "Tsune ni taiyō no hō ni kao o mukeyo. Sō sureba kage wa anata no ushiro ni ochiru."
        ),
        Quote(
            english = "The art of living lies less in eliminating our troubles than growing with them.",
            japanese = "生きる技術は、困難を排除することよりも、困難とともに成長することにある。",
            romaji = "Ikiru gijutsu wa, konnan o haijo suru koto yori mo, konnan to tomo ni seichō suru koto ni aru."
        ),
        Quote(
            english = "Life is a journey, not a destination.",
            japanese = "人生は旅だ。目的地ではない。",
            romaji = "Jinsei wa tabi da. Mokutekichi de wa nai."
        ),
        Quote(
            english = "Simplicity is the ultimate sophistication.",
            japanese = "シンプルさは、究極の洗練だ。",
            romaji = "Shimpurusa wa, kyūkyoku no senren da."
        ),
        Quote(
            english = "Clarity, focus, and follow-through are the three pillars of execution.",
            japanese = "明確さ、集中力、そして実行力。この三つが実践の柱だ。",
            romaji = "Meikakusa, shūchūryoku, soshite jikkōryoku. Kono mittsu ga jissen no hashira da."
        ),
        Quote(
            english = "The man who moves a mountain begins by carrying away small stones.",
            japanese = "山を動かす者は、小石を運び出すことから始める。",
            romaji = "Yama o ugokasu mono wa, koishi o hakobidasu koto kara hajimeru."
        ),
        Quote(
            english = "Speak less, do more.",
            japanese = "語ることを減らし、行動することを増やせ。",
            romaji = "Kataru koto o herashi, kōdō suru koto o fuyase."
        ),
        Quote(
            english = "Knowledge is of no value unless you put it into practice.",
            japanese = "実践に移さない限り、知識に価値はない。",
            romaji = "Jissen ni utsusu nai kagiri, chishiki ni kachi wa nai."
        ),
        Quote(
            english = "Turn your wounds into wisdom.",
            japanese = "傷を知恵に変えよ。",
            romaji = "Kizu o chie ni kaeyo."
        ),
        Quote(
            english = "You don't get what you wish for. You get what you work for.",
            japanese = "願ったものは手に入らない。努力したものが手に入る。",
            romaji = "Negatta mono wa te ni hairanai. Doryoku shita mono ga te ni hairu."
        ),
        Quote(
            english = "No regrets. Just lessons.",
            japanese = "後悔なし。ただ教訓あり。",
            romaji = "Kōkai nashi. Tada kyōkun ari."
        ),
        Quote(
            english = "Be the change you wish to see in the world.",
            japanese = "世界に見たい変化そのものになれ。",
            romaji = "Sekai ni mitai henka sono mono ni nare."
        ),
        Quote(
            english = "When you want to succeed as bad as you want to breathe, then you'll be successful.",
            japanese = "呼吸したいと思うほど強く成功を望んだとき、あなたは成功する。",
            romaji = "Kokyū shitai to omou hodo tsuyoku seikō o nozonda toki, anata wa seikō suru."
        ),
        Quote(
            english = "If you always do what you've always done, you'll always get what you've always got.",
            japanese = "ずっと同じことをしていれば、ずっと同じ結果が得られる。",
            romaji = "Zutto onaji koto o shite ireba, zutto onaji kekka ga erareru."
        ),
        Quote(
            english = "Continuous effort — not strength or intelligence — is the key to unlocking our potential.",
            japanese = "継続的な努力こそが、力や知性ではなく、潜在能力を引き出す鍵だ。",
            romaji = "Keizokuteki na doryoku koso ga, chikara ya chisei de wa naku, senzai nōryoku o hikidasu kagi da."
        ),
        Quote(
            english = "You can't climb the ladder of success with your hands in your pockets.",
            japanese = "ポケットに手を入れたまま、成功の梯子を登ることはできない。",
            romaji = "Poketto ni te o ireta mama, seikō no hashigo o noboru koto wa dekinai."
        ),
        Quote(
            english = "Leap and the net will appear.",
            japanese = "飛び込め。網は現れる。",
            romaji = "Tobikome. Ami wa arawareru."
        ),
        Quote(
            english = "Work hard in silence, let success be your noise.",
            japanese = "黙して働け。成功が声となる。",
            romaji = "Mokushite hatarake. Seikō ga koe to naru."
        ),
        Quote(
            english = "Every morning brings new potential.",
            japanese = "毎朝、新しい可能性をもたらす。",
            romaji = "Maiasa, atarashii kanōsei o motarasu."
        ),
        Quote(
            english = "It always seems impossible until it becomes inevitable.",
            japanese = "避けられないことになるまで、いつも不可能に見える。",
            romaji = "Sakerarenai koto ni naru made, itsumo fukanō ni mieru."
        ),
        Quote(
            english = "No matter how hard the past, you can always begin again.",
            japanese = "過去がいくら辛くても、いつでもやり直せる。",
            romaji = "Kako ga ikura tsuraku temo, itsudemo yarinaoseru."
        ),
        Quote(
            english = "We rise by lifting others.",
            japanese = "他者を持ち上げることで、私たちは高まる。",
            romaji = "Taisha o mochiageru koto de, watashitachi wa takamaru."
        ),
        Quote(
            english = "The more you practice, the luckier you get.",
            japanese = "練習すればするほど、運が良くなる。",
            romaji = "Renshū sureba suru hodo, un ga yoku naru."
        ),
        Quote(
            english = "Keep your goals to yourself until they become results.",
            japanese = "目標が結果になるまで、自分だけの秘密にせよ。",
            romaji = "Mokuhyō ga kekka ni naru made, jibun dake no himitsu ni seyo."
        ),
        Quote(
            english = "Real change is difficult at the beginning, but gorgeous at the end.",
            japanese = "本当の変化は最初は難しいが、最後には素晴らしい。",
            romaji = "Hontō no henka wa saisho wa muzukashii ga, saigo ni wa subarashii."
        ),
        Quote(
            english = "An ounce of action is worth a ton of theory.",
            japanese = "1オンスの行動は、1トンの理論に値する。",
            romaji = "Ichi onsu no kōdō wa, ichi ton no riron ni atai suru."
        ),
        Quote(
            english = "When you feel like quitting, think about why you started.",
            japanese = "諦めたくなったとき、なぜ始めたかを考えよ。",
            romaji = "Akirametaku natta toki, naze hajimeta ka o kangae yo."
        ),
        Quote(
            english = "Great things take time.",
            japanese = "偉大なものには時間がかかる。",
            romaji = "Idai na mono ni wa jikan ga kakaru."
        ),
        Quote(
            english = "Success is not in what you have but in who you are.",
            japanese = "成功とは、あなたが持つものではなく、あなたが何者であるかにある。",
            romaji = "Seikō to wa, anata ga motsu mono de wa naku, anata ga nanimono de aru ka ni aru."
        ),
        Quote(
            english = "Prove them wrong.",
            japanese = "彼らが間違っていることを証明せよ。",
            romaji = "Karera ga machigatte iru koto o shōmei se yo."
        ),
        Quote(
            english = "Chase the vision, not the money.",
            japanese = "お金ではなく、ビジョンを追え。",
            romaji = "Okane de wa naku, bijon o oe."
        ),
        Quote(
            english = "Don't dream your life, live your dream.",
            japanese = "人生を夢見るな。夢を生きよ。",
            romaji = "Jinsei o yumemiru na. Yume o iki yo."
        ),
        Quote(
            english = "The time is now.",
            japanese = "今がその時だ。",
            romaji = "Ima ga sono toki da."
        ),
        Quote(
            english = "Fall in love with the process.",
            japanese = "プロセスを愛せ。",
            romaji = "Purosesu o aise."
        ),
        Quote(
            english = "Results require sacrifice.",
            japanese = "結果には犠牲が必要だ。",
            romaji = "Kekka ni wa gisei ga hitsuyō da."
        ),
        Quote(
            english = "Your journey is unique.",
            japanese = "あなたの旅は唯一無二だ。",
            romaji = "Anata no tabi wa yuiitsu muni da."
        ),
        Quote(
            english = "Rise above the storm and you will find the sunshine.",
            japanese = "嵐を越えれば、太陽を見つけるだろう。",
            romaji = "Arashi o koereba, taiyō o mitsukeru darō."
        ),
        Quote(
            english = "Mistakes are proof that you are trying.",
            japanese = "間違いは、あなたが挑戦している証拠だ。",
            romaji = "Machigai wa, anata ga chōsen shite iru shōko da."
        ),
        Quote(
            english = "Keep moving forward.",
            japanese = "前に進み続けろ。",
            romaji = "Mae ni susumi tsuzukero."
        ),
        Quote(
            english = "Stop being afraid of what could go wrong and start being excited about what could go right.",
            japanese = "うまくいかないことを恐れるのをやめ、うまくいくことに興奮し始めよ。",
            romaji = "Umaku ikanai koto o osoreru no o yame, umaku iku koto ni kōfun shi hajimeyo."
        ),
        Quote(
            english = "The pain you feel today will be the strength you feel tomorrow.",
            japanese = "今日感じる痛みが、明日感じる強さになる。",
            romaji = "Kyō kanjiru itami ga, ashita kanjiru tsuyosa ni naru."
        ),
        Quote(
            english = "Never apologize for having high standards.",
            japanese = "高い基準を持つことを決して謝るな。",
            romaji = "Takai kijun o motsu koto o kesshite ayamaru na."
        ),
        Quote(
            english = "Rise up and attack the day with enthusiasm.",
            japanese = "立ち上がり、熱意を持って一日に挑め。",
            romaji = "Tachiagari, netsui o motte ichinichi ni idome."
        ),
        Quote(
            english = "Ambition is the first step to success.",
            japanese = "野心は、成功への第一歩だ。",
            romaji = "Yashin wa, seikō e no daiippo da."
        ),
        Quote(
            english = "Silence is a source of great strength.",
            japanese = "沈黙は、大きな力の源だ。",
            romaji = "Chinmoku wa, ōkina chikara no minamoto da."
        ),
        Quote(
            english = "Adapt. Improve. Overcome.",
            japanese = "適応し、改善し、克服せよ。",
            romaji = "Tekiō shi, kaizen shi, kokufuku se yo."
        ),
        Quote(
            english = "Give more than you take.",
            japanese = "受け取る以上に与えよ。",
            romaji = "Ukotoru ijō ni ataereyo."
        ),
        Quote(
            english = "Persistence is the vehicle you arrive in.",
            japanese = "粘り強さは、あなたが乗り込む乗り物だ。",
            romaji = "Nebarizuyosa wa, anata ga norikomu norimono da."
        ),
        Quote(
            english = "Don't talk, just act. Don't say, just show. Don't promise, just prove.",
            japanese = "語るな、行動せよ。言うな、見せよ。約束するな、証明せよ。",
            romaji = "Kataru na, kōdō se yo. Iu na, misero. Yakusoku suru na, shōmei se yo."
        ),
        Quote(
            english = "You are the author of your own life story.",
            japanese = "あなたは自分の人生の物語の著者だ。",
            romaji = "Anata wa jibun no jinsei no monogatari no chosha da."
        ),
        Quote(
            english = "Every expert was once a disaster.",
            japanese = "すべての専門家は、かつて大失敗した者だった。",
            romaji = "Subete no senmonka wa, katsute daishippai shita mono datta."
        ),
        Quote(
            english = "Aspire to inspire before you expire.",
            japanese = "命が尽きる前に、人を鼓舞することを目指せ。",
            romaji = "Inochi ga tsukiru mae ni, hito o kobu suru koto o mezase."
        ),
        Quote(
            english = "Your life is your message to the world.",
            japanese = "あなたの人生は、世界へのメッセージだ。",
            romaji = "Anata no jinsei wa, sekai e no messēji da."
        ),
        Quote(
            english = "Self-belief and hard work will always earn you success.",
            japanese = "自己信頼と努力は、常に成功をもたらす。",
            romaji = "Jiko shinrai to doryoku wa, tsune ni seikō o motarasu."
        ),
        Quote(
            english = "In order to achieve something you've never achieved, you have to become someone you've never been.",
            japanese = "これまで達成したことのないものを達成するためには、これまでとは異なる人間にならなければならない。",
            romaji = "Kore made tassei shita koto no nai mono o tassei suru tame ni wa, kore made to wa kotonaru ningen ni naranakere ba naranai."
        ),
        Quote(
            english = "You are a product of your environment. Choose it well.",
            japanese = "あなたは環境の産物だ。良く選べ。",
            romaji = "Anata wa kankyō no sanbutsu da. Yoku erabe."
        ),
        Quote(
            english = "Determination today leads to success tomorrow.",
            japanese = "今日の決意が、明日の成功につながる。",
            romaji = "Kyō no ketsui ga, ashita no seikō ni tsunagaru."
        ),
        Quote(
            english = "Don't be satisfied with stories. How things have gone with others. Unfold your own myth.",
            japanese = "物語に満足するな。他の人の話ではなく、あなた自身の神話を展開せよ。",
            romaji = "Monogatari ni manzoku suru na. Hoka no hito no hanashi de wa naku, anata jishin no shinwa o tenkai se yo."
        ),
        Quote(
            english = "The present moment is the only moment available to us, and it is the door to all moments.",
            japanese = "現在の瞬間だけが、私たちに利用可能な唯一の瞬間であり、すべての瞬間への扉だ。",
            romaji = "Genzai no shunkan dake ga, watashitachi ni riyō kanō na yuiitsu no shunkan de ari, subete no shunkan e no tobira da."
        ),
        Quote(
            english = "Turn your can'ts into cans, your dreams into plans.",
            japanese = "できないをできるに、夢を計画に変えよ。",
            romaji = "Dekinai o dekiru ni, yume o keikaku ni kaeyo."
        ),
        Quote(
            english = "The best investment you can make is in yourself.",
            japanese = "あなたができる最高の投資は、自分自身への投資だ。",
            romaji = "Anata ga dekiru saikō no tōshi wa, jibun jishin e no tōshi da."
        ),
        Quote(
            english = "It's not about being the best; it's about being better than you were yesterday.",
            japanese = "最高になることではなく、昨日の自分より良くなることだ。",
            romaji = "Saikō ni naru koto de wa naku, kinō no jibun yori yoku naru koto da."
        ),
        Quote(
            english = "Live with intention.",
            japanese = "意図を持って生きよ。",
            romaji = "Ito o motte ikiyō."
        ),
        Quote(
            english = "Effort is the great equalizer.",
            japanese = "努力こそが、偉大な平等化要素だ。",
            romaji = "Doryoku koso ga, idai na byōdō ka yōso da."
        ),
        Quote(
            english = "Be brave enough to be bad at something new.",
            japanese = "新しいことが下手くそでも挑戦できる勇気を持て。",
            romaji = "Atarashii koto ga heta kuso demo chōsen dekiru yūki o mote."
        ),
        Quote(
            english = "Never confuse movement with progress.",
            japanese = "動くことと前進を混同するな。",
            romaji = "Ugoku koto to zenshin o kondō suru na."
        ),
        Quote(
            english = "Act as if what you do makes a difference. It does.",
            japanese = "あなたのすることが違いを生むかのように行動せよ。実際に生む。",
            romaji = "Anata no suru koto ga chigai o umu ka no yō ni kōdō se yo. Jissai ni umu."
        ),
        Quote(
            english = "Take the risk or lose the chance.",
            japanese = "リスクを取るか、チャンスを失うか。",
            romaji = "Risuku o toru ka, chansu o ushinau ka."
        ),
        Quote(
            english = "Train your mind to see the good in every situation.",
            japanese = "あらゆる状況に良いことを見出せるよう、心を鍛えよ。",
            romaji = "Arayuru jōkyō ni yoi koto o miidase ru yō, kokoro o kitae yo."
        ),
        Quote(
            english = "Be so positive that negative people don't want to be around you.",
            japanese = "ネガティブな人が近づきたくないほどポジティブであれ。",
            romaji = "Negatibu na hito ga chikadzukitakunai hodo pojithibu de are."
        ),
        Quote(
            english = "Stop waiting and start creating.",
            japanese = "待つのをやめ、作ることを始めよ。",
            romaji = "Matsu no o yame, tsukuru koto o hajimeyo."
        ),
        Quote(
            english = "One day at a time.",
            japanese = "一日一日を大切に。",
            romaji = "Ichinichi ichinichi o taisetsu ni."
        ),
        Quote(
            english = "You can do hard things.",
            japanese = "あなたは困難なことができる。",
            romaji = "Anata wa konnan na koto ga dekiru."
        ),
        Quote(
            english = "Gratitude transforms what we have into enough.",
            japanese = "感謝は、私たちが持つものを十分なものに変える。",
            romaji = "Kansha wa, watashitachi ga motsu mono o jūbun na mono ni kaeru."
        ),
        Quote(
            english = "A goal without a plan is just a wish.",
            japanese = "計画のない目標は、ただの願い事だ。",
            romaji = "Keikaku no nai mokuhyō wa, tada no negaigoto da."
        ),
        Quote(
            english = "You're capable of so much more.",
            japanese = "あなたはもっとずっと多くのことができる。",
            romaji = "Anata wa motto zutto ōku no koto ga dekiru."
        ),
        Quote(
            english = "Life begins at the end of your comfort zone.",
            japanese = "人生は、コンフォートゾーンの終わりから始まる。",
            romaji = "Jinsei wa, konfo-to zōn no owari kara hajimaru."
        ),
        Quote(
            english = "Stop starting. Start finishing.",
            japanese = "始めることをやめよ。終わらせることを始めよ。",
            romaji = "Hajimeru koto o yameyō. Owaraseru koto o hajimeyō."
        ),
        Quote(
            english = "Do the best you can until you know better. Then when you know better, do better.",
            japanese = "より良いやり方を知るまで、できる限り最善を尽くせ。そして知ったら、もっとうまくやれ。",
            romaji = "Yori yoi yarikata o shiru made, dekiru kagiri saizen o tsukuse. Soshite shittara, motto umaku yare."
        ),
        Quote(
            english = "Leadership begins with taking responsibility.",
            japanese = "リーダーシップは、責任を取ることから始まる。",
            romaji = "Rīdāshippu wa, sekinin o toru koto kara hajimaru."
        ),
        Quote(
            english = "The key is not to prioritize what's on your schedule, but to schedule your priorities.",
            japanese = "スケジュールにあるものを優先するのではなく、優先事項をスケジュールすることが重要だ。",
            romaji = "Sukejūru ni aru mono o yūsen suru no de wa naku, yūsen jikō o sukejūru suru koto ga jūyō da."
        ),
        Quote(
            english = "Throw kindness around like confetti.",
            japanese = "紙吹雪のように、至る所に優しさをまき散らせ。",
            romaji = "Kamifubuki no yō ni, itaru tokoro ni yasashisa o makichirasa e."
        ),
        Quote(
            english = "Surround yourself with people who push you to do better.",
            japanese = "あなたをより良くさせてくれる人々に囲まれよ。",
            romaji = "Anata o yori yoku sasete kureru hitobito ni kakomare yo."
        ),
        Quote(
            english = "Success is a state of mind.",
            japanese = "成功とは、心の状態だ。",
            romaji = "Seikō to wa, kokoro no jōtai da."
        ),
        Quote(
            english = "Don't lower your expectations; raise your efforts.",
            japanese = "期待を下げるな。努力を上げよ。",
            romaji = "Kitai o sageru na. Doryoku o ageyō."
        ),
        Quote(
            english = "Be a student as long as you still have something to learn.",
            japanese = "学ぶものがある限り、学び続けよ。",
            romaji = "Manabu mono ga aru kagiri, manabi tsudukeyō."
        ),
        Quote(
            english = "Wake up. Work out. Be grateful.",
            japanese = "目覚めよ。体を動かせ。感謝せよ。",
            romaji = "Mezameyo. Karada o ugokase. Kansha se yo."
        ),
        Quote(
            english = "Do more of what makes you come alive.",
            japanese = "あなたを生き生きとさせることをもっとやれ。",
            romaji = "Anata o ikiiki to saseru koto o motto yare."
        ),
        Quote(
            english = "Let your dreams be bigger than your fears.",
            japanese = "夢を恐れより大きくせよ。",
            romaji = "Yume o osore yori ōkiku se yo."
        ),
        Quote(
            english = "This is not the time to be comfortable. This is the time to be great.",
            japanese = "今は安心している時間ではない。今は偉大になる時間だ。",
            romaji = "Ima wa anshin shite iru jikan de wa nai. Ima wa idai ni naru jikan da."
        ),
        Quote(
            english = "Life rewards the brave.",
            japanese = "人生は勇者に報いる。",
            romaji = "Jinsei wa yūsha ni mukuiru."
        ),
        Quote(
            english = "Resilience is built in the moments you want to give up.",
            japanese = "諦めたくなる瞬間に、回復力は築かれる。",
            romaji = "Akirametaku naru shunkan ni, kaifukukyoku wa kizukarreru."
        ),
        Quote(
            english = "Be relentless.",
            japanese = "容赦なく努め続けよ。",
            romaji = "Yōsha naku tsutome tsuzukeyō."
        ),
        Quote(
            english = "The only way out is through.",
            japanese = "抜け出す唯一の方法は、突き進むことだ。",
            romaji = "Nukedasu yuiitsu no hōhō wa, tsukisusumu koto da."
        ),
        Quote(
            english = "You grow through what you go through.",
            japanese = "経験することで成長する。",
            romaji = "Keiken suru koto de seichō suru."
        ),
        Quote(
            english = "Honor your commitments.",
            japanese = "約束を守れ。",
            romaji = "Yakusoku o mamore."
        ),
        Quote(
            english = "The mirror shows your face; your habits show your character.",
            japanese = "鏡はあなたの顔を映す。習慣はあなたの性格を映す。",
            romaji = "Kagami wa anata no kao o utsusu. Shūkan wa anata no seikaku o utsusu."
        ),
        Quote(
            english = "Tension is who you think you should be. Relaxation is who you are.",
            japanese = "緊張はあなたがなるべき人。くつろぎはあなた本来の姿だ。",
            romaji = "Kinchō wa anata ga naru beki hito. Kutsurogi wa anata honrai no sugata da."
        ),
        Quote(
            english = "Your present circumstances don't determine where you can go; they merely determine where you start.",
            japanese = "現在の状況はあなたがどこへ行けるかを決めない。ただ出発点を決めるだけだ。",
            romaji = "Genzai no jōkyō wa anata ga doko e ikeru ka o kimeru nai. Tada shuppatsu ten o kimeru dake da."
        ),
        Quote(
            english = "Let go of yesterday. Take hold of today.",
            japanese = "昨日を手放せ。今日をつかめ。",
            romaji = "Kinō o tebanase. Kyō o tsukame."
        ),
        Quote(
            english = "A disciplined mind leads to happiness.",
            japanese = "規律ある心は、幸福をもたらす。",
            romaji = "Kiritsu aru kokoro wa, kōfuku o motarasu."
        ),
        Quote(
            english = "We suffer more in imagination than in reality.",
            japanese = "私たちは現実よりも想像の中でより苦しむ。",
            romaji = "Watashitachi wa genjitsu yori mo sōzō no naka de yori kurushimu."
        ),
        Quote(
            english = "Suffer the pain of discipline, or suffer the pain of regret.",
            japanese = "規律の苦しみを受け入れよ。さもなければ後悔の苦しみを受けることになる。",
            romaji = "Kiritsu no kurushimi o ukeireyō. Samonakere ba kōkai no kurushimi o ukeru koto ni naru."
        ),
        Quote(
            english = "Take massive, imperfect action.",
            japanese = "大規模で不完全な行動を起こせ。",
            romaji = "Daikibo de fukanzen na kōdō o okose."
        ),
        Quote(
            english = "Eat sleep grind repeat.",
            japanese = "食べ、眠り、努力し、繰り返せ。",
            romaji = "Tabe, nemuri, doryoku shi, kurikaese."
        ),
        Quote(
            english = "Results don't lie.",
            japanese = "結果は嘘をつかない。",
            romaji = "Kekka wa uso o tsukanai."
        ),
        Quote(
            english = "Good habits are hard to form but easy to live with. Bad habits are easy to form but hard to live with.",
            japanese = "良い習慣は形成が難しいが、共に生きるのは楽だ。悪い習慣は形成が簡単だが、共に生きるのは辛い。",
            romaji = "Yoi shūkan wa keisei ga muzukashii ga, tomo ni ikiru no wa raku da. Warui shūkan wa keisei ga kantan da ga, tomo ni ikiru no wa tsurai."
        ),
        Quote(
            english = "We are more often frightened than hurt; our troubles spring more from fancy than from fact.",
            japanese = "私たちは傷つくよりも怖がることの方が多い。悩みの多くは事実よりも想像から生まれる。",
            romaji = "Watashitachi wa kizutsuku yori mo kowagaru koto no hō ga ōi. Nayami no ōku wa jijitsu yori mo sōzō kara umareru."
        ),
        Quote(
            english = "It's not the mountain we conquer, but ourselves.",
            japanese = "私たちが征服するのは山ではなく、自分自身だ。",
            romaji = "Watashitachi ga seifuku suru no wa yama de wa naku, jibun jishin da."
        ),
        Quote(
            english = "The secret to success is consistency of purpose.",
            japanese = "成功の秘訣は、目的に対する一貫性だ。",
            romaji = "Seikō no hiketsu wa, mokuteki ni taisuru ikkansei da."
        ),
        Quote(
            english = "Luck is what happens when preparation meets opportunity.",
            japanese = "運とは、準備と機会が出会ったときに起きるものだ。",
            romaji = "Un to wa, junbi to kikai ga deatta toki ni okiru mono da."
        ),
        Quote(
            english = "Make yourself proud.",
            japanese = "自分が誇れる自分になれ。",
            romaji = "Jibun ga hokoreru jibun ni nare."
        ),
        Quote(
            english = "What we fear doing most is usually what we most need to do.",
            japanese = "私たちが最も恐れてすることは、たいてい最も必要なことだ。",
            romaji = "Watashitachi ga mottomo osorete suru koto wa, taitei mottomo hitsuyō na koto da."
        ),
        Quote(
            english = "The number one reason people fail in life is because they listen to their friends, family, and neighbors.",
            japanese = "人が人生で失敗する最大の理由は、友人、家族、隣人の言葉を聞くからだ。",
            romaji = "Hito ga jinsei de shippai suru saidai no riyū wa, yūjin, kazoku, rinjin no kotoba o kiku kara da."
        ),
        Quote(
            english = "Be honest, work hard, and great things will follow.",
            japanese = "誠実で、懸命に働けば、素晴らしいことが続く。",
            romaji = "Seijitsu de, kenmei ni hatarake ba, subarashii koto ga tsuzuku."
        ),
        Quote(
            english = "The worst enemy of success is self-doubt.",
            japanese = "成功の最大の敵は、自己不信だ。",
            romaji = "Seikō no saidai no teki wa, jiko fushin da."
        ),
        Quote(
            english = "Don't study me. You won't graduate.",
            japanese = "私を研究するな。卒業できないぞ。",
            romaji = "Watashi o kenkyū suru na. Sotsugyō dekinai zo."
        ),
        Quote(
            english = "Failure is simply the opportunity to begin again, this time more intelligently.",
            japanese = "失敗は、今度はより賢くやり直す機会に過ぎない。",
            romaji = "Shippai wa, kondo wa yori kashikoku yarinaosu kikai ni suginai."
        ),
        Quote(
            english = "The more you help others succeed, the more you'll succeed.",
            japanese = "他者の成功を助けるほど、あなたも成功する。",
            romaji = "Taisha no seikō o tasukeru hodo, anata mo seikō suru."
        ),
        Quote(
            english = "Decide. Commit. Succeed.",
            japanese = "決断せよ。コミットせよ。成功せよ。",
            romaji = "Ketsudan se yo. Komitto se yo. Seikō se yo."
        ),
        Quote(
            english = "Try and fail, but never fail to try.",
            japanese = "試みて失敗せよ。しかし、試みることを絶対に諦めるな。",
            romaji = "Kokoromite shippai se yo. Shikashi, kokoromiru koto o zettai ni akirameru na."
        ),
        Quote(
            english = "The ladder of success is best climbed by stepping on the rungs of opportunity.",
            japanese = "成功の梯子は、機会の踏み台を踏むことで最もうまく登れる。",
            romaji = "Seikō no hashigo wa, kikai no fumidai o fumu koto de mottomo umaku noboreru."
        ),
        Quote(
            english = "When you focus on problems you'll have more problems. When you focus on possibilities you'll have more opportunities.",
            japanese = "問題に集中すれば、問題が増える。可能性に集中すれば、機会が増える。",
            romaji = "Mondai ni shūchū sureba, mondai ga fueru. Kanōsei ni shūchū sureba, kikai ga fueru."
        ),
        Quote(
            english = "The fire you have inside can change the world.",
            japanese = "あなたの内に燃える炎は、世界を変えられる。",
            romaji = "Anata no uchi ni moeru honō wa, sekai o kaerareru."
        ),
        Quote(
            english = "The only failure is not trying.",
            japanese = "唯一の失敗は、試みないことだ。",
            romaji = "Yuiitsu no shippai wa, kokoromina koto da."
        ),
        Quote(
            english = "Believe deep down in your heart that you're destined to do great things.",
            japanese = "偉大なことをする運命にあると、心の奥底から信じよ。",
            romaji = "Idai na koto o suru unmei ni aru to, kokoro no okosoko kara shinjiyō."
        ),
        Quote(
            english = "Don't wait. The time will never be just right.",
            japanese = "待つな。時はちょうどいいタイミングにはならない。",
            romaji = "Matsu na. Toki wa chōdo ii taimingu ni wa naranai."
        ),
        Quote(
            english = "Our aspirations are our possibilities.",
            japanese = "私たちの願望は、可能性そのものだ。",
            romaji = "Watashitachi no ganbō wa, kanōsei sono mono da."
        ),
        Quote(
            english = "Success is the progressive realization of a worthy goal.",
            japanese = "成功とは、価値ある目標の漸進的な実現だ。",
            romaji = "Seikō to wa, kachi aru mokuhyō no zenshinnteki na jitsugen da."
        ),
        Quote(
            english = "If you want to achieve greatness, stop asking for permission.",
            japanese = "偉大さを達成したければ、許可を求めるのをやめよ。",
            romaji = "Idaisa o tassei shitakereba, kyoka o motomeru no o yameyō."
        ),
        Quote(
            english = "Work is the key to success; you can't get anywhere without it.",
            japanese = "努力は成功の鍵だ。それなしにはどこへも行けない。",
            romaji = "Doryoku wa seikō no kagi da. Sore nashi ni wa doko e mo ikenai."
        ),
        Quote(
            english = "You must expect great things of yourself before you can do them.",
            japanese = "偉大なことをする前に、自分自身に偉大なことを期待しなければならない。",
            romaji = "Idai na koto o suru mae ni, jibun jishin ni idai na koto o kitai shi nakereba naranai."
        ),
        Quote(
            english = "Don't let what you cannot do interfere with what you can do.",
            japanese = "できないことが、できることの邪魔をさせるな。",
            romaji = "Dekinai koto ga, dekiru koto no jama o saseru na."
        ),
        Quote(
            english = "Today is a good day for a good day.",
            japanese = "今日は良い一日を過ごすのに最高の日だ。",
            romaji = "Kyō wa yoi ichinichi o suguru no ni saikō no hi da."
        ),
        Quote(
            english = "What comes easy won't last. What lasts won't come easy.",
            japanese = "簡単に手に入るものは長続きしない。長続きするものは簡単には手に入らない。",
            romaji = "Kantan ni te ni hairu mono wa nagatsuzuki shinai. Nagatsuzuki suru mono wa kantan ni wa te ni hairanai."
        ),
        Quote(
            english = "The secret ingredient is always hard work.",
            japanese = "秘密の材料は、常に努力だ。",
            romaji = "Himitsu no zairyō wa, tsune ni doryoku da."
        ),
        Quote(
            english = "Eat, sleep, and breathe your passion.",
            japanese = "情熱を食べ、眠り、呼吸せよ。",
            romaji = "Jōnetsu o tabe, nemuri, kokyū se yo."
        ),
        Quote(
            english = "Remember why you started.",
            japanese = "なぜ始めたかを思い出せ。",
            romaji = "Naze hajimeta ka o omoidase."
        ),
        Quote(
            english = "Earn your sleep.",
            japanese = "眠りに値することをせよ。",
            romaji = "Nemuri ni atai suru koto o se yo."
        ),
        Quote(
            english = "Progress is a choice.",
            japanese = "前進は選択だ。",
            romaji = "Zenshin wa sentaku da."
        ),
        Quote(
            english = "Let your inner light guide you.",
            japanese = "内なる光にガイドさせよ。",
            romaji = "Uchi naru hikari ni gaido sa seyō."
        ),
        Quote(
            english = "Finish each day and be done with it.",
            japanese = "毎日を終え、それで終わりにせよ。",
            romaji = "Mainichi o oeri, sore de owari ni se yo."
        ),
        Quote(
            english = "Clarity of mind means clarity of passion.",
            japanese = "心の明確さは、情熱の明確さを意味する。",
            romaji = "Kokoro no meikakusa wa, jōnetsu no meikakusa o imi suru."
        ),
        Quote(
            english = "Give it everything you've got.",
            japanese = "持てる力のすべてを注げ。",
            romaji = "Moteru chikara no subete o tsoge."
        ),
        Quote(
            english = "Move in silence. Let your success roar.",
            japanese = "沈黙の中で動け。成功が咆哮するだろう。",
            romaji = "Chinmoku no naka de ugoke. Seikō ga hōkō suru darō."
        ),
        Quote(
            english = "If you're not making mistakes, you're not taking risks, and that means you're not going anywhere.",
            japanese = "間違いを犯していないなら、リスクを取っていない。それはどこにも行っていないということだ。",
            romaji = "Machigai o okashite inai nara, risuku o totte inai. Sore wa doko ni mo itte inai to iu koto da."
        ),
        Quote(
            english = "Outperform your previous self daily.",
            japanese = "毎日、かつての自分を超えよ。",
            romaji = "Mainichi, katsute no jibun o koe yo."
        ),
        Quote(
            english = "Success isn't given; it's earned.",
            japanese = "成功は与えられるものではなく、勝ち取るものだ。",
            romaji = "Seikō wa ataerareru mono de wa naku, kachitoru mono da."
        ),
        Quote(
            english = "True grit is making a decision and standing by it, doing what must be done.",
            japanese = "真の根性とは、決断を下し、それを守り、なすべきことをすることだ。",
            romaji = "Shin no konjō to wa, ketsudan o kudashi, sore o mamori, nasu beki koto o suru koto da."
        ),
        Quote(
            english = "Create your own sunshine.",
            japanese = "自分自身の太陽を作れ。",
            romaji = "Jibun jishin no taiyō o tsukure."
        ),
        Quote(
            english = "Find something you're passionate about and keep tremendously interested in it.",
            japanese = "情熱を持てることを見つけ、それへの強い関心を持ち続けよ。",
            romaji = "Jōnetsu o moteru koto o mitsuke, sore e no tsuyoi kanshin o mochi tsuzuke yo."
        ),
        Quote(
            english = "A burning desire is the starting point of all achievement.",
            japanese = "燃えるような欲望こそが、すべての達成の出発点だ。",
            romaji = "Moeru yō na yokubō koso ga, subete no tassei no shuppatsu ten da."
        ),
        Quote(
            english = "You have to fight through some bad days to earn the best days of your life.",
            japanese = "人生最高の日を勝ち取るために、いくつかの辛い日を乗り越えなければならない。",
            romaji = "Jinsei saikō no hi o kachitoru tame ni, ikutsu ka no tsurai hi o norikoe nakereba naranai."
        ),
        Quote(
            english = "Be a warrior, not a worrier.",
            japanese = "心配する者ではなく、戦士であれ。",
            romaji = "Shinpai suru mono de wa naku, senshi de are."
        ),
        Quote(
            english = "Live life to the fullest.",
            japanese = "人生を最大限に生きよ。",
            romaji = "Jinsei o saidaigen ni ikiyō."
        ),
        Quote(
            english = "Hustle until you no longer need to introduce yourself.",
            japanese = "自己紹介が不要になるまで、懸命に努力せよ。",
            romaji = "Jiko shōkai ga fuyō ni naru made, kenmei ni doryoku se yo."
        ),
        Quote(
            english = "Success has a simple formula: do your best and people may like it.",
            japanese = "成功にはシンプルな公式がある。最善を尽くせ、そうすれば人々は気に入るかもしれない。",
            romaji = "Seikō ni wa shimpuru na kōshiki ga aru. Saizen o tsukuse, sō sureba hitobito wa ki ni iru kamoshirenai."
        ),
        Quote(
            english = "The biggest adventure you can take is to live the life of your dreams.",
            japanese = "あなたが取ることができる最大の冒険は、夢の人生を生きることだ。",
            romaji = "Anata ga toru koto ga dekiru saidai no bōken wa, yume no jinsei o ikiru koto da."
        ),
        Quote(
            english = "Do it with passion or not at all.",
            japanese = "情熱を持ってやれ。さもなければやめよ。",
            romaji = "Jōnetsu o motte yare. Samonakere ba yame yo."
        ),
        Quote(
            english = "Success is a journey, not a destination.",
            japanese = "成功は旅だ。目的地ではない。",
            romaji = "Seikō wa tabi da. Mokutekichi de wa nai."
        ),
        Quote(
            english = "Stop thinking about it and start doing it.",
            japanese = "考えるのをやめ、始めよ。",
            romaji = "Kangaeru no o yame, hajimeyo."
        ),
        Quote(
            english = "Tough times reveal strong people.",
            japanese = "辛い時代が、強い人を明らかにする。",
            romaji = "Tsurai jidai ga, tsuyoi hito o akiraka ni suru."
        ),
        Quote(
            english = "Every champion was once a beginner.",
            japanese = "すべてのチャンピオンは、かつて初心者だった。",
            romaji = "Subete no chanpion wa, katsute shoshinsha datta."
        ),
        Quote(
            english = "Wake up. Kick ass. Repeat.",
            japanese = "目覚めよ。やり遂げよ。繰り返せ。",
            romaji = "Mezameyo. Yaritogeyō. Kurikaese."
        ),
        Quote(
            english = "Winners are not people who never fail; they are people who never quit.",
            japanese = "勝者とは、決して失敗しない人ではなく、決して諦めない人だ。",
            romaji = "Shōsha to wa, kesshite shippai shinai hito de wa naku, kesshite akiramenai hito da."
        ),
        Quote(
            english = "Work for it more than you pray for it.",
            japanese = "祈るよりも、それのために働け。",
            romaji = "Inoru yori mo, sore no tame ni hatarake."
        ),
        Quote(
            english = "Opportunities are usually disguised as hard work.",
            japanese = "チャンスはたいてい、努力に変装している。",
            romaji = "Chansu wa taitei, doryoku ni hensō shite iru."
        ),
        Quote(
            english = "Be the hardest working person in the room.",
            japanese = "部屋で最も懸命に働く人であれ。",
            romaji = "Heya de mottomo kenmei ni hataraku hito de are."
        ),
        Quote(
            english = "Great vision without great people is irrelevant.",
            japanese = "偉大な人材なしの偉大なビジョンは、意味がない。",
            romaji = "Idai na jinzai nashi no idai na bijon wa, imi ga nai."
        ),
        Quote(
            english = "Every night I remind myself that my future depends on what I do today.",
            japanese = "毎晩、自分の未来は今日の行動にかかっていることを思い出す。",
            romaji = "Maiban, jibun no mirai wa kyō no kōdō ni kakatte iru koto o omoidasu."
        ),
        Quote(
            english = "Burn the midnight oil to outshine the day.",
            japanese = "昼間を輝かせるために、深夜まで油を燃やせ。",
            romaji = "Hiruma o kagayakaseru tame ni, shin'ya made abura o moyase."
        ),
        Quote(
            english = "Do what is right, not what is easy.",
            japanese = "簡単なことではなく、正しいことをせよ。",
            romaji = "Kantan na koto de wa naku, tadashii koto o se yo."
        ),
        Quote(
            english = "The taste of success is sweet.",
            japanese = "成功の味は甘い。",
            romaji = "Seikō no aji wa amai."
        ),
        Quote(
            english = "Every big goal was once a small idea.",
            japanese = "すべての大きな目標は、かつて小さなアイデアだった。",
            romaji = "Subete no ōkina mokuhyō wa, katsute chiisana aidea datta."
        ),
        Quote(
            english = "Visualize, then actualize.",
            japanese = "視覚化せよ、そして実現せよ。",
            romaji = "Shikakuka se yo, soshite jitsugen se yo."
        ),
        Quote(
            english = "Master your craft.",
            japanese = "自分の技術を極めよ。",
            romaji = "Jibun no gijutsu o kiwame yo."
        ),
        Quote(
            english = "The best revenge is not to be like your enemy.",
            japanese = "最高の復讐は、敵のようにならないことだ。",
            romaji = "Saikō no fukushū wa, teki no yō ni naranai koto da."
        ),
        Quote(
            english = "Not all those who wander are lost.",
            japanese = "さまよう者がすべて迷っているわけではない。",
            romaji = "Samayou mono ga subete mayotte iru wake de wa nai."
        ),
        Quote(
            english = "Train hard, fight easy.",
            japanese = "厳しく鍛え、楽に戦え。",
            romaji = "Kibishiku kitae, raku ni tatakae."
        ),
        Quote(
            english = "Grit is sticking with your future, day in and day out.",
            japanese = "根性とは、毎日毎日、自分の未来に向けて踏ん張り続けることだ。",
            romaji = "Konjō to wa, mainichi mainichi, jibun no mirai ni mukete funbari tsudukeru koto da."
        ),
        Quote(
            english = "Do not be embarrassed by your failures; learn from them and start again.",
            japanese = "失敗を恥じるな。そこから学び、再び始めよ。",
            romaji = "Shippai o hajiru na. Soko kara manabi, futatabi hajime yo."
        ),
        Quote(
            english = "Every second you spend doubting yourself is a second of your potential going to waste.",
            japanese = "自分を疑うすべての瞬間は、あなたの潜在能力が無駄になる瞬間だ。",
            romaji = "Jibun o utagau subete no shunkan wa, anata no senzai nōryoku ga muda ni naru shunkan da."
        ),
        Quote(
            english = "Your life is a result of the choices you make.",
            japanese = "あなたの人生は、あなたの選択の結果だ。",
            romaji = "Anata no jinsei wa, anata no sentaku no kekka da."
        ),
        Quote(
            english = "The goal is not to be perfect by the end. The goal is to be better today.",
            japanese = "目標は最終的に完璧になることではない。今日より良くなることだ。",
            romaji = "Mokuhyō wa saishūteki ni kanpeki ni naru koto de wa nai. Kyō yori yoku naru koto da."
        ),
        Quote(
            english = "It's a slow process, but quitting won't speed it up.",
            japanese = "ゆっくりとしたプロセスだが、諦めても速くならない。",
            romaji = "Yukkuri to shita purosesu da ga, akiramete mo hayaku naranai."
        ),
        Quote(
            english = "Forge your path with purpose.",
            japanese = "目的を持って自分の道を切り拓け。",
            romaji = "Mokuteki o motte jibun no michi o kirihirake."
        ),
        Quote(
            english = "Great journeys begin with small, deliberate steps.",
            japanese = "偉大な旅は、小さく意図した一歩から始まる。",
            romaji = "Idai na tabi wa, chiisaku ito shita ippo kara hajimaru."
        ),
        Quote(
            english = "Take the stairs every time.",
            japanese = "いつでも階段を選べ。",
            romaji = "Itsudemo kaidan o erabe."
        ),
        Quote(
            english = "You don't have to be extreme. Just be consistent.",
            japanese = "極端である必要はない。ただ一貫していればいい。",
            romaji = "Kyokutan de aru hitsuyō wa nai. Tada ikkan shite ireba ii."
        ),
        Quote(
            english = "Success is not for the lazy.",
            japanese = "成功は、怠惰な者のためにあるのではない。",
            romaji = "Seikō wa, taida na mono no tame ni aru no de wa nai."
        ),
        Quote(
            english = "Each season of hardship is a seed of strength.",
            japanese = "苦難のすべての季節は、強さの種だ。",
            romaji = "Kunan no subete no kisetsu wa, tsuyosa no tane da."
        ),
        Quote(
            english = "You can have results or excuses. Not both.",
            japanese = "結果か言い訳か。両方は持てない。",
            romaji = "Kekka ka iiwake ka. Ryōhō wa motenai."
        ),
        Quote(
            english = "Self-discipline is the foundation of all success.",
            japanese = "自己規律は、すべての成功の基盤だ。",
            romaji = "Jiko kiritsu wa, subete no seikō no kiban da."
        ),
        Quote(
            english = "Every day is a gift. Treat it that way.",
            japanese = "毎日は贈り物だ。そのように扱え。",
            romaji = "Mainichi wa okurimono da. Sono yō ni atsukae."
        ),
        Quote(
            english = "Stillness is where creativity and solutions are found.",
            japanese = "静けさの中に、創造性と解決策がある。",
            romaji = "Shizukesa no naka ni, sōzōsei to kaiketsusaku ga aru."
        ),
        Quote(
            english = "Stop making excuses and start making progress.",
            japanese = "言い訳をやめ、前進を始めよ。",
            romaji = "Iiwake o yame, zenshin o hajimeyo."
        ),
        Quote(
            english = "You are becoming who you are meant to be.",
            japanese = "あなたはなるべき存在になりつつある。",
            romaji = "Anata wa naru beki sonzai ni naritsutsua ru."
        ),
        Quote(
            english = "Be curious. Stay curious.",
            japanese = "好奇心を持て。好奇心を持ち続けよ。",
            romaji = "Kōkishin o mote. Kōkishin o mochi tsudukeyō."
        ),
        Quote(
            english = "All progress takes place outside the comfort zone.",
            japanese = "すべての前進は、コンフォートゾーンの外で起きる。",
            romaji = "Subete no zenshin wa, konfo-to zōn no soto de okiru."
        ),
        Quote(
            english = "Success is not an accident.",
            japanese = "成功は偶然ではない。",
            romaji = "Seikō wa gūzen de wa nai."
        ),
        Quote(
            english = "The purpose of life is a life of purpose.",
            japanese = "人生の目的は、目的のある人生だ。",
            romaji = "Jinsei no mokuteki wa, mokuteki no aru jinsei da."
        ),
        Quote(
            english = "Your choices are your character.",
            japanese = "あなたの選択が、あなたの性格だ。",
            romaji = "Anata no sentaku ga, anata no seikaku da."
        ),
        Quote(
            english = "You are one decision away from a completely different life.",
            japanese = "あなたは全く異なる人生から、一つの決断の距離にいる。",
            romaji = "Anata wa mattaku kotonaru jinsei kara, hitotsu no ketsudan no kyori ni iru."
        ),
        Quote(
            english = "Never give up. Great things take time.",
            japanese = "決して諦めるな。偉大なものには時間がかかる。",
            romaji = "Kesshite akirameru na. Idai na mono ni wa jikan ga kakaru."
        ),
        Quote(
            english = "Your commitment to yourself is the most powerful commitment you can make.",
            japanese = "自分自身へのコミットメントは、あなたができる最も強力なコミットメントだ。",
            romaji = "Jibun jishin e no komittomento wa, anata ga dekiru mottomo kyōryoku na komittomento da."
        ),
        Quote(
            english = "Hard days are the best days because that is when champions are made.",
            japanese = "辛い日が最高の日だ。それはチャンピオンが生まれる時だからだ。",
            romaji = "Tsurai hi ga saikō no hi da. Sore wa chanpion ga umareru toki dakara da."
        ),
        Quote(
            english = "Be bold enough to use your voice.",
            japanese = "自分の声を使う勇気を持て。",
            romaji = "Jibun no koe o tsukau yūki o mote."
        ),
        Quote(
            english = "Step by step, day by day.",
            japanese = "一歩一歩、日々着実に。",
            romaji = "Ippo ippo, hibi chakujitsu ni."
        ),
        Quote(
            english = "Hardship often prepares an ordinary person for an extraordinary destiny.",
            japanese = "苦難はしばしば、普通の人を非凡な運命へと準備させる。",
            romaji = "Kunan wa shiba shiba, futsū no hito o hibon na unmei e to junbi sa seru."
        ),
        Quote(
            english = "Struggle is the father of all things.",
            japanese = "苦難は万物の父だ。",
            romaji = "Kunan wa banbutsu no chichi da."
        ),
        Quote(
            english = "Lose an hour in the morning and you'll spend all day looking for it.",
            japanese = "朝に一時間を失えば、一日中それを探すことになる。",
            romaji = "Asa ni ichijikan o ushinaeba, ichinichijū sore o sagasu koto ni naru."
        ),
        Quote(
            english = "Either you master the day, or the day masters you.",
            japanese = "あなたが一日を支配するか、一日があなたを支配するかだ。",
            romaji = "Anata ga ichinichi o shihai suru ka, ichinichi ga anata o shihai suru ka da."
        ),
        Quote(
            english = "Elevate your standards; your life will follow.",
            japanese = "基準を高めよ。人生はそれに続く。",
            romaji = "Kijun o takame yo. Jinsei wa sore ni tsuzuku."
        ),
        Quote(
            english = "Turn your obstacles into opportunities.",
            japanese = "障害をチャンスに変えよ。",
            romaji = "Shōgai o chansu ni kaeyo."
        ),
        Quote(
            english = "Stay focused and never give up.",
            japanese = "集中し、決して諦めるな。",
            romaji = "Shūchū shi, kesshite akirameru na."
        ),
        Quote(
            english = "The graveyard is the richest place on earth, because it is here that you will find all the hopes and dreams that were never fulfilled.",
            japanese = "墓地は地球上で最も豊かな場所だ。実現しなかったすべての希望と夢がそこにあるから。",
            romaji = "Bochi wa chikyū jō de mottomo yutaka na basho da. Jitsugen shinakatta subete no kibō to yume ga soko ni aru kara."
        ),
        Quote(
            english = "Success is built on the daily decisions you make.",
            japanese = "成功は、あなたが毎日下す決断の上に築かれる。",
            romaji = "Seikō wa, anata ga mainichi kudasu ketsudan no ue ni kizukarreru."
        ),
        Quote(
            english = "Write your story with purpose.",
            japanese = "目的を持って自分の物語を書け。",
            romaji = "Mokuteki o motte jibun no monogatari o kake."
        ),
        Quote(
            english = "Be unstoppable.",
            japanese = "止められない存在になれ。",
            romaji = "Tomerarenai sonzai ni nare."
        ),
        Quote(
            english = "Start with what you have.",
            japanese = "今あるもので始めよ。",
            romaji = "Ima aru mono de hajimeyo."
        ),
        Quote(
            english = "Dare to be different.",
            japanese = "違うことを恐れるな。あえて違え。",
            romaji = "Chigau koto o osoreru na. Aete chigae."
        ),
        Quote(
            english = "You've survived 100% of your worst days so far.",
            japanese = "あなたはこれまでの最悪の日々を100%生き延びてきた。",
            romaji = "Anata wa kore made no saiaku no hibi o 100% ikinobite kita."
        ),
        Quote(
            english = "Sweat is just fat crying.",
            japanese = "汗はただ脂肪が泣いているだけだ。",
            romaji = "Ase wa tada shibō ga naite iru dake da."
        ),
        Quote(
            english = "Move fast. Break limits.",
            japanese = "速く動け。限界を破れ。",
            romaji = "Hayaku ugoke. Genkai o yabure."
        ),
        Quote(
            english = "Push past your plateau.",
            japanese = "停滞を突き破れ。",
            romaji = "Teitai o tsukiyabure."
        ),
        Quote(
            english = "Invest in your craft daily.",
            japanese = "毎日、自分の技術に投資せよ。",
            romaji = "Mainichi, jibun no gijutsu ni tōshi se yo."
        ),
        Quote(
            english = "Feed your focus, starve your distractions.",
            japanese = "集中力を育て、注意散漫を飢えさせよ。",
            romaji = "Shūchūryoku o sodatete, chūi sanman o ue saseyō."
        ),
        Quote(
            english = "Leave nothing on the table.",
            japanese = "やり残しなく、すべてを出し切れ。",
            romaji = "Yarinokoshi naku, subete o dashikitre."
        ),
        Quote(
            english = "Your biggest investment is yourself.",
            japanese = "あなたの最大の投資は、自分自身だ。",
            romaji = "Anata no saidai no tōshi wa, jibun jishin da."
        ),
        Quote(
            english = "Execute flawlessly.",
            japanese = "完璧に実行せよ。",
            romaji = "Kanpeki ni jikkō se yo."
        ),
        Quote(
            english = "Commit fully.",
            japanese = "完全にコミットせよ。",
            romaji = "Kanzen ni komitto se yo."
        ),
        Quote(
            english = "Be proud of how far you've come.",
            japanese = "ここまで来たことを誇れ。",
            romaji = "Koko made kita koto o hokore."
        ),
        Quote(
            english = "Your potential is a seed waiting to be watered.",
            japanese = "あなたの可能性は、水を待つ種だ。",
            romaji = "Anata no kanōsei wa, mizu o matsu tane da."
        ),
        Quote(
            english = "Be the lion, not the lamb.",
            japanese = "羊ではなく、ライオンになれ。",
            romaji = "Hitsuji de wa naku, raion ni nare."
        ),
        Quote(
            english = "Embrace the grind.",
            japanese = "努力を受け入れよ。",
            romaji = "Doryoku o ukeireyō."
        ),
        Quote(
            english = "Every rep counts.",
            japanese = "すべての反復が重要だ。",
            romaji = "Subete no hanpuku ga jūyō da."
        ),
        Quote(
            english = "You've come too far to give up now.",
            japanese = "ここまで来て、今さら諦めるな。",
            romaji = "Koko made kite, imasara akirameru na."
        ),
        Quote(
            english = "Dominate the day.",
            japanese = "一日を制せよ。",
            romaji = "Ichinichi o sei se yo."
        ),
        Quote(
            english = "Become what you need to be.",
            japanese = "なるべき自分になれ。",
            romaji = "Naru beki jibun ni nare."
        ),
        Quote(
            english = "Finish what you started.",
            japanese = "始めたことを終えよ。",
            romaji = "Hajimeta koto o oeyō."
        ),
        Quote(
            english = "You are your own greatest asset.",
            japanese = "あなたは自分自身の最大の資産だ。",
            romaji = "Anata wa jibun jishin no saidai no shisan da."
        ),
        Quote(
            english = "Sacrifice today for tomorrow's success.",
            japanese = "明日の成功のために、今日を犠牲にせよ。",
            romaji = "Ashita no seikō no tame ni, kyō o gisei ni se yo."
        ),
        Quote(
            english = "Your legacy is built one day at a time.",
            japanese = "あなたのレガシーは、一日一日積み上げられる。",
            romaji = "Anata no regashī wa, ichinichi ichinichi tsumia gerare ru."
        ),
        Quote(
            english = "Show the world what you're made of.",
            japanese = "あなたが何者であるかを世界に見せよ。",
            romaji = "Anata ga nanimono de aru ka o sekai ni miseyō."
        ),
        Quote(
            english = "Be disciplined enough to keep the promises you make to yourself.",
            japanese = "自分自身に約束したことを守るのに十分な規律を持て。",
            romaji = "Jibun jishin ni yakusoku shita koto o mamoru no ni jūbun na kiritsu o mote."
        ),
        Quote(
            english = "Bet on yourself.",
            japanese = "自分に賭けよ。",
            romaji = "Jibun ni kakeyō."
        ),
        Quote(
            english = "The struggle you're in today is developing the strength you need for tomorrow.",
            japanese = "今日のあなたの苦闘は、明日に必要な強さを育んでいる。",
            romaji = "Kyō no anata no kutō wa, ashita ni hitsuyō na tsuyosa o hagukunde iru."
        ),
        Quote(
            english = "Your worth is not determined by your productivity.",
            japanese = "あなたの価値は、生産性によって決まるのではない。",
            romaji = "Anata no kachi wa, seisansei ni yotte kimaru no de wa nai."
        ),
        Quote(
            english = "It's okay to rest.",
            japanese = "休んでも大丈夫だ。",
            romaji = "Yasunde mo daijōbu da."
        ),
        Quote(
            english = "Progress is healing.",
            japanese = "前進は癒しだ。",
            romaji = "Zenshin wa iyashi da."
        ),
        Quote(
            english = "You are resilient. Act like it.",
            japanese = "あなたは回復力がある。そのように行動せよ。",
            romaji = "Anata wa kaifukukyoku ga aru. Sono yō ni kōdō se yo."
        ),
        Quote(
            english = "One deep breath and begin.",
            japanese = "深呼吸を一度して、始めよ。",
            romaji = "Shinkokyū o ichido shite, hajimeyō."
        ),
        Quote(
            english = "Consistency beats perfection every time.",
            japanese = "一貫性は、常に完璧に勝る。",
            romaji = "Ikkansei wa, tsune ni kanpeki ni masaru."
        ),
        Quote(
            english = "You have everything you need inside you.",
            japanese = "あなたに必要なものはすべて、あなたの中にある。",
            romaji = "Anata ni hitsuyō na mono wa subete, anata no naka ni aru."
        ),
        Quote(
            english = "Don't just set goals. Build systems.",
            japanese = "目標を設定するだけでなく、システムを構築せよ。",
            romaji = "Mokuhyō o settei suru dake de naku, shisutemu o kōchiku se yo."
        ),
        Quote(
            english = "Morning sets the tone.",
            japanese = "朝がリズムを作る。",
            romaji = "Asa ga rizumu o tsukuru."
        ),
        Quote(
            english = "You deserve your own dedication.",
            japanese = "あなたは自分自身への献身に値する。",
            romaji = "Anata wa jibun jishin e no kenshin ni atai suru."
        ),
        Quote(
            english = "Big things start small.",
            japanese = "大きなものは、小さく始まる。",
            romaji = "Ōkina mono wa, chiisaku hajimaru."
        ),
        Quote(
            english = "Outwork everyone in silence.",
            japanese = "沈黙の中で誰よりも努力せよ。",
            romaji = "Chinmoku no naka de dare yori mo doryoku se yo."
        ),
        Quote(
            english = "Your mind is your most powerful tool.",
            japanese = "あなたの心は、最も強力なツールだ。",
            romaji = "Anata no kokoro wa, mottomo kyōryoku na tsūru da."
        ),
        Quote(
            english = "Patience and perseverance have a magical effect before which difficulties disappear.",
            japanese = "忍耐と粘り強さは、困難が消えていく魔法のような効果がある。",
            romaji = "Nintai to nebarizuyosa wa, konnan ga kiete iku mahō no yō na kōka ga aru."
        ),
        Quote(
            english = "Burn for something.",
            japanese = "何かのために燃えろ。",
            romaji = "Nanika no tame ni moero."
        ),
        Quote(
            english = "Seize the moment.",
            japanese = "瞬間をつかめ。",
            romaji = "Shunkan o tsukame."
        ),
        Quote(
            english = "Reach for what feels impossible.",
            japanese = "不可能に感じるものに手を伸ばせ。",
            romaji = "Fukanō ni kanjiru mono ni te o nobase."
        ),
        Quote(
            english = "Keep the faith.",
            japanese = "信念を持ち続けよ。",
            romaji = "Shinnen o mochi tsudukeyō."
        ),
        Quote(
            english = "Every small act of discipline compounds.",
            japanese = "すべての小さな規律の行為が積み重なる。",
            romaji = "Subete no chiisana kiritsu no kōi ga tsumikasanaru."
        ),
        Quote(
            english = "Be stubborn about your health.",
            japanese = "健康については頑固であれ。",
            romaji = "Kenkō ni tsuite wa ganko de are."
        ),
        Quote(
            english = "You are what you do, not what you say you'll do.",
            japanese = "あなたは言うことではなく、することそのものだ。",
            romaji = "Anata wa iu koto de wa naku, suru koto sono mono da."
        ),
        Quote(
            english = "Good enough is never good enough.",
            japanese = "まずまずは、決してまずまずではない。",
            romaji = "Mazumazu wa, kesshite mazumazu de wa nai."
        ),
        Quote(
            english = "Your story isn't over yet.",
            japanese = "あなたの物語はまだ終わっていない。",
            romaji = "Anata no monogatari wa mada owatte inai."
        ),
        Quote(
            english = "Adversity introduces a person to themselves.",
            japanese = "逆境は、人に自分自身を紹介する。",
            romaji = "Gyakkyō wa, hito ni jibun jishin o shōkai suru."
        ),
        Quote(
            english = "Become comfortable with discomfort.",
            japanese = "不快感に慣れよ。",
            romaji = "Fukaikan ni nareyo."
        ),
        Quote(
            english = "The best is yet to come.",
            japanese = "最高のことはまだこれからだ。",
            romaji = "Saikō no koto wa mada korekara da."
        ),
        Quote(
            english = "Act with intention, not impulse.",
            japanese = "衝動ではなく、意図を持って行動せよ。",
            romaji = "Shōdō de wa naku, ito o motte kōdō se yo."
        ),
        Quote(
            english = "Your discipline is a declaration of self-love.",
            japanese = "あなたの規律は、自己愛の宣言だ。",
            romaji = "Anata no kiritsu wa, jiko ai no sengen da."
        ),
        Quote(
            english = "Reclaim your power.",
            japanese = "自分のパワーを取り戻せ。",
            romaji = "Jibun no pawā o torimodo se."
        ),
        Quote(
            english = "Conquer yourself before you conquer the world.",
            japanese = "世界を征服する前に、自分自身を征服せよ。",
            romaji = "Sekai o seifuku suru mae ni, jibun jishin o seifuku se yo."
        ),
        Quote(
            english = "Create the life you can't wait to wake up to.",
            japanese = "目覚めるのが待ち切れないような人生を作れ。",
            romaji = "Mezameru no ga machikirenai yō na jinsei o tsukure."
        ),
        Quote(
            english = "Light yourself on fire with passion and people will come from miles around to watch you burn.",
            japanese = "情熱で自分自身を燃やせ。そうすれば人々がはるか遠くからあなたが燃えるのを見に来るだろう。",
            romaji = "Jōnetsu de jibun jishin o moyase. Sō sureba hitobito ga haruka tōku kara anata ga moeru no o mi ni kuru darō."
        ),
        Quote(
            english = "The journey of a thousand miles starts beneath your feet.",
            japanese = "千里の旅は、あなたの足元から始まる。",
            romaji = "Senri no tabi wa, anata no ashimoto kara hajimaru."
        ),
        Quote(
            english = "Choose your hard.",
            japanese = "自分の辛さを選べ。",
            romaji = "Jibun no tsurasa o erabe."
        ),
        Quote(
            english = "Health is wealth.",
            japanese = "健康は富だ。",
            romaji = "Kenkō wa tomi da."
        ),
        Quote(
            english = "Glow from within.",
            japanese = "内側から輝け。",
            romaji = "Uchigawa kara kagayake."
        ),
        Quote(
            english = "Be the master of your fate.",
            japanese = "あなたの運命の主人になれ。",
            romaji = "Anata no unmei no shujin ni nare."
        ),
        Quote(
            english = "Push a little further today.",
            japanese = "今日はもう少し前へ進め。",
            romaji = "Kyō wa mō sukoshi mae e susume."
        ),
        Quote(
            english = "Let your actions define you.",
            japanese = "行動があなたを定義するようにせよ。",
            romaji = "Kōdō ga anata o teigi suru yō ni se yo."
        ),
        Quote(
            english = "Trust the process, trust the timing.",
            japanese = "プロセスを信じ、タイミングを信じよ。",
            romaji = "Purosesu o shinji, taimingu o shinjiyō."
        ),
        Quote(
            english = "Be the game changer.",
            japanese = "ゲームチェンジャーになれ。",
            romaji = "Gēmu chenjā ni nare."
        ),
        Quote(
            english = "Grow through the discomfort.",
            japanese = "不快感の中で成長せよ。",
            romaji = "Fukaikan no naka de seichō se yo."
        ),
        Quote(
            english = "The harder the battle, the sweeter the victory.",
            japanese = "戦いが激しいほど、勝利は甘い。",
            romaji = "Tatakai ga hageshii hodo, shōri wa amai."
        ),
        Quote(
            english = "Finish strong.",
            japanese = "力強く終わらせよ。",
            romaji = "Chikara tsuyoku owaraseyō."
        ),
        Quote(
            english = "Bloom where you are planted.",
            japanese = "植えられた場所で咲け。",
            romaji = "Ueareta basho de sake."
        ),
        Quote(
            english = "Less talking, more doing.",
            japanese = "語ることを減らし、行動することを増やせ。",
            romaji = "Kataru koto o herashi, kōdō suru koto o fuyase."
        ),
        Quote(
            english = "Thrive, don't just survive.",
            japanese = "ただ生き延びるのではなく、繁栄せよ。",
            romaji = "Tada ikinobiru no de wa naku, han'ei se yo."
        ),
        Quote(
            english = "Harvest the power of small wins.",
            japanese = "小さな勝利のパワーを収穫せよ。",
            romaji = "Chiisana shōri no pawā o shūkaku se yo."
        ),
        Quote(
            english = "Outthink your own fear.",
            japanese = "自分の恐れを超える思考をせよ。",
            romaji = "Jibun no osore o koeru shikō o se yo."
        ),
        Quote(
            english = "Your mind is a powerful engine.",
            japanese = "あなたの心は強力なエンジンだ。",
            romaji = "Anata no kokoro wa kyōryoku na enjin da."
        ),
        Quote(
            english = "There is no try. There is only do.",
            japanese = "試みはない。あるのは実行のみだ。",
            romaji = "Kokoromi wa nai. Aru no wa jikkō nomi da."
        ),
        Quote(
            english = "The quality of your life is determined by the quality of your habits.",
            japanese = "あなたの人生の質は、あなたの習慣の質によって決まる。",
            romaji = "Anata no jinsei no shitsu wa, anata no shūkan no shitsu ni yotte kimaru."
        ),
        Quote(
            english = "Level up every single day.",
            japanese = "毎日レベルアップせよ。",
            romaji = "Mainichi reberu appu se yo."
        ),
        Quote(
            english = "Silence the doubt.",
            japanese = "疑いを黙らせよ。",
            romaji = "Utagai o damaraseyō."
        ),
        Quote(
            english = "Be willing to be a beginner every single morning.",
            japanese = "毎朝、初心者であろうとする意志を持て。",
            romaji = "Maiasa, shoshinsha de arō to suru ishi o mote."
        ),
        Quote(
            english = "Every day holds the possibility of a miracle.",
            japanese = "毎日に奇跡の可能性がある。",
            romaji = "Mainichi ni kiseki no kanōsei ga aru."
        ),
        Quote(
            english = "Commit to a vision and watch it manifest.",
            japanese = "ビジョンにコミットせよ。そしてそれが実現するのを見守れ。",
            romaji = "Bijon ni komitto se yo. Soshite sore ga jitsugen suru no o mimamoreyo."
        ),
        Quote(
            english = "Turn pain into power.",
            japanese = "痛みを力に変えよ。",
            romaji = "Itami o chikara ni kaeyo."
        ),
        Quote(
            english = "Rise above your story.",
            japanese = "あなたの物語を超えよ。",
            romaji = "Anata no monogatari o koe yo."
        ),
        Quote(
            english = "Don't shrink to fit in.",
            japanese = "馴染むために縮こまるな。",
            romaji = "Najimu tame ni chijiko maru na."
        ),
        Quote(
            english = "Your battles have made you stronger.",
            japanese = "あなたの戦いは、あなたをより強くした。",
            romaji = "Anata no tatakai wa, anata o yori tsuyoku shita."
        ),
        Quote(
            english = "The best gift you can give yourself is a disciplined life.",
            japanese = "自分に与えられる最高の贈り物は、規律ある人生だ。",
            romaji = "Jibun ni ataerareru saikō no okurimono wa, kiritsu aru jinsei da."
        ),
        Quote(
            english = "Doubt less. Believe more.",
            japanese = "疑いを減らせ。信じることを増やせ。",
            romaji = "Utagai o herase. Shinjiru koto o fuyase."
        ),
        Quote(
            english = "Build something you are proud of.",
            japanese = "誇れるものを作れ。",
            romaji = "Hokoreru mono o tsukure."
        ),
        Quote(
            english = "Consistency is key.",
            japanese = "一貫性が鍵だ。",
            romaji = "Ikkansei ga kagi da."
        ),
        Quote(
            english = "Unleash your inner champion.",
            japanese = "内なるチャンピオンを解き放て。",
            romaji = "Uchi naru chanpion o tokihanate."
        ),
        Quote(
            english = "I will not be outworked.",
            japanese = "私は誰よりも努力することをやめない。",
            romaji = "Watashi wa dare yori mo doryoku suru koto o yamenai."
        ),
        Quote(
            english = "Patience is power.",
            japanese = "忍耐は力だ。",
            romaji = "Nintai wa chikara da."
        ),
        Quote(
            english = "All things are difficult before they are easy.",
            japanese = "すべてのものは、簡単になる前は難しい。",
            romaji = "Subete no mono wa, kantan ni naru mae wa muzukashii."
        ),
        Quote(
            english = "The goal is progress, not perfection.",
            japanese = "目標は完璧ではなく、前進だ。",
            romaji = "Mokuhyō wa kanpeki de wa naku, zenshin da."
        ),
        Quote(
            english = "Value your time. It's your most precious resource.",
            japanese = "時間を大切にせよ。それはあなたの最も貴重なリソースだ。",
            romaji = "Jikan o taisetsu ni se yo. Sore wa anata no mottomo kichō na risōsu da."
        ),
        Quote(
            english = "Fuel your purpose.",
            japanese = "あなたの目的に燃料を注げ。",
            romaji = "Anata no mokuteki ni nenryō o tsoge."
        ),
        Quote(
            english = "Strong people don't put others down. They lift them up.",
            japanese = "強い人は他者をけなさない。持ち上げる。",
            romaji = "Tsuyoi hito wa taisha o kenasa nai. Mochiageru."
        ),
        Quote(
            english = "Real men cry when they work hard.",
            japanese = "本物の男は、懸命に働いて泣く。",
            romaji = "Honmono no otoko wa, kenmei ni hataruite naku."
        ),
        Quote(
            english = "Adapt to every challenge.",
            japanese = "すべての挑戦に適応せよ。",
            romaji = "Subete no chōsen ni tekiō se yo."
        ),
        Quote(
            english = "Act on your dreams.",
            japanese = "夢を行動に移せ。",
            romaji = "Yume o kōdō ni utsusei."
        ),
        Quote(
            english = "Yesterday you said tomorrow.",
            japanese = "昨日あなたは明日と言った。",
            romaji = "Kinō anata wa ashita to itta."
        ),
        Quote(
            english = "Measure success by how much you have grown.",
            japanese = "成功は、どれだけ成長したかで測れ。",
            romaji = "Seikō wa, dorehodo seichō shita ka de hakare."
        ),
        Quote(
            english = "One more rep. One more step. One more day.",
            japanese = "もう一回。もう一歩。もう一日。",
            romaji = "Mō ikkai. Mō ippo. Mō ichinichi."
        ),
        Quote(
            english = "Rise to your own standard.",
            japanese = "自分自身の基準に達せよ。",
            romaji = "Jibun jishin no kijun ni tasse yo."
        ),
        Quote(
            english = "Hard work compounds into extraordinary results.",
            japanese = "努力は積み重なり、並外れた結果になる。",
            romaji = "Doryoku wa tsumikasanari, namihazureta kekka ni naru."
        ),
        Quote(
            english = "Never leave until you have given everything.",
            japanese = "すべてを出し切るまで、立ち去るな。",
            romaji = "Subete o dashikiru made, tachi saru na."
        ),
        Quote(
            english = "Your mindset is your ultimate superpower.",
            japanese = "あなたのマインドセットは、究極のスーパーパワーだ。",
            romaji = "Anata no maindo setto wa, kyūkyoku no sūpā pawā da."
        ),
        Quote(
            english = "Create, not consume.",
            japanese = "消費するのではなく、創造せよ。",
            romaji = "Shōhi suru no de wa naku, sōzō se yo."
        ),
        Quote(
            english = "Be so focused that nothing can distract you.",
            japanese = "何も気を散らせないほど集中せよ。",
            romaji = "Nani mo ki o chirasanai hodo shūchū se yo."
        ),
        Quote(
            english = "Nothing extraordinary happens inside your comfort zone.",
            japanese = "コンフォートゾーンの中では、非凡なことは何も起きない。",
            romaji = "Konfo-to zōn no naka de wa, hibon na koto wa nani mo okinai."
        ),
        Quote(
            english = "Consistency is not a sprint; it's a marathon.",
            japanese = "一貫性はスプリントではなく、マラソンだ。",
            romaji = "Ikkansei wa supurinto de wa naku, marason da."
        ),
        Quote(
            english = "Hold the line.",
            japanese = "ラインを守れ。",
            romaji = "Rain o mamore."
        ),
        Quote(
            english = "Train your body, discipline your mind, nourish your soul.",
            japanese = "体を鍛え、心を律し、魂を養え。",
            romaji = "Karada o kitae, kokoro o tadashi, tamashii o yashinaeru."
        ),
        Quote(
            english = "Build your empire one brick at a time.",
            japanese = "一度に一つのレンガで、あなたの帝国を築け。",
            romaji = "Ichido ni hitotsu no renga de, anata no teikoku o kizuke."
        ),
        Quote(
            english = "Find a reason every single day.",
            japanese = "毎日理由を見つけよ。",
            romaji = "Mainichi riyū o mitsukeyō."
        ),
        Quote(
            english = "Let go of the old to make room for the new.",
            japanese = "古いものを手放し、新しいものが入るスペースを作れ。",
            romaji = "Furui mono o tebanashi, atarashii mono ga hairu supēsu o tsukure."
        ),
        Quote(
            english = "Forward is the only direction worth moving.",
            japanese = "前進だけが、動く価値のある方向だ。",
            romaji = "Zenshin dake ga, ugoku kachi no aru hōkō da."
        ),
        Quote(
            english = "Life is too short to waste on doubt.",
            japanese = "人生は疑いに無駄にするには短すぎる。",
            romaji = "Jinsei wa utagai ni muda ni suru ni wa mijikasugiru."
        ),
        Quote(
            english = "Your resilience is your greatest treasure.",
            japanese = "あなたの回復力は、最大の宝だ。",
            romaji = "Anata no kaifukukyoku wa, saidai no takara da."
        ),
        Quote(
            english = "You matter.",
            japanese = "あなたは重要な存在だ。",
            romaji = "Anata wa jūyō na sonzai da."
        ),
        Quote(
            english = "Don't give up on the things that make you come alive.",
            japanese = "あなたを生き生きとさせるものを諦めるな。",
            romaji = "Anata o ikiiki to saseru mono o akirameru na."
        ),
        Quote(
            english = "Your future self is watching.",
            japanese = "未来の自分が見ている。",
            romaji = "Mirai no jibun ga mite iru."
        ),
        Quote(
            english = "The only way to achieve the impossible is to believe it is possible.",
            japanese = "不可能を達成する唯一の方法は、それが可能だと信じることだ。",
            romaji = "Fukanō o tassei suru yuiitsu no hōhō wa, sore ga kanō da to shinjiru koto da."
        )
    )

    /** Get a random quote. */
    fun random(): Quote = all.random()

    /** Get a quote by cycling through the list based on an index (e.g. hour of day). */
    fun byIndex(index: Int): Quote = all[index % all.size]
}