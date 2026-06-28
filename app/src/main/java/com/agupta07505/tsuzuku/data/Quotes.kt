/*
 * Tsuzuku (2026)
 * ? Animesh Gupta ? github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

/**
 * Single source of truth for all motivational quotes used across the app.
 * Each quote has an English translation and Japanese text.
 */
data class Quote(
    val english: String,
    val japanese: String
)

object Quotes {
    val all: List<Quote> = listOf(
        Quote(
            english = "Genius is one percent inspiration and ninety-nine percent perspiration.",
            japanese = "天才とは、1 パーセントのインスピレーションと 99 パーセントの努力です。"
        ),
        Quote(
            english = "You can observe a lot just by watching.",
            japanese = "見ているだけでもたくさんのことを観察できます。"
        ),
        Quote(
            english = "A house divided against itself cannot stand.",
            japanese = "対立して分裂した家は存続できません。"
        ),
        Quote(
            english = "Difficulties increase the nearer we get to the goal.",
            japanese = "ゴールに近づくほど困難は増します。"
        ),
        Quote(
            english = "Fate is in your hands and no one elses",
            japanese = "運命はあなたの手の中にあり、他の誰でもない"
        ),
        Quote(
            english = "Be the chief but never the lord.",
            japanese = "首長であっても決して領主になってはなりません。"
        ),
        Quote(
            english = "Nothing happens unless first we dream.",
            japanese = "まず夢を見なければ何も起こりません。"
        ),
        Quote(
            english = "Well begun is half done.",
            japanese = "始まりは半分終わった。"
        ),
        Quote(
            english = "Life is a learning experience, only if you learn.",
            japanese = "人生は学習経験です、それはあなたが学んだ場合にのみです。"
        ),
        Quote(
            english = "Self-complacency is fatal to progress.",
            japanese = "自己満足は進歩にとって致命的です。"
        ),
        Quote(
            english = "Peace comes from within. Do not seek it without.",
            japanese = "平和は内側から生まれます。それなしにそれを求めないでください。"
        ),
        Quote(
            english = "What you give is what you get.",
            japanese = "あなたが与えるものはあなたが得るものです。"
        ),
        Quote(
            english = "We can only learn to love by loving.",
            japanese = "私たちは愛することによってのみ愛することを学ぶことができます。"
        ),
        Quote(
            english = "Life is change. Growth is optional. Choose wisely.",
            japanese = "人生は変化だ。成長はオプションです。賢明に選択してください。"
        ),
        Quote(
            english = "You'll see it when you believe it.",
            japanese = "信じれば見えてきます。"
        ),
        Quote(
            english = "Today is the tomorrow we worried about yesterday.",
            japanese = "今日は昨日心配した明日です。"
        ),
        Quote(
            english = "It's easier to see the mistakes on someone else's paper.",
            japanese = "他の人の論文の間違いを見つけるのは簡単です。"
        ),
        Quote(
            english = "Every man dies. Not every man really lives.",
            japanese = "すべての人は死ぬ。すべての人が本当に生きているわけではありません。"
        ),
        Quote(
            english = "To lead people walk behind them.",
            japanese = "人々を導くには、彼らの後ろを歩きます。"
        ),
        Quote(
            english = "Having nothing, nothing can he lose.",
            japanese = "何も持っていないので、彼は何も失うことはできません。"
        ),
        Quote(
            english = "Trouble is only opportunity in work clothes.",
            japanese = "作業服ではトラブルはチャンスに過ぎません。"
        ),
        Quote(
            english = "A rolling stone gathers no moss.",
            japanese = "転がる石には苔はつかない。"
        ),
        Quote(
            english = "Ideas are the beginning points of all fortunes.",
            japanese = "アイデアはすべての幸運の始まりです。"
        ),
        Quote(
            english = "Everything in life is luck.",
            japanese = "人生のすべては運です。"
        ),
        Quote(
            english = "Doing nothing is better than being busy doing nothing.",
            japanese = "何もしないで忙しいよりは、何もしないほうが良いのです。"
        ),
        Quote(
            english = "Trust yourself. You know more than you think you do.",
            japanese = "èªåãä¿¡é ¼ãããããªãã¯èªåãæã£ã¦ããããå¤ãã®ãã¨ãç¥ã£ã¦ããã"
        ),
        Quote(
            english = "Study the past, if you would divine the future.",
            japanese = "未来を占うなら、過去を研究してください。"
        ),
        Quote(
            english = "The day is already blessed, find peace within it.",
            japanese = "その日はすでに祝福されています。その中に平安を見つけましょう。"
        ),
        Quote(
            english = "From error to error one discovers the entire truth.",
            japanese = "誤りから誤りへと、人はすべての真実を発見します。"
        ),
        Quote(
            english = "Well done is better than well said.",
            japanese = "よくやったということは、よく言ったというよりも優れています。"
        ),
        Quote(
            english = "Bite off more than you can chew, then chew it.",
            japanese = "噛み切れないほど噛み切ってから噛みます。"
        ),
        Quote(
            english = "Work out your own salvation. Do not depend on others.",
            japanese = "自分自身の救いを見つけ出してください。他人に依存しないでください。"
        ),
        Quote(
            english = "One today is worth two tomorrows.",
            japanese = "今日の 1 つは明日の 2 つに相当します。"
        ),
        Quote(
            english = "Once you choose hope, anythings possible.",
            japanese = "希望を選択したら、何でも可能になります。"
        ),
        Quote(
            english = "God always takes the simplest way.",
            japanese = "神は常に最も単純な方法をとります。"
        ),
        Quote(
            english = "One fails forward toward success.",
            japanese = "人は成功に向かって失敗し続けます。"
        ),
        Quote(
            english = "From small beginnings come great things.",
            japanese = "小さな始まりから素晴らしいことが生まれます。"
        ),
        Quote(
            english = "Learning is a treasure that will follow its owner everywhere",
            japanese = "学びは宝物であり、持ち主のどこへでもついてきます"
        ),
        Quote(
            english = "Be as you wish to seem.",
            japanese = "あなたが望むように見えてください。"
        ),
        Quote(
            english = "The world is always in movement.",
            japanese = "世界は常に動いています。"
        ),
        Quote(
            english = "Never mistake activity for achievement.",
            japanese = "活動を達成と誤解しないでください。"
        ),
        Quote(
            english = "What worries you masters you.",
            japanese = "あなたが心配するものはあなたを支配します。"
        ),
        Quote(
            english = "One faces the future with ones past.",
            japanese = "人は過去とともに未来に直面します。"
        ),
        Quote(
            english = "Goals are the fuel in the furnace of achievement.",
            japanese = "目標は達成の炉の燃料です。"
        ),
        Quote(
            english = "Who sows virtue reaps honour.",
            japanese = "美徳を蒔く者は名誉を得る。"
        ),
        Quote(
            english = "Be kind whenever possible. It is always possible.",
            japanese = "可能な限り親切にしてください。それはいつでも可能です。"
        ),
        Quote(
            english = "Talk doesn't cook rice.",
            japanese = "話してもご飯は炊けない。"
        ),
        Quote(
            english = "He is able who thinks he is able.",
            japanese = "自分ができると思っている人はできる。"
        ),
        Quote(
            english = "A goal without a plan is just a wish.",
            japanese = "è¨ç»ã®ãªãç®æ¨ã¯ããã ã®é¡ãäºã ã"
        ),
        Quote(
            english = "To succeed, we must first believe that we can.",
            japanese = "成功するには、まずできると信じる必要があります。"
        ),
        Quote(
            english = "Learn from yesterday, live for today, hope for tomorrow.",
            japanese = "昨日から学び、今日に生き、明日に希望を持ちましょう。"
        ),
        Quote(
            english = "A weed is no more than a flower in disguise.",
            japanese = "雑草は花が姿を変えたものにすぎません。"
        ),
        Quote(
            english = "Do, or do not. There is no try.",
            japanese = "やるか、やらないか。試してみることはありません。"
        ),
        Quote(
            english = "All serious daring starts from within.",
            japanese = "すべての真剣な大胆さは内側から始まります。"
        ),
        Quote(
            english = "The best teacher is experience learned from failures.",
            japanese = "最高の教師は失敗から学んだ経験です。"
        ),
        Quote(
            english = "Think how hard physics would be if particles could think.",
            japanese = "粒子が考えることができたら、物理学がどれほど難しいか考えてみましょう。"
        ),
        Quote(
            english = "Love is the flower you've got to let grow.",
            japanese = "愛はあなたが育てなければならない花です。"
        ),
        Quote(
            english = "Don't wait. The time will never be just right.",
            japanese = "å¾ã¤ãªãæã¯ã¡ããã©ããã¿ã¤ãã³ã°ã«ã¯ãªããªãã"
        ),
        Quote(
            english = "Time is the wisest counsellor of all.",
            japanese = "時間は何よりも賢い相談相手です。"
        ),
        Quote(
            english = "You give before you get.",
            japanese = "得る前に与えるのです。"
        ),
        Quote(
            english = "Wisdom begins in wonder.",
            japanese = "知恵は驚きから始まります。"
        ),
        Quote(
            english = "Without courage, wisdom bears no fruit.",
            japanese = "勇気がなければ、知恵も実を結びません。"
        ),
        Quote(
            english = "Change in all things is sweet.",
            japanese = "すべての物事の変化は甘いものです。"
        ),
        Quote(
            english = "What you fear is that which requires action to overcome.",
            japanese = "あなたが恐れているのは、克服するために行動が必要なものです。"
        ),
        Quote(
            english = "When performance exceeds ambition, the overlap is called success.",
            japanese = "パフォーマンスが野心を上回るとき、その重なりを成功と呼びます。"
        ),
        Quote(
            english = "When deeds speak, words are nothing.",
            japanese = "行為が語るとき、言葉は何もありません。"
        ),
        Quote(
            english = "Real magic in relationships means an absence of judgement of others.",
            japanese = "人間関係における本当の魔法とは、他人を批判しないことを意味します。"
        ),
        Quote(
            english = "I never think of the future. It comes soon enough.",
            japanese = "将来のことは決して考えません。それはすぐに来ます。"
        ),
        Quote(
            english = "Skill to do comes of doing.",
            japanese = "行うスキルは行うことから生まれます。"
        ),
        Quote(
            english = "Wisdom is the supreme part of happiness.",
            japanese = "知恵は幸福の最高の部分です。"
        ),
        Quote(
            english = "I believe that every person is born with talent.",
            japanese = "私は、人は誰しも才能を持って生まれてくると信じています。"
        ),
        Quote(
            english = "Important principles may, and must, be inflexible.",
            japanese = "重要な原則は柔軟性に欠ける可能性があり、またそうしなければなりません。"
        ),
        Quote(
            english = "The undertaking of a new action brings new strength.",
            japanese = "新しい行動を起こすと、新たな力が生まれます。"
        ),
        Quote(
            english = "The years teach much which the days never know.",
            japanese = "年月は、日々では決して知ることのできない多くのことを教えてくれます。"
        ),
        Quote(
            english = "Our distrust is very expensive.",
            japanese = "私たちの不信感は非常に高価です。"
        ),
        Quote(
            english = "All know the way; few actually walk it.",
            japanese = "誰もが道を知っています。実際に歩いている人はほとんどいません。"
        ),
        Quote(
            english = "Great talent finds happiness in execution.",
            japanese = "優れた才能は、実行することに幸福を見出します。"
        ),
        Quote(
            english = "Faith in oneself is the best and safest course.",
            japanese = "自分自身を信じることが最善で最も安全な道です。"
        ),
        Quote(
            english = "Courage is going from failure to failure without losing enthusiasm.",
            japanese = "勇気とは、熱意を失わずに失敗を重ねていくことです。"
        ),
        Quote(
            english = "The two most powerful warriors are patience and time.",
            japanese = "最も強力な 2 つの戦士は忍耐と時間です。"
        ),
        Quote(
            english = "Anticipate the difficult by managing the easy.",
            japanese = "簡単なことを管理することで、困難なことを予測します。"
        ),
        Quote(
            english = "Those who are free of resentful thoughts surely find peace.",
            japanese = "恨みの念を持たない人は必ずや平安を得ることができます。"
        ),
        Quote(
            english = "A short saying often contains much wisdom.",
            japanese = "短い言葉には多くの知恵が含まれていることがよくあります。"
        ),
        Quote(
            english = "It takes both sunshine and rain to make a rainbow.",
            japanese = "虹ができるには、太陽の光と雨の両方が必要です。"
        ),
        Quote(
            english = "A beautiful thing is never perfect.",
            japanese = "美しいものは決して完璧ではありません。"
        ),
        Quote(
            english = "Only do what your heart tells you.",
            japanese = "自分の心が告げることだけをしてください。"
        ),
        Quote(
            english = "Life is movement-we breathe, we eat, we walk, we move!",
            japanese = "人生は動きです - 私たちは呼吸し、食べ、歩き、動きます。"
        ),
        Quote(
            english = "No one can make you feel inferior without your consent.",
            japanese = "あなたの同意なしに、誰もあなたに劣等感を抱かせることはできません。"
        ),
        Quote(
            english = "Argue for your limitations, and sure enough theyre yours.",
            japanese = "自分の限界について議論すれば、確かにそれはあなたのものです。"
        ),
        Quote(
            english = "Luck is what happens when preparation meets opportunity.",
            japanese = "éã¨ã¯ãæºåã¨æ©ä¼ãåºä¼ã£ãã¨ãã«èµ·ãããã®ã ã"
        ),
        Quote(
            english = "Victory belongs to the most persevering.",
            japanese = "勝利は最も忍耐強いものに属します。"
        ),
        Quote(
            english = "Love all, trust a few, do wrong to none.",
            japanese = "すべての人を愛し、少数の人を信頼し、誰も悪さをしません。"
        ),
        Quote(
            english = "In order to win, you must expect to win.",
            japanese = "勝つためには勝つことを期待しなければなりません。"
        ),
        Quote(
            english = "A goal is a dream with a deadline.",
            japanese = "目標とは期限のある夢です。"
        ),
        Quote(
            english = "You can do it if you believe you can!",
            japanese = "できると信じればできる！"
        ),
        Quote(
            english = "Set your goals high, and don't stop till you get there.",
            japanese = "é«ãç®æ¨ãè¨­å®ããéæããã¾ã§æ­¢ã¾ããªã"
        ),
        Quote(
            english = "Every new day is another chance to change your life.",
            japanese = "毎日があなたの人生を変えるチャンスです。"
        ),
        Quote(
            english = "Smile, breathe, and go slowly.",
            japanese = "笑って、呼吸して、ゆっくり進みましょう。"
        ),
        Quote(
            english = "Nobody will believe in you unless you believe in yourself.",
            japanese = "あなたが自分を信じない限り、誰もあなたを信じません。"
        ),
        Quote(
            english = "Do more than dream: work.",
            japanese = "夢を見るだけではなく、仕事をしましょう。"
        ),
        Quote(
            english = "No man was ever wise by chance.",
            japanese = "偶然に賢くなった人はいない。"
        ),
        Quote(
            english = "Some pursue happiness, others create it.",
            japanese = "幸福を追求する人もいれば、幸福を創造する人もいます。"
        ),
        Quote(
            english = "He that is giddy thinks the world turns round.",
            japanese = "めまいがする人は、世界が回転すると考えています。"
        ),
        Quote(
            english = "Don't ruin the present with the ruined past.",
            japanese = "台無しになった過去で現在を台無しにしないでください。"
        ),
        Quote(
            english = "Do something wonderful, people may imitate it.",
            japanese = "何か素晴らしいことをすれば、人々はそれを真似するかもしれません。"
        ),
        Quote(
            english = "We do what we do because we believe.",
            japanese = "私たちは信じているからやることをやります。"
        ),
        Quote(
            english = "Do one thing every day that scares you.",
            japanese = "あなたを怖がらせることを毎日一つ実行してください。"
        ),
        Quote(
            english = "If you cannot be silent be brilliant and thoughtful.",
            japanese = "黙っていられない場合は、聡明で思慮深くなりましょう。"
        ),
        Quote(
            english = "Who looks outside, dreams; who looks inside, awakes.",
            japanese = "外を見る者は夢を見る。中を見た人は目覚めます。"
        ),
        Quote(
            english = "What we think, we become.",
            japanese = "私たちが考えるものは、私たちになります。"
        ),
        Quote(
            english = "The shortest answer is doing.",
            japanese = "最短の答えは実行することです。"
        ),
        Quote(
            english = "All our knowledge has its origins in our perceptions.",
            japanese = "私たちのすべての知識は、私たちの認識に由来しています。"
        ),
        Quote(
            english = "The harder you fall, the higher you bounce.",
            japanese = "強く落ちれば落ちるほど、高く跳ね返ります。"
        ),
        Quote(
            english = "Trusting our intuition often saves us from disaster.",
            japanese = "自分の直感を信じることで、災害から身を守ることができます。"
        ),
        Quote(
            english = "Truth is powerful and it prevails.",
            japanese = "真実は強力であり、勝利します。"
        ),
        Quote(
            english = "Light tomorrow with today!",
            japanese = "今日で明日も明るく！"
        ),
        Quote(
            english = "Silence is a fence around wisdom.",
            japanese = "沈黙は知恵を囲う柵です。"
        ),
        Quote(
            english = "Society develops wit, but its contemplation alone forms genius.",
            japanese = "社会は機知を発達させますが、その熟考だけが天才を形成します。"
        ),
        Quote(
            english = "The simplest things are often the truest.",
            japanese = "最も単純なことは、多くの場合、最も真実です。"
        ),
        Quote(
            english = "Everyone smiles in the same language.",
            japanese = "誰もが同じ言語で微笑みます。"
        ),
        Quote(
            english = "Yesterday I dared to struggle. Today I dare to win.",
            japanese = "昨日は思い切って奮闘しました。今日はあえて勝ちます。"
        ),
        Quote(
            english = "No alibi will save you from accepting the responsibility.",
            japanese = "アリバイがなければ責任を負わなくても済みます。"
        ),
        Quote(
            english = "If you can dream it, you can do it.",
            japanese = "å¤¢è¦ããã¨ãã§ããã°ãå®ç¾ã§ããã"
        ),
        Quote(
            english = "It is better to travel well than to arrive.",
            japanese = "到着するよりもよく旅行する方が良いです。"
        ),
        Quote(
            english = "Life shrinks or expands in proportion to one's courage.",
            japanese = "人生は勇気に比例して縮んだり広がったりする。"
        ),
        Quote(
            english = "You have to believe in yourself.",
            japanese = "自分自身を信じなければなりません。"
        ),
        Quote(
            english = "Our intention creates our reality.",
            japanese = "私たちの意図が現実を創造します。"
        ),
        Quote(
            english = "Silence is a true friend who never betrays.",
            japanese = "沈黙は決して裏切らない真の友人です。"
        ),
        Quote(
            english = "Character develops itself in the stream of life.",
            japanese = "性格は人生の流れの中で発展していきます。"
        ),
        Quote(
            english = "From little acorns mighty oaks do grow.",
            japanese = "小さなドングリから大きな樫の木が育ちます。"
        ),
        Quote(
            english = "You can't stop the waves, but you can learn to surf.",
            japanese = "波を止めることはできませんが、サーフィンを学ぶことはできます。"
        ),
        Quote(
            english = "Reality does not conform to the ideal, but confirms it.",
            japanese = "現実は理想と一致しませんが、理想を裏付けます。"
        ),
        Quote(
            english = "Speak low, if you speak love.",
            japanese = "愛を語るなら、低い声で話してください。"
        ),
        Quote(
            english = "A really great talent finds its happiness in execution.",
            japanese = "本当に優れた才能は、実行することに喜びを感じます。"
        ),
        Quote(
            english = "Reality leaves a lot to the imagination.",
            japanese = "現実には想像力の余地がたくさんあります。"
        ),
        Quote(
            english = "The greatest remedy for anger is delay.",
            japanese = "怒りに対する最大の治療法は遅らせることです。"
        ),
        Quote(
            english = "Growth itself contains the germ of happiness.",
            japanese = "成長そのものには幸福の芽が含まれています。"
        ),
        Quote(
            english = "You can do what's reasonable or you can decide what's possible.",
            japanese = "合理的なことを行うことも、可能なことを決定することもできます。"
        ),
        Quote(
            english = "Nothing strengthens authority so much as silence.",
            japanese = "沈黙ほど権威を強化するものはありません。"
        ),
        Quote(
            english = "Wherever you go, go with all your heart.",
            japanese = "どこへ行くにも、心を込めて行きましょう。"
        ),
        Quote(
            english = "The only real valuable thing is intuition.",
            japanese = "本当に価値のあるものは直感だけです。"
        ),
        Quote(
            english = "Good luck is another name for tenacity of purpose.",
            japanese = "幸運とは、目的への粘り強さの別名です。"
        ),
        Quote(
            english = "Rainbows apologize for angry skies.",
            japanese = "虹は空の怒りを謝罪します。"
        ),
        Quote(
            english = "Friendship isn't a big thing. It's a million little things.",
            japanese = "友情なんて大したことじゃない。それは何百万もの小さなことです。"
        ),
        Quote(
            english = "Time is the most valuable thing a man can spend.",
            japanese = "時間は人間が費やすことができる最も貴重なものです。"
        ),
        Quote(
            english = "Whatever happens, take responsibility.",
            japanese = "何が起こっても、責任を持ってください。"
        ),
        Quote(
            english = "Experience is simply the name we give our mistakes.",
            japanese = "経験とは、私たちが自分の間違いに付けた名前にすぎません。"
        ),
        Quote(
            english = "I think and that is all that I am.",
            japanese = "私はそう思います、そしてそれが私のすべてです。"
        ),
        Quote(
            english = "A good plan today is better than a perfect plan tomorrow.",
            japanese = "今日の良い計画は、明日の完璧な計画よりも優れています。"
        ),
        Quote(
            english = "If the shoe doesn't fit, must we change the foot?",
            japanese = "靴が合わなかったら足を変えなければなりませんか？"
        ),
        Quote(
            english = "Each day provides its own gifts.",
            japanese = "毎日、独自のギフトが提供されます。"
        ),
        Quote(
            english = "While we stop to think, we often miss our opportunity.",
            japanese = "立ち止まって考えている間に、私たちはチャンスを逃してしまうことがよくあります。"
        ),
        Quote(
            english = "Life isn't about finding yourself. Life is about creating yourself.",
            japanese = "人生は自分自身を見つけることではありません。人生とは自分自身を創造することです。"
        ),
        Quote(
            english = "To bring anything into your life, imagine that it's already there.",
            japanese = "何かを自分の生活に取り入れるには、それがすでにそこにあると想像してください。"
        ),
        Quote(
            english = "Begin to weave and God will give you the thread.",
            japanese = "織り始めれば、神はあなたに糸を与えてくれるでしょう。"
        ),
        Quote(
            english = "The more you know yourself, the more you forgive yourself.",
            japanese = "自分を知れば知るほど、自分を許せるようになります。"
        ),
        Quote(
            english = "Someone remembers, someone cares; your name is whispered in someone's prayers.",
            japanese = "誰かが覚えていて、誰かが気にしている。あなたの名前は誰かの祈りの中でささやかれます。"
        ),
        Quote(
            english = "Without faith, nothing is possible. With it, nothing is impossible.",
            japanese = "信仰がなければ何もできません。それがあれば、不可能なことは何もありません。"
        ),
        Quote(
            english = "Once we accept our limits, we go beyond them.",
            japanese = "自分の限界を受け入れたら、それを超えていきます。"
        ),
        Quote(
            english = "Don't be pushed by your problems; be led by your dreams.",
            japanese = "自分の問題に押し流されないでください。あなたの夢に導かれてください。"
        ),
        Quote(
            english = "Whatever we expect with confidence becomes our own self-fulfilling prophecy.",
            japanese = "私たちが自信を持って期待するものはすべて、私たち自身の自己成就的予言になります。"
        ),
        Quote(
            english = "Everything you can imagine is real.",
            japanese = "あなたが想像できるものはすべて現実です。"
        ),
        Quote(
            english = "Fear is a darkroom where negatives develop.",
            japanese = "恐怖はネガティブな感情が展開される暗室です。"
        ),
        Quote(
            english = "The truest wisdom is a resolute determination.",
            japanese = "本当の知恵とは、断固たる決意です。"
        ),
        Quote(
            english = "Life is the flower for which love is the honey.",
            japanese = "人生は花であり、愛はその蜜です。"
        ),
        Quote(
            english = "Freedom is the right to live as we wish.",
            japanese = "自由とは、私たちが望むように生きる権利です。"
        ),
        Quote(
            english = "Change your thoughts, change your life!",
            japanese = "思考を変えて人生を変えましょう！"
        ),
        Quote(
            english = "Never ignore a gut feeling, but never believe that it's enough.",
            japanese = "直感を決して無視してはなりませんが、それで十分だとは決して考えないでください。"
        ),
        Quote(
            english = "Loss is nothing else but change,and change is Natures delight.",
            japanese = "喪失は変化にほかならず、変化は自然の喜びです。"
        ),
        Quote(
            english = "Someone is special only if you tell them.",
            japanese = "あなたが伝えた場合にのみ、その人は特別になります。"
        ),
        Quote(
            english = "Today is the tomorrow you worried about yesterday.",
            japanese = "今日はあなたが昨日心配した明日です。"
        ),
        Quote(
            english = "There is no way to happiness, happiness is the way.",
            japanese = "幸福への道はありません、幸福こそが道なのです。"
        ),
        Quote(
            english = "The day always looks brighter from behind a smile.",
            japanese = "笑顔の後ろでその日はいつも明るく見えます。"
        ),
        Quote(
            english = "A stumble may prevent a fall.",
            japanese = "つまずくことで転倒を防ぐことができます。"
        ),
        Quote(
            english = "He who talks more is sooner exhausted.",
            japanese = "たくさん話す人はすぐに疲れてしまいます。"
        ),
        Quote(
            english = "He who is contented is rich.",
            japanese = "満足している人は金持ちです。"
        ),
        Quote(
            english = "What we achieve inwardly will change outer reality.",
            japanese = "私たちが内側で達成したことは、外側の現実を変えるでしょう。"
        ),
        Quote(
            english = "Our strength grows out of our weaknesses.",
            japanese = "私たちの強さは弱さから生まれます。"
        ),
        Quote(
            english = "We must become the change we want to see.",
            japanese = "私たちは自分が見たい変化にならなければなりません。"
        ),
        Quote(
            english = "Happiness is found in doing, not merely possessing.",
            japanese = "幸福は、ただ所有するだけではなく、行うことによってもたらされます。"
        ),
        Quote(
            english = "Put your future in good hands : your own.",
            japanese = "自分の未来を自分自身の手に委ねましょう。"
        ),
        Quote(
            english = "We choose our destiny in the way we treat others.",
            japanese = "私たちは他者への接し方によって自分の運命を選択します。"
        ),
        Quote(
            english = "No snowflake in an avalanche ever feels responsible.",
            japanese = "雪崩に巻き込まれた雪片は決して責任を感じません。"
        ),
        Quote(
            english = "Fortune favours the brave.",
            japanese = "幸運は勇敢な者に味方します。"
        ),
        Quote(
            english = "I believe in one thing only, the power of human will.",
            japanese = "私が信じているのはただ一つ、人間の意志の力です。"
        ),
        Quote(
            english = "The best way out is always through.",
            japanese = "最善の解決策は常に通り抜けることだ。"
        ),
        Quote(
            english = "The mind unlearns with difficulty what it has long learned.",
            japanese = "心は長い間学んだことを忘れるのが困難です。"
        ),
        Quote(
            english = "I destroy my enemies when I make them my friends.",
            japanese = "敵を味方にするとき、私は敵を滅ぼします。"
        ),
        Quote(
            english = "No garden is without its weeds.",
            japanese = "雑草のない庭はありません。"
        ),
        Quote(
            english = "There is no failure except in no longer trying.",
            japanese = "もう挑戦しないこと以外に失敗はありません。"
        ),
        Quote(
            english = "Kind words will unlock an iron door.",
            japanese = "優しい言葉をかけると鉄の扉が開かれます。"
        ),
        Quote(
            english = "Problems are only opportunities with thorns on them.",
            japanese = "問題は、とげのある機会にすぎません。"
        ),
        Quote(
            english = "Life is just a chance to grow a soul.",
            japanese = "人生は魂を成長させるチャンスにすぎません。"
        ),
        Quote(
            english = "Mountains cannot be surmounted except by winding paths.",
            japanese = "曲がりくねった道を通らなければ山を越えることはできません。"
        ),
        Quote(
            english = "May our hearts garden of awakening bloom with hundreds of flowers.",
            japanese = "私たちの心の目覚めの庭が何百もの花で咲きますように。"
        ),
        Quote(
            english = "Fortune befriends the bold.",
            japanese = "幸運は大胆な人に味方します。"
        ),
        Quote(
            english = "Keep true to the dreams of thy youth.",
            japanese = "若い頃の夢に忠実であり続けてください。"
        ),
        Quote(
            english = "You're never a loser until you quit trying.",
            japanese = "挑戦することをやめない限り、あなたは決して敗者ではありません。"
        ),
        Quote(
            english = "Science is organized knowledge. Wisdom is organized life.",
            japanese = "科学は組織化された知識です。知恵とは組織化された生活です。"
        ),
        Quote(
            english = "Knowing is not enough; we must apply!",
            japanese = "知っているだけでは十分ではありません。私たちは申請しなければなりません！"
        ),
        Quote(
            english = "Strong beliefs win strong men, and then make them stronger.",
            japanese = "強い信念は強い人を勝ち取り、さらに強くします。"
        ),
        Quote(
            english = "Autumn is a second spring when every leaf is a flower.",
            japanese = "秋は、すべての葉が花になる第二の春です。"
        ),
        Quote(
            english = "If you surrender to the wind, you can ride it.",
            japanese = "風に身を任せれば乗れます。"
        ),
        Quote(
            english = "Keep yourself to the sunshine and you cannot see the shadow.",
            japanese = "太陽の光に当たっていれば、影は見えません。"
        ),
        Quote(
            english = "Write your plans in pencil and give God the eraser.",
            japanese = "計画を鉛筆で書いて、神様に消しゴムを渡してください。"
        ),
        Quote(
            english = "Inspiration exists, but it has to find us working.",
            japanese = "インスピレーションは存在しますが、それには私たちが取り組む必要があります。"
        ),
        Quote(
            english = "Pick battles big enough to matter, small enough to win.",
            japanese = "重要になるのに十分な規模の戦闘、または勝つのに十分な規模の戦闘を選択してください。"
        ),
        Quote(
            english = "Don't compromise yourself. You are all you've got.",
            japanese = "自分自身に妥協しないでください。あなたが持っているのはあなただけです。"
        ),
        Quote(
            english = "A short saying oft contains much wisdom.",
            japanese = "短い言葉には多くの知恵が含まれていることがよくあります。"
        ),
        Quote(
            english = "Difficulties are things that show a person what they are.",
            japanese = "困難は、その人が何であるかを示すものです。"
        ),
        Quote(
            english = "When you doubt your power, you give power to your doubt.",
            japanese = "自分の力を疑うとき、あなたはその疑いに力を与えます。"
        ),
        Quote(
            english = "The cause is hidden. The effect is visible to all.",
            japanese = "原因は隠されています。その効果は誰でも目に見えてわかります。"
        ),
        Quote(
            english = "A prudent question is one half of wisdom.",
            japanese = "賢明な質問は知恵の半分です。"
        ),
        Quote(
            english = "The path to success is to take massive, determined action.",
            japanese = "成功への道は、大規模で断固たる行動をとることです。"
        ),
        Quote(
            english = "I allow my intuition to lead my path.",
            japanese = "私は自分の直感を自分の道に導きます。"
        ),
        Quote(
            english = "Nature takes away any faculty that is not used.",
            japanese = "自然は、使われないあらゆる能力を奪い去ります。"
        ),
        Quote(
            english = "If you wish to be a writer, write.",
            japanese = "ライターになりたいなら、書いてください。"
        ),
        Quote(
            english = "There is no way to prosperity, prosperity is the way.",
            japanese = "繁栄への道はなく、繁栄こそが道なのです。"
        ),
        Quote(
            english = "Either you run the day or the day runs you.",
            japanese = "ããªããä¸æ¥ãæ¯éããããä¸æ¥ãããªããæ¯éãããã ã"
        ),
        Quote(
            english = "Better be ignorant of a matter than half know it.",
            japanese = "ある事柄について半分知っているよりは、知らないほうがいいです。"
        ),
        Quote(
            english = "Follow your instincts. That is where true wisdom manifests itself.",
            japanese = "自分の本能に従ってください。そこに真の知恵が現れます。"
        ),
        Quote(
            english = "There never was a good knife made of bad steel.",
            japanese = "悪い鋼で作られた良いナイフは決してありませんでした。"
        ),
        Quote(
            english = "To accomplish great things, we must dream as well as act.",
            japanese = "偉大なことを達成するには、行動するだけでなく夢も持たなければなりません。"
        ),
        Quote(
            english = "Patience is the companion of wisdom.",
            japanese = "忍耐は知恵の伴侶です。"
        ),
        Quote(
            english = "The mind is everything. What you think you become.",
            japanese = "心こそがすべてだ。自分が何になると思うか。"
        ),
        Quote(
            english = "To enjoy life, we must touch much of it lightly.",
            japanese = "人生を楽しむためには、人生の多くのことに軽く触れなければなりません。"
        ),
        Quote(
            english = "To fly, we have to have resistance.",
            japanese = "飛ぶためには抵抗が必要です。"
        ),
        Quote(
            english = "What you see depends on what you're looking for.",
            japanese = "何が表示されるかは、何を探しているかによって異なります。"
        ),
        Quote(
            english = "The heart has its reasons which reason knows not of.",
            japanese = "心には理性が知らない理由がある。"
        ),
        Quote(
            english = "Be great in act, as you have been in thought.",
            japanese = "考えたことと同じように、行動においても素晴らしくなりましょう。"
        ),
        Quote(
            english = "Imagination rules the world.",
            japanese = "想像力が世界を支配します。"
        ),
        Quote(
            english = "Kind words do not cost much. Yet they accomplish much.",
            japanese = "優しい言葉にはそれほどお金はかかりません。それでも、彼らは多くのことを成し遂げました。"
        ),
        Quote(
            english = "There is no greater harm than that of time wasted.",
            japanese = "時間を無駄にすることほど大きな害はありません。"
        ),
        Quote(
            english = "Intuition will tell the thinking mind where to look next.",
            japanese = "直観は思考力に次にどこを見るべきかを教えてくれます。"
        ),
        Quote(
            english = "Worry gives a small thing a big shadow.",
            japanese = "心配は小さなことに大きな影を与えます。"
        ),
        Quote(
            english = "Fears are nothing more than a state of mind.",
            japanese = "恐怖は心の状態にすぎません。"
        ),
        Quote(
            english = "The journey of a thousand miles begins with one step.",
            japanese = "千里の道も一歩から始まる。"
        ),
        Quote(
            english = "Efficiency is doing things right; effectiveness is doing the right things.",
            japanese = "効率とは物事を正しく行うことです。有効性とは正しいことを行うことです。"
        ),
        Quote(
            english = "Blaze with the fire that is never extinguished.",
            japanese = "決して消えることのない火で燃え上がります。"
        ),
        Quote(
            english = "Don't cry because it's over. Smile because it happened.",
            japanese = "もう終わったから泣かないで。それが起こったから笑ってください。"
        ),
        Quote(
            english = "No is easier to do. Yes is easier to say.",
            japanese = "いいえの方が簡単です。 「はい」のほうが言いやすいです。"
        ),
        Quote(
            english = "To be wrong is nothing unless you continue to remember it.",
            japanese = "間違っていたとしても、それを覚え続けなければ何も意味がありません。"
        ),
        Quote(
            english = "Yesterdays home runs don't win today's games.",
            japanese = "昨日のホームランが今日の試合に勝つわけではありません。"
        ),
        Quote(
            english = "Silence is deep as Eternity, Speech is shallow as Time.",
            japanese = "沈黙は永遠のように深く、スピーチは時間のように浅い。"
        ),
        Quote(
            english = "Don't smother each other. No one can grow in the shade.",
            japanese = "お互いを窒息させないでください。日陰では誰も成長できません。"
        ),
        Quote(
            english = "An ant on the move does more than a dozing ox",
            japanese = "動き回るアリは居眠りする牛以上のことをする"
        ),
        Quote(
            english = "You can't shake hands with a clenched fist.",
            japanese = "握りこぶしのままでは握手はできません。"
        ),
        Quote(
            english = "A good decision is based on knowledge and not on numbers.",
            japanese = "適切な決定は、数字ではなく知識に基づいて行われます。"
        ),
        Quote(
            english = "The cautious seldom err.",
            japanese = "用心深い人はめったに間違いを犯しません。"
        ),
        Quote(
            english = "If there is no struggle, there is no progress.",
            japanese = "闘争がなければ進歩はありません。"
        ),
        Quote(
            english = "Where there is great love, there are always miracles.",
            japanese = "偉大な愛があるところには、必ず奇跡が起こります。"
        ),
        Quote(
            english = "Time you enjoy wasting, was not wasted.",
            japanese = "無駄を楽しむ時間は、無駄ではありませんでした。"
        ),
        Quote(
            english = "Every problem has a gift for you in its hands.",
            japanese = "あらゆる問題はあなたへの贈り物を持っています。"
        ),
        Quote(
            english = "Sadness flies away on the wings of time.",
            japanese = "悲しみは時の翼に乗って飛び去ります。"
        ),
        Quote(
            english = "I have often regretted my speech, never my silence.",
            japanese = "私は自分のスピーチを後悔することはよくありましたが、沈黙したことは一度もありませんでした。"
        ),
        Quote(
            english = "Never put off till tomorrow what you can do today.",
            japanese = "今日できることを明日まで延期しないでください。"
        ),
        Quote(
            english = "Minds are like parachutes. They only function when open.",
            japanese = "心はパラシュートのようなものです。開いているときにのみ機能します。"
        ),
        Quote(
            english = "If a man does his best, what else is there?",
            japanese = "男が最善を尽くすなら、他に何があるでしょうか？"
        ),
        Quote(
            english = "The secret of success is constancy to purpose.",
            japanese = "成功の秘訣は目的を堅持することです。"
        ),
        Quote(
            english = "Life is a progress, and not a station.",
            japanese = "人生は進歩であり、終点ではありません。"
        ),
        Quote(
            english = "All seasons are beautiful for the person who carries happiness within.",
            japanese = "幸せを内に秘めた人にとって、どの季節も美しい。"
        ),
        Quote(
            english = "To avoid criticism, do nothing, say nothing, be nothing.",
            japanese = "批判を避けるには、何もせず、何も言わず、何もしないことです。"
        ),
        Quote(
            english = "All things change; nothing perishes.",
            japanese = "すべてのものは変化します。何も滅びません。"
        ),
        Quote(
            english = "Absence makes the heart grow fonder.",
            japanese = "不在は心をより愛おしくさせます。"
        ),
        Quote(
            english = "Imagination is the highest kite one can fly.",
            japanese = "想像力は人が揚げることのできる最高の凧です。"
        ),
        Quote(
            english = "The beginning of knowledge is the discovery of something we do not understand.",
            japanese = "知識の始まりは、理解できないものを発見することです。"
        ),
        Quote(
            english = "Love doesn't make the world go round, love is what makes the ride worthwhile.",
            japanese = "愛が世界を動かすのではなく、愛こそが旅を価値あるものにするのです。"
        ),
        Quote(
            english = "Good timber does not grow with ease; the stronger the wind, the stronger the trees.",
            japanese = "良い木材は簡単に育つものではありません。風が強ければ強いほど、木々も強くなります。"
        ),
        Quote(
            english = "I believe that we are fundamentally the same and have the same basic potential.",
            japanese = "私たちは根本的には同じであり、同じ基本的な可能性を持っていると信じています。"
        ),
        Quote(
            english = "The winds and waves are always on the side of the ablest navigators.",
            japanese = "風と波は常に最も有能なナビゲーターの味方です。"
        ),
        Quote(
            english = "The future belongs to those who believe in the beauty of their dreams.",
            japanese = "æªæ¥ã¯ãå¤¢ã®ç¾ãããä¿¡ããäººã®ãã®ã ã"
        ),
        Quote(
            english = "To get something you never had, you have to do something you never did.",
            japanese = "得たことのないものを手に入れるには、やったことのないことをしなければなりません。"
        ),
        Quote(
            english = "Be thankful when you don't know something for it gives you the opportunity to learn.",
            japanese = "何かを知らないときは、学ぶ機会を与えてくれるから感謝しましょう。"
        ),
        Quote(
            english = "Strength does not come from physical capacity. It comes from an indomitable will.",
            japanese = "強さは身体能力から生まれるものではありません。それは不屈の意志から生まれます。"
        ),
        Quote(
            english = "Each misfortune you encounter will carry in it the seed of tomorrows good luck.",
            japanese = "あなたが遭遇するあらゆる不幸は、その中に明日の幸運の種を運んでくるでしょう。"
        ),
        Quote(
            english = "To forgive is to set a prisoner free and realize that prisoner was you.",
            japanese = "許すということは、囚人を解放し、囚人は自分だったと気づくことです。"
        ),
        Quote(
            english = "In separateness lies the world's great misery, in compassion lies the world's true strength.",
            japanese = "孤立の中に世界の大きな悲惨があり、慈悲の中に世界の真の強さがある。"
        ),
        Quote(
            english = "By believing passionately in something that does not yet exist, we create it.",
            japanese = "まだ存在しないものを情熱的に信じることで、私たちはそれを創造します。"
        ),
        Quote(
            english = "Letting go isn't the end of the world; it's the beginning of a new life.",
            japanese = "手放すことは世界の終わりではありません。それは新しい人生の始まりです。"
        ),
        Quote(
            english = "All the great performers I have worked with are fuelled by a personal dream.",
            japanese = "私がこれまで一緒に仕事をしてきた偉大なパフォーマーたちは皆、個人的な夢を原動力にしています。"
        ),
        Quote(
            english = "One of the advantages of being disorderly is that one is constantly making exciting discoveries.",
            japanese = "秩序を乱すことの利点の 1 つは、常に刺激的な発見ができることです。"
        ),
        Quote(
            english = "I never see what has been done; I only see what remains to be done.",
            japanese = "何が行われたのかは決して分かりません。やるべきことが残っていることだけが見えています。"
        ),
        Quote(
            english = "Begin at once to live and count each separate day as a separate life.",
            japanese = "すぐに生き始めて、別々の日を別々の人生として数えてください。"
        ),
        Quote(
            english = "If you don't know where you are going, you will probably end up somewhere else.",
            japanese = "どこに行くのか分からない場合は、おそらく別の場所に行き着くでしょう。"
        ),
        Quote(
            english = "It is not so important to know everything as to appreciate what we learn.",
            japanese = "すべてを知ることはそれほど重要ではなく、学んだことを評価することも重要です。"
        ),
        Quote(
            english = "The bird of paradise alights only upon the hand that does not grasp.",
            japanese = "極楽鳥は握らない手にのみ舞い降りる。"
        ),
        Quote(
            english = "Think as a wise man but communicate in the language of the people.",
            japanese = "賢人として考えながらも、人々の言葉でコミュニケーションを取りましょう。"
        ),
        Quote(
            english = "Practice yourself, for heavens sake in little things, and then proceed to greater.",
            japanese = "小さなことから自分自身を練習し、それからより大きなことに進みましょう。"
        ),
        Quote(
            english = "If one does not know to which port is sailing, no wind is favorable.",
            japanese = "どの港に向かって航行しているのかが分からない場合、風は有利ではありません。"
        ),
        Quote(
            english = "Our greatest glory is not in never failing but rising everytime we fall.",
            japanese = "私たちの最大の栄光は、決して失敗しないことではなく、失敗するたびに立ち上がることです。"
        ),
        Quote(
            english = "Being right is highly overrated. Even a stopped clock is right twice a day.",
            japanese = "正しいことは非常に過大評価されています。止まった時計でも1日に2回は正確になります。"
        ),
        Quote(
            english = "To be upset over what you don't have is to waste what you do have.",
            japanese = "自分にないものに腹を立てることは、自分が持っているものを無駄にすることと同じです。"
        ),
        Quote(
            english = "If we did the things we are capable of, we would astound ourselves.",
            japanese = "もし私たちができることをやったら、私たちは自分自身を驚かせるでしょう。"
        ),
        Quote(
            english = "Nothing in life is to be feared. It is only to be understood.",
            japanese = "人生において恐れるべきものは何もありません。それは理解するしかありません。"
        ),
        Quote(
            english = "Successful people ask better questions, and as a result, they get better answers.",
            japanese = "成功した人はより良い質問をし、その結果、より良い答えが得られます。"
        ),
        Quote(
            english = "Love is not blind; it simply enables one to see things others fail to see.",
            japanese = "愛は盲目ではありません。それは単に他の人が見えないものを見ることを可能にするだけです。"
        ),
        Quote(
            english = "Life is a process. We are a process. The universe is a process.",
            japanese = "人生はプロセスです。私たちはプロセスです。宇宙はプロセスです。"
        ),
        Quote(
            english = "I think somehow we learn who we really are and then live with that decision.",
            japanese = "私たちは、どういうわけか自分が本当の人間であることを知り、その決断に従って生きているのだと思います。"
        ),
        Quote(
            english = "We learn what we have said from those who listen to our speaking.",
            japanese = "私たちは、自分の話を聞いている人から、自分の話した内容を学びます。"
        ),
        Quote(
            english = "A little knowledge that acts is worth infinitely more than much knowledge that is idle.",
            japanese = "行動に移す少しの知識は、無駄な多くの知識よりも無限に価値があります。"
        ),
        Quote(
            english = "If you get up one more time than you fall, you will make it through.",
            japanese = "転ぶよりも１回多く起き上がれば、乗り越えられる。"
        ),
        Quote(
            english = "The doors we open and close each day decide the lives we live.",
            japanese = "私たちが毎日開閉するドアは、私たちの人生を決定します。"
        ),
        Quote(
            english = "The worst bankrupt in the world is the person who has lost his enthusiasm.",
            japanese = "世界で最悪の破産者は、熱意を失った人です。"
        ),
        Quote(
            english = "Happiness comes when your work and words are of benefit to yourself and others.",
            japanese = "自分の仕事や言葉が自分自身や他人にとって有益であれば、幸福は訪れます。"
        ),
        Quote(
            english = "Don't focus on making the right decision, focus on making the decision the right one.",
            japanese = "正しい決断を下すことに集中するのではなく、正しい決断を下すことに集中してください。"
        ),
        Quote(
            english = "Everything is perfect in the universe, even your desire to improve it.",
            japanese = "宇宙ではすべてが完璧であり、それを改善したいというあなたの願望も含めてです。"
        ),
        Quote(
            english = "The universe is full of magical things, patiently waiting for our wits to grow sharper.",
            japanese = "宇宙は魔法に満ちており、私たちの知恵がより鋭くなるのを辛抱強く待っています。"
        ),
        Quote(
            english = "Just as a candle cannot burn without fire, men cannot live without a spiritual life.",
            japanese = "ろうそくが火なしでは燃えないのと同じように、人は霊的な生活なしでは生きていけません。"
        ),
        Quote(
            english = "A thing long expected takes the form of the unexpected when at last it comes.",
            japanese = "長い間期待されていたものは、ついに予期せぬ形で現れます。"
        ),
        Quote(
            english = "Action may not always bring happiness; but there is no happiness without action.",
            japanese = "行動は必ずしも幸福をもたらすとは限りません。しかし、行動がなければ幸福はありません。"
        ),
        Quote(
            english = "I don't believe in failure. It is not failure if you enjoyed the process.",
            japanese = "私は失敗を信じません。その過程を楽しめたなら、それは失敗ではありません。"
        ),
        Quote(
            english = "What you do not want done to yourself, do not do to others.",
            japanese = "自分にしてほしくないことは、他人にもしてはいけません。"
        ),
        Quote(
            english = "Short words are best and the old words when short are best of all.",
            japanese = "短い単語が最適であり、短い古い単語が何よりも最適です。"
        ),
        Quote(
            english = "If you light a lamp for somebody, it will also brighten your path.",
            japanese = "誰かのためにランプを灯せば、あなたの道も明るくなります。"
        ),
        Quote(
            english = "I have done my best: that is about all the philosophy of living one needs.",
            japanese = "私は最善を尽くしました。それが人が必要とする生きる哲学のすべてです。"
        ),
        Quote(
            english = "Through perseverance many people win success out of what seemed destined to be certain failure.",
            japanese = "多くの人は、失敗する運命にあると思われた状況から、粘り強さによって成功を勝ち取ります。"
        ),
        Quote(
            english = "Give thanks for the rain of life that propels us to reach new horizons.",
            japanese = "私たちを新たな地平に到達させる人生の雨に感謝しましょう。"
        ),
        Quote(
            english = "Love is just a word until someone comes along and gives it meaning.",
            japanese = "誰かが現れてそれに意味を与えるまでは、愛は単なる言葉にすぎません。"
        ),
        Quote(
            english = "We all have problems. The way we solve them is what makes us different.",
            japanese = "私たちは皆、問題を抱えています。それらを解決する方法が私たちを他と違うものにします。"
        ),
        Quote(
            english = "The secret to a rich life is to have more beginnings than endings.",
            japanese = "豊かな人生の秘訣は、終わりよりも始まりをたくさん持つことです。"
        ),
        Quote(
            english = "It is only when the mind and character slumber that the dress can be seen.",
            japanese = "心と性格が眠っているときにのみ、ドレスが見えてきます。"
        ),
        Quote(
            english = "If you don't like something, change it. If you can't change it, change your attitude.",
            japanese = "気に入らないものがあれば、変更してください。変えられないなら態度を変えましょう。"
        ),
        Quote(
            english = "Reviewing what you have learned and learning anew, you are fit to be a teacher.",
            japanese = "これまで学んだことを見直し、新たに学ぶなら、あなたは教師に適任です。"
        ),
        Quote(
            english = "The world is a book, and those who do not travel read only a page.",
            japanese = "世界は一冊の本であり、旅をしない人はたったの 1 ページを読むだけです。"
        ),
        Quote(
            english = "So long as a person is capable of self-renewal they are a living being.",
            japanese = "人は自己再生能力がある限り、生き物です。"
        ),
        Quote(
            english = "I'm not afraid of storms, for Im learning how to sail my ship.",
            japanese = "私は船の操縦方法を学んでいるので、嵐は怖くない。"
        ),
        Quote(
            english = "Think for yourselves and let others enjoy the privilege to do so too.",
            japanese = "自分自身で考えて、他の人にもそうする特権を享受してもらいましょう。"
        ),
        Quote(
            english = "How we spend our days is, of course, how we spend our lives.",
            japanese = "日々の過ごし方は、当然、人生の過ごし方でもあります。"
        ),
        Quote(
            english = "It has never been my object to record my dreams, just to realize them.",
            japanese = "私の目的は夢を記録することではなく、ただそれを実現することだけでした。"
        ),
        Quote(
            english = "The most complicated achievements of thought are possible without the assistance of consciousness.",
            japanese = "思考の最も複雑な成果は、意識の助けなしで可能です。"
        ),
        Quote(
            english = "Be miserable. Or motivate yourself. Whatever has to be done, it's always your choice.",
            japanese = "惨めになってください。あるいは自分自身を鼓舞することもできます。何をしなければならないとしても、それは常にあなたの選択です。"
        ),
        Quote(
            english = "Most great people have attained their greatest success just one step beyond their greatest failure.",
            japanese = "ほとんどの偉大な人々は、最大の失敗の一歩手前で最大の成功を収めています。"
        ),
        Quote(
            english = "If you think you can, you can. And if you think you can't, you're right.",
            japanese = "できると思えばできます。そして、それができないと思うなら、それは正しいです。"
        ),
        Quote(
            english = "Better to have loved and lost, than to have never loved at all.",
            japanese = "まったく愛さなかったよりは、愛して失ったほうがいい。"
        ),
        Quote(
            english = "Everyone thinks of changing the world, but no one thinks of changing himself.",
            japanese = "誰もが世界を変えようと考えますが、自分自身を変えようとは誰も考えません。"
        ),
        Quote(
            english = "The best way to pay for a lovely moment is to enjoy it.",
            japanese = "素敵な瞬間にお金を払う最善の方法は、それを楽しむことです。"
        ),
        Quote(
            english = "You have enemies? Good. That means you've stood up for something, sometime in your life.",
            japanese = "敵がいますか？良い。それはあなたが人生のどこかで何かのために立ち上がったことを意味します。"
        ),
        Quote(
            english = "Slow down and everything you are chasing will come around and catch you.",
            japanese = "速度を落とせば、あなたが追いかけているものはすべて近づいてきてあなたを捕まえます。"
        ),
        Quote(
            english = "Your worst enemy cannot harm you as much as your own unguarded thoughts.",
            japanese = "あなたの最悪の敵は、あなた自身の無防備な思考ほどあなたに害を及ぼすことはできません。"
        ),
        Quote(
            english = "I always wanted to be somebody, but I should have been more specific.",
            japanese = "私はいつも何者かになりたいと思っていましたが、もっと具体的にすべきでした。"
        ),
        Quote(
            english = "Yeah we all shine on, like the moon, and the stars, and the sun.",
            japanese = "そう、私たちは皆、月や星や太陽のように輝いています。"
        ),
        Quote(
            english = "Knowledge is a process of piling up facts; wisdom lies in their simplification.",
            japanese = "知識は事実を積み重ねるプロセスです。知恵はその単純化の中にあります。"
        ),
        Quote(
            english = "Life is like riding a bicycle. To keep your balance you must keep moving.",
            japanese = "人生は自転車に乗るようなものです。バランスを保つためには動き続けなければなりません。"
        ),
        Quote(
            english = "We should all be thankful for those people who rekindle the inner spirit.",
            japanese = "私たちは皆、内なる精神を再燃させてくれる人々に感謝すべきです。"
        ),
        Quote(
            english = "Opportunity is missed by most because it is dressed in overalls and looks like work.",
            japanese = "オーバーオールを着ていて仕事のように見えるため、ほとんどの人はチャンスを逃しています。"
        ),
        Quote(
            english = "Feeling and longing are the motive forces behind all human endeavor and human creations.",
            japanese = "感情と憧れは、人間のあらゆる努力と創造物の背後にある原動力です。"
        ),
        Quote(
            english = "In the end we retain from our studies only that which we practically apply.",
            japanese = "最終的には、実際に応用できるものだけが研究から得られます。"
        ),
        Quote(
            english = "If you correct your mind, the rest of your life will fall into place.",
            japanese = "心を正せば、残りの人生はうまくいくでしょう。"
        ),
        Quote(
            english = "The world makes way for the man who knows where he is going.",
            japanese = "世界は、自分がどこへ向かうのかを知っている男に道を譲ってくれます。"
        ),
        Quote(
            english = "When your desires are strong enough you will appear to possess superhuman powers to achieve.",
            japanese = "あなたの願望が十分に強いとき、あなたはそれを達成するための超人的な力を持っているように見えます。"
        ),
        Quote(
            english = "I cannot make my days longer so I strive to make them better.",
            japanese = "一日を長くすることはできないので、より良くするために努力しています。"
        ),
        Quote(
            english = "Tension is who you think you should be. Relaxation is who you are.",
            japanese = "ç·å¼µã¯ããªãããªãã¹ãäººããã¤ããã¯ããªãæ¬æ¥ã®å§¿ã ã"
        ),
        Quote(
            english = "Never bend your head. Always hold it high. Look the world right in the eye.",
            japanese = "決して頭を曲げないでください。常に高く持ってください。世界をまっすぐに見てください。"
        ),
        Quote(
            english = "We cannot do everything at once, but we can do something at once.",
            japanese = "一度にすべてを行うことはできませんが、一度に何かを行うことはできます。"
        ),
        Quote(
            english = "You have to do your own growing no matter how tall your grandfather was.",
            japanese = "祖父の身長がどれほど高くても、自分で成長しなければなりません。"
        ),
        Quote(
            english = "Invent your world. Surround yourself with people, color, sounds, and work that nourish you.",
            japanese = "自分の世界を発明しましょう。自分を養ってくれる人、色、音、仕事に囲まれてください。"
        ),
        Quote(
            english = "It is fatal to enter any war without the will to win it.",
            japanese = "勝つ意志なしに戦争に参加することは致命的です。"
        ),
        Quote(
            english = "Be what you are. This is the first step toward becoming better than you are.",
            japanese = "ありのままの自分でいてください。これは、自分よりも優れた人間になるための第一歩です。"
        ),
        Quote(
            english = "There is nothing in a caterpillar that tells you it's going to be a butterfly.",
            japanese = "毛虫には、蝶になることを告げるものは何もありません。"
        ),
        Quote(
            english = "Love and compassion open our own inner life, reducing stress, distrust and loneliness.",
            japanese = "愛と思いやりは私たち自身の内なる生活を開き、ストレス、不信感、孤独感を軽減します。"
        ),
        Quote(
            english = "Ideals are an imaginative understanding of that which is desirable in that which is possible.",
            japanese = "理想とは、可能なことの中で何が望ましいのかを想像的に理解したものです。"
        ),
        Quote(
            english = "The superior man is satisfied and composed; the mean man is always full of distress.",
            japanese = "優れた人は満足し、落ち着いています。意地悪な人はいつも苦しみでいっぱいです。"
        ),
        Quote(
            english = "If you spend too much time thinking about a thing, you'll never get it done.",
            japanese = "何かを考えるのにあまりにも多くの時間を費やすと、決してそれを成し遂げることはできません。"
        ),
        Quote(
            english = "The way is not in the sky. The way is in the heart.",
            japanese = "道は空にあるわけではありません。道は心の中にある。"
        ),
        Quote(
            english = "Most people are about as happy as they make up their minds to be",
            japanese = "ほとんどの人は、自分がそうしようと決意したのと同じくらい幸せです"
        ),
        Quote(
            english = "Three things cannot be long hidden: the sun, the moon, and the truth.",
            japanese = "太陽、月、そして真実という 3 つのものを長く隠すことはできません。"
        ),
        Quote(
            english = "More often than not, anger is actually an indication of weakness rather than of strength.",
            japanese = "多くの場合、怒りは実際には強さではなく弱さの表れです。"
        ),
        Quote(
            english = "Before you put on a frown, make absolutely sure there are no smiles available.",
            japanese = "しかめ面をする前に、笑顔がないことを絶対に確認してください。"
        ),
        Quote(
            english = "A man of ability and the desire to accomplish something can do anything.",
            japanese = "能力があり、何かを達成したいという願望がある人は、何でもできます。"
        ),
        Quote(
            english = "You, yourself, as much as anybody in the entire universe, deserve your love and affection.",
            japanese = "あなた自身も、全宇宙の他の人々と同じように、あなたの愛と愛情を受ける権利があります。"
        ),
        Quote(
            english = "It is not uncommon for people to spend their whole life waiting to start living.",
            japanese = "人生の始まりを待ち続ける人も珍しくありません。"
        ),
        Quote(
            english = "Don't be afraid to go out on a limb. That's where the fruit is.",
            japanese = "思い切って外に出ることを恐れないでください。そこが果物です。"
        ),
        Quote(
            english = "Wicked people are always surprised to find ability in those that are good.",
            japanese = "邪悪な人々は、善良な人々の中に能力を見出すことにいつも驚きます。"
        ),
        Quote(
            english = "Life is so constructed that an event does not, cannot, will not, match the expectation.",
            japanese = "人生は、出来事が期待と一致しない、一致しない、一致しないように作られています。"
        ),
        Quote(
            english = "If you change the way you look at things, the things you look at change.",
            japanese = "ものの見方が変われば、見えるものも変わります。"
        ),
        Quote(
            english = "No man can succeed in a line of endeavor which he does not like.",
            japanese = "人は、自分が好まない一連の努力で成功することはできません。"
        ),
        Quote(
            english = "You will not be punished for your anger, you will be punished by your anger.",
            japanese = "あなたは怒りによって罰されるのではなく、怒りによって罰されるのです。"
        ),
        Quote(
            english = "Don't judge each day by the harvest you reap but by the seeds you plant.",
            japanese = "毎日を刈り取った収穫によって判断するのではなく、自分が蒔いた種によって判断してください。"
        ),
        Quote(
            english = "They say that time changes things, but you actually have to change them yourself.",
            japanese = "時間が経てば物事は変わると言いますが、実際には自分で変える必要があります。"
        ),
        Quote(
            english = "Never apologize for showing feelings. When you do so, you apologize for the truth.",
            japanese = "感情を表したことを決して謝らないでください。そうするとき、あなたは真実について謝罪します。"
        ),
        Quote(
            english = "The truth you believe and cling to makes you unavailable to hear anything new.",
            japanese = "あなたが信じ、しがみついている真実により、新しいことを聞くことができなくなります。"
        ),
        Quote(
            english = "If you spend your whole life waiting for the storm, you'll never enjoy the sunshine.",
            japanese = "嵐を待つことに一生を費やすなら、太陽の光を楽しむことは決してできません。"
        ),
        Quote(
            english = "The only limit to our realization of tomorrow will be our doubts of today.",
            japanese = "私たちが明日を実現するための唯一の制限は、今日の私たちの疑念だけです。"
        ),
        Quote(
            english = "Every action of our lives touches on some chord that will vibrate in eternity.",
            japanese = "私たちの人生のあらゆる行為は、永遠に振動する何らかのコードに触れています。"
        ),
        Quote(
            english = "Shoot for the moon. Even if you miss, you'll land among the stars.",
            japanese = "月に向かって撃て。たとえ失敗しても、星々の間に着陸します。"
        ),
        Quote(
            english = "It does not matter how slowly you go as long as you do not stop.",
            japanese = "æ­¢ã¾ããªãéããã©ããªã«ãã£ããé²ãã§ãæ§ããªãã"
        ),
        Quote(
            english = "Every day may not be good, but there's something good in every day.",
            japanese = "毎日が良いことはないかもしれないが、毎日には何か良いことがある。"
        ),
        Quote(
            english = "Most folks are about as happy as they make up their minds to be.",
            japanese = "ほとんどの人は、自分で決めたとおりに幸せです。"
        ),
        Quote(
            english = "If you would take, you must first give, this is the beginning of intelligence.",
            japanese = "取るなら、まず与えなければなりません、これが知性の始まりです。"
        ),
        Quote(
            english = "Some people think it's holding that makes one strong, sometimes it's letting go.",
            japanese = "抱き続けることが人を強くすると考える人もいますが、時には手放すこともあるのです。"
        ),
        Quote(
            english = "It is on our failures that we base a new and different and better success.",
            japanese = "私たちは失敗の上に、新たな、これまでとは異なる、より良い成功を築くのです。"
        ),
        Quote(
            english = "Quality is never an accident; it is always the result of intelligent effort.",
            japanese = "品質は決して偶然ではありません。それは常に知的な努力の結果です。"
        ),
        Quote(
            english = "To study and not think is a waste. To think and not study is dangerous.",
            japanese = "勉強しても考えないのは無駄です。考えて勉強しないのは危険です。"
        ),
        Quote(
            english = "Life is a succession of lessons, which must be lived to be understood.",
            japanese = "人生は教訓の連続であり、それを理解するには生きなければなりません。"
        ),
        Quote(
            english = "Time changes everything except something within us which is always surprised by change.",
            japanese = "時間はすべてを変えますが、私たちの中の何かは常に変化に驚かされます。"
        ),
        Quote(
            english = "You are important enough to ask and you are blessed enough to receive back.",
            japanese = "あなたは尋ねるほど重要であり、答えてもらえるほど恵まれています。"
        ),
        Quote(
            english = "If you cannot do great things, do small things in a great way.",
            japanese = "素晴らしいことができないなら、小さなことを素晴らしい方法でやりましょう。"
        ),
        Quote(
            english = "If you want your life to be more rewarding, you have to change the way you think.",
            japanese = "人生をもっと価値のあるものにしたいなら、考え方を変える必要があります。"
        ),
        Quote(
            english = "The free man is he who does not fear to go to the end of his thought.",
            japanese = "自由な人とは、自分の思考の終わりに向かうことを恐れない人のことです。"
        ),
        Quote(
            english = "Don't leave a stone unturned. It's always something, to know you have done the most you could.",
            japanese = "一石を投じないでください。自分ができる限りのことをやったと知ることは、常に大切なことです。"
        ),
        Quote(
            english = "What lies behind us and what lies before us are tiny matters compared to what lies within us.",
            japanese = "私たちの後ろにあるものも、私たちの前にあるものも、私たちの内側にあるものに比べれば小さな問題です。"
        ),
        Quote(
            english = "There is no retirement for an artist, it's your way of living so there is no end to it.",
            japanese = "アーティストに引退はありません。それはあなたの生き方なので、終わりはありません。"
        ),
        Quote(
            english = "When you are content to be simply yourself and don't compare or compete, everybody will respect you.",
            japanese = "あなたがただ自分自身でいることに満足し、比較したり競争したりしないとき、誰もがあなたを尊敬するでしょう。"
        ),
        Quote(
            english = "Do you want to know who you are? Don't ask. Act! Action will delineate and define you.",
            japanese = "自分が誰なのか知りたいですか？尋ねないでください。活動！行動があなたを描写し、定義します。"
        ),
        Quote(
            english = "It is only with the heart that one can see rightly, what is essential is invisible to the eye.",
            japanese = "人は心によってのみ正しく見ることができ、本質的なものは目には見えません。"
        ),
        Quote(
            english = "Make the best use of what is in your power, and take the rest as it happens.",
            japanese = "自分にできることを最大限に活用し、残りは必要に応じて取りましょう。"
        ),
        Quote(
            english = "The thoughts we choose to think are the tools we use to paint the canvas of our lives.",
            japanese = "私たちが考えることを選択した思考は、私たちの人生のキャンバスを描くために使用するツールです。"
        ),
        Quote(
            english = "The reason most goals are not achieved is that we spend our time doing second things first.",
            japanese = "ほとんどの目標が達成できない理由は、私たちが最初に二番目のことに時間を費やしているからです。"
        ),
        Quote(
            english = "If your actions inspire others to dream more, learn more, do more and become more, you are a leader.",
            japanese = "あなたの行動が他の人たちに、もっと夢を持ち、もっと学び、もっと行動し、もっと成長するよう促すなら、あなたはリーダーです。"
        ),
        Quote(
            english = "I'm a great believer in luck and I find the harder I work, the more I have of it.",
            japanese = "私は幸運を強く信じており、努力すればするほど幸運が得られると感じています。"
        ),
        Quote(
            english = "Do not waste yourself in rejection, nor bark against the bad, but chant the beauty of the good.",
            japanese = "拒絶して無駄にせず、悪に対して吠えず、善の美しさを唱えてください。"
        ),
        Quote(
            english = "The person born with a talent they are meant to use will find their greatest happiness in using it.",
            japanese = "使うべき才能を持って生まれた人は、それを使うことで最大の幸福を感じるでしょう。"
        ),
        Quote(
            english = "Your destiny isn't just fate; it is how you use your own developed abilities to get what you want.",
            japanese = "あなたの運命は単なる運命ではありません。それは、あなたが望むものを手に入れるために、あなた自身の発達した能力をどのように活用するかです。"
        ),
        Quote(
            english = "You got to be careful if you don't know where you're going, because you might not get there.",
            japanese = "行き先が分からない場合は、到着できない可能性があるので注意が必要です。"
        ),
        Quote(
            english = "You can't let praise or criticism get to you. It's a weakness to get caught up in either one.",
            japanese = "賞賛や批判を受け取ることはできません。どちらかに囚われてしまうのが弱点です。"
        ),
        Quote(
            english = "Our doubts are traitors and make us lose the good we often might win, by fearing to attempt.",
            japanese = "私たちの疑いは裏切り者であり、挑戦することを恐れることによって、私たちが獲得できるかもしれない利益を失うことになります。"
        ),
        Quote(
            english = "The minute you settle for less than you deserve, you get even less than you settled for.",
            japanese = "あなたが値するものよりも低い金額で妥協した瞬間に、あなたはあなたが納得したよりもさらに少ないものを得るでしょう。"
        ),
        Quote(
            english = "There is only one success, to be able to spend your life in your own way.",
            japanese = "成功はただ一つ、自分らしく人生を過ごせること。"
        ),
        Quote(
            english = "There is only one corner of the universe you can be certain of improving, and that's your own self.",
            japanese = "確実に改善できるのは宇宙の一角だけ、それはあなた自身です。"
        ),
        Quote(
            english = "You're not obligated to win. You're obligated to keep trying to do the best you can every day.",
            japanese = "勝つ義務はありません。あなたには毎日最善を尽くし続ける義務があります。"
        ),
        Quote(
            english = "You may be deceived if you trust too much, but you will live in torment if you don't trust enough.",
            japanese = "信頼しすぎると騙されるかもしれませんが、十分に信頼しないと苦しみの中で生きることになります。"
        ),
        Quote(
            english = "Choose a job you love, and you will never have to work a day in your life.",
            japanese = "好きな仕事を選べば、一生働かなくても済むようになります。"
        ),
        Quote(
            english = "You cannot find yourself by going into the past. You can find yourself by coming into the present.",
            japanese = "過去に行っても自分を見つけることはできません。現在に入ることで自分自身を見つけることができます。"
        ),
        Quote(
            english = "In order to live free and happily you must sacrifice boredom. It is not always an easy sacrifice.",
            japanese = "自由で幸せに生きるためには、退屈を犠牲にしなければなりません。それは必ずしも簡単な犠牲ではありません。"
        ),
        Quote(
            english = "The fox has many tricks. The hedgehog has but one. But that is the best of all.",
            japanese = "キツネにはたくさんのトリックがあります。ハリネズミには一匹しかいません。しかし、それが何よりも良いのです。"
        ),
        Quote(
            english = "Let me tell you the secret that has led me to my goal: my strength lies solely in my tenacity",
            japanese = "私を目標に導いた秘密を教えましょう：私の強さはただ粘り強さだけです"
        ),
        Quote(
            english = "We must never forget that it is through our actions, words, and thoughts that we have a choice.",
            japanese = "私たちは自分の行動、言葉、思考を通して選択をすることができるということを決して忘れてはなりません。"
        ),
        Quote(
            english = "We see things not as they are, but as we are. Our perception is shaped by our previous experiences.",
            japanese = "私たちは物事をありのままに見るのではなく、ありのままに見ます。私たちの認識は、これまでの経験によって形作られています。"
        ),
        Quote(
            english = "As the rest of the world is walking out the door, your best friends are the ones walking in.",
            japanese = "世界の他の人々がドアから出ていく中、あなたの親友が入ってくるのです。"
        ),
        Quote(
            english = "A wise man can learn more from a foolish question than a fool can learn from a wise answer.",
            japanese = "賢い人は、愚か者が賢明な答えから学ぶよりも、愚かな質問からより多くのことを学ぶことができます。"
        ),
        Quote(
            english = "Every man takes the limits of his own field of vision for the limits of the world.",
            japanese = "人は誰しも、自分の視野の限界を世界の限界とみなします。"
        ),
        Quote(
            english = "One does not discover new lands without consenting to lose sight of the shore for a very long time.",
            japanese = "非常に長い間海岸を見失わない限り、人は新しい土地を発見することはできません。"
        ),
        Quote(
            english = "Life is like a sewer. What you get out of it depends on what you put into it.",
            japanese = "人生は下水道のようなものです。そこから何が得られるかは、そこに何を投入したかによって決まります。"
        ),
        Quote(
            english = "Learn all you can from the mistakes of others. You won't have time to make them all yourself.",
            japanese = "他人の間違いからできる限りのことを学びましょう。全部自分で作る時間はありません。"
        ),
        Quote(
            english = "It is the greatest of all mistakes to do nothing because you can only do little, do what you can.",
            japanese = "できることは少ししかできないからといって何もしないのは、あらゆる間違いの中で最大の間違いです。できる限りのことをしてください。"
        ),
        Quote(
            english = "Those who try to do something and fail are infinitely better than those who try nothing and succeed.",
            japanese = "何かをしようとして失敗する人は、何もしようとせずに成功する人よりもはるかに優れています。"
        ),
        Quote(
            english = "The first step to getting the things you want out of life is this: decide what you want.",
            japanese = "人生で欲しいものを手に入れるための最初のステップは、何が欲しいかを決めることです。"
        ),
        Quote(
            english = "Experience is not what happens to a man. It is what a man does with what happens to him.",
            japanese = "経験は人間に起こるものではありません。それは人が自分に起こったことに対して何をするかです。"
        ),
        Quote(
            english = "A good teacher is like a candle, it consumes itself to light the way for others.",
            japanese = "優れた教師はろうそくのようなもので、自分自身を消耗して他の人たちの道を照らすのです。"
        ),
        Quote(
            english = "The only thing to do with good advice is to pass it on. It is never of any use to oneself.",
            japanese = "良いアドバイスを得るためにできる唯一のことは、それを伝えることです。それは自分自身にとって決して役に立ちません。"
        ),
        Quote(
            english = "Life is not measured by the breaths we take, but by the moments that take our breath.",
            japanese = "人生は呼吸によって測られるのではなく、呼吸する瞬間によって測られます。"
        ),
        Quote(
            english = "What lies behind us and what lies before us are small matters compared to what lies within us.",
            japanese = "私たちの後ろにあるものも、私たちの前にあるものも、私たちの中にあるものに比べれば些細なことです。"
        ),
        Quote(
            english = "It is the mark of an educated mind to be able to entertain a thought without accepting it.",
            japanese = "ある考えを受け入れずに楽しむことができるのは、教育を受けた心の証です。"
        ),
        Quote(
            english = "Love is never lost. If not reciprocated, it will flow back and soften and purify the heart.",
            japanese = "愛は決して失われることはありません。返されなければ逆流し、心を和らげ浄化します。"
        ),
        Quote(
            english = "We all live with the objective of being happy; our lives are all different and yet the same.",
            japanese = "私たちは皆、幸せになるという目的を持って生きています。私たちの人生はすべて異なりますが、同じです。"
        ),
        Quote(
            english = "Every time you smile at someone, it is an action of love, a gift to that person, a beautiful thing.",
            japanese = "あなたが誰かに微笑むたび、それは愛の行為であり、その人への贈り物であり、美しいことです。"
        ),
        Quote(
            english = "If you must tell me your opinions, tell me what you believe in. I have plenty of douts of my own.",
            japanese = "あなたの意見を言わなければならない場合は、あなたが何を信じているか教えてください。私には自分のドークがたくさんあります。"
        ),
        Quote(
            english = "We never understand how little we need in this world until we know the loss of it.",
            japanese = "私たちは、それが失われることを知るまで、この世界に必要なものがどれほど少ないかを決して理解することはできません。"
        ),
        Quote(
            english = "The real measure of your wealth is how much youd be worth if you lost all your money.",
            japanese = "あなたの富の本当の尺度は、すべてのお金を失った場合にどれだけの価値があるかです。"
        ),
        Quote(
            english = "Take no thought of who is right or wrong or who is better than. Be not for or against.",
            japanese = "誰が正しいか間違っているか、誰がより優れているかなどは考えないでください。賛成でも反対でもありません。"
        ),
        Quote(
            english = "I am a man of fixed and unbending principles, the first of which is to be flexible at all times.",
            japanese = "私は確固たる信念を持つ人間であり、その第一は常に柔軟であることです。"
        ),
        Quote(
            english = "Today, give a stranger a smile without waiting for it may be the joy they need to have a great day.",
            japanese = "今日、見知らぬ人に待たずに笑顔を与えることは、素晴らしい一日を過ごすために必要な喜びかもしれません。"
        ),
        Quote(
            english = "At the center of your being you have the answer; you know who you are and you know what you want.",
            japanese = "あなたという存在の中心に答えがあります。あなたは自分が誰であるかを知っており、自分が何を望んでいるのかを知っています。"
        ),
        Quote(
            english = "How wonderful that we have met with a paradox. Now we have some hope of making progress.",
            japanese = "私たちがパラドックスに遭遇したのはなんと素晴らしいことでしょう。今、私たちは進歩する希望を持っています。"
        ),
        Quote(
            english = "Everyone is a genius at least once a year. A real genius has his original ideas closer together.",
            japanese = "誰もが少なくとも年に一度は天才になります。本物の天才は、オリジナルのアイデアをより密接に結びつけています。"
        ),
        Quote(
            english = "Sadness may be part of life but there is no need to let it dominate your entire life.",
            japanese = "悲しみは人生の一部かもしれませんが、人生全体を悲しみに支配される必要はありません。"
        ),
        Quote(
            english = "Keeping a little ahead of conditions is one of the secrets of business, the trailer seldom goes far.",
            japanese = "状況を少しでも先取りすることがビジネスの秘訣の 1 つであり、トレーラーが遠くまで進むことはめったにありません。"
        ),
        Quote(
            english = "Nature gave us one tongue and two ears so we could hear twice as much as we speak.",
            japanese = "自然は私たちに 1 つの舌と 2 つの耳を与えてくれたので、話す量の 2 倍の量を聞くことができます。"
        ),
        Quote(
            english = "You are always free to change your mind and choose a different future, or a different past.",
            japanese = "あなたはいつでも自由に考えを変え、別の未来や別の過去を選択することができます。"
        ),
        Quote(
            english = "You were not born a winner, and you were not born a loser. You are what you make yourself be.",
            japanese = "あなたは勝者として生まれたわけでも、敗者として生まれたわけでもありません。あなたはあなた自身を作るものです。"
        ),
        Quote(
            english = "To be what we are, and to become what we are capable of becoming, is the only end of life.",
            japanese = "私たちがありのままであること、そして私たちがなれる可能性があるものになること、それが人生の唯一の目的です。"
        ),
        Quote(
            english = "Take things as they are. Punch when you have to punch. Kick when you have to kick.",
            japanese = "物事をありのままに受け入れてください。パンチする必要があるときはパンチします。蹴るべき時は蹴る。"
        ),
        Quote(
            english = "Mind is everything: muscle, pieces of rubber. All that I am, I am because of my mind.",
            japanese = "心はすべてです：筋肉、ゴム片。私が存在するすべては、私の心のおかげです。"
        ),
        Quote(
            english = "How wonderful it is that nobody need wait a single moment before starting to improve the world.",
            japanese = "世界を改善し始める前に誰も少しも待つ必要がないのは、なんと素晴らしいことでしょう。"
        ),
        Quote(
            english = "It is one of the blessings of old friends that you can afford to be stupid with them.",
            japanese = "一緒にバカなことをする余裕があるのは、古い友人の祝福の 1 つです。"
        ),
        Quote(
            english = "Give me six hours to chop down a tree and I will spend the first four sharpening the axe.",
            japanese = "木を切り倒すのに 6 時間を与えてください。そうすれば、最初の 4 時間は斧を研ぐのに費やします。"
        ),
        Quote(
            english = "One must be fond of people and trust them if one is not to make a mess of life.",
            japanese = "人生を台無しにしないためには、人を愛し、信頼しなければなりません。"
        ),
        Quote(
            english = "We cannot change our memories, but we can change their meaning and the power they have over us.",
            japanese = "私たちは記憶を変えることはできませんが、その意味や記憶が私たちに及ぼす力を変えることはできます。"
        ),
        Quote(
            english = "To give hope to someone occurs when you teach them how to use the tools to do it for themselves.",
            japanese = "誰かに希望を与えるということは、自分自身でそれを行うためのツールの使い方を教えるときに起こります。"
        ),
        Quote(
            english = "Id rather regret the things that I have done than the things that I have not done.",
            japanese = "やらなかったことよりも、やったことを後悔したいと思います。"
        ),
        Quote(
            english = "If the stars should appear but one night every thousand years how man would marvel and adore.",
            japanese = "もし星が千年に一夜だけ現れるとしたら、人はどれほど驚嘆し、崇拝するだろう。"
        ),
        Quote(
            english = "I'm not interested in age. People who tell me their age are silly. You're as old as you feel.",
            japanese = "年齢には興味がない。自分の年齢を言う人は愚かです。あなたは自分が感じているのと同じくらいの年齢です。"
        ),
        Quote(
            english = "I find hope in the darkest of days, and focus in the brightest. I do not judge the universe.",
            japanese = "私は最も暗い日々の中でも希望を見出し、最も明るい日に集中します。私は宇宙を批判しません。"
        ),
        Quote(
            english = "Blessed is the person who is too busy to worry in the daytime, and too sleepy to worry at night.",
            japanese = "日中は忙しすぎて心配することができず、夜は眠くて心配できない人は幸いです。"
        ),
        Quote(
            english = "He can who thinks he can, and he can't who thinks he can't. This is an inexorable, indisputable law.",
            japanese = "できると思う人はできるし、できないと思う人はできない。これは容赦のない、議論の余地のない法律です。"
        ),
        Quote(
            english = "These days people seek knowledge, not wisdom. Knowledge is of the past, wisdom is of the future.",
            japanese = "最近、人々は知恵ではなく知識を求めています。知識は過去のものであり、知恵は未来のものです。"
        ),
        Quote(
            english = "One secret of success in life is for a man to be ready for his opportunity when it comes.",
            japanese = "人生で成功する秘訣の一つは、チャンスが来たときにその準備ができていることです。"
        ),
        Quote(
            english = "The shoe that fits one person pinches another; there is no recipe for living that suits all cases.",
            japanese = "ある人にぴったりの靴は別の人に当てはまります。すべてのケースに適した生き方のレシピはありません。"
        ),
        Quote(
            english = "Very little is needed to make a happy life; it is all within yourself, in your way of thinking.",
            japanese = "幸せな人生を送るために必要なものはほとんどありません。それはすべて自分自身の中にあり、あなたの考え方にあります。"
        ),
        Quote(
            english = "Giving up doesn't always mean you are weak. Sometimes it means that you are strong enough to let go.",
            japanese = "諦めることは必ずしもあなたが弱いことを意味するわけではありません。場合によっては、それはあなたが手放すのに十分な強さを持っていることを意味します。"
        ),
        Quote(
            english = "If you focus on results, you will never change. If you focus on change, you will get results.",
            japanese = "結果ばかりを重視していては、決して変わることはありません。変化に焦点を当てれば、結果は得られます。"
        ),
        Quote(
            english = "You can be what you want to be. You have the power within and we will help you always.",
            japanese = "あなたはなりたいものになれるのです。あなたには内なる力があり、私たちは常にあなたを助けます。"
        ),
        Quote(
            english = "Wisdom is the reward you get for a lifetime of listening when you'd have preferred to talk.",
            japanese = "知恵は、話したかったときに、一生聞き続けることで得られる報酬です。"
        ),
        Quote(
            english = "When one tugs at a single thing in nature, he finds it attached to the rest of the world.",
            japanese = "人が自然界の単一のものを引っ張ると、それが世界の他の部分と結びついていることに気づきます。"
        ),
        Quote(
            english = "To live a pure unselfish life, one must count nothing as ones own in the midst of abundance.",
            japanese = "純粋で利己的な人生を送るためには、豊かさのただ中で何も自分のものとして数えてはなりません。"
        ),
        Quote(
            english = "When we seek to discover the best in others, we somehow bring out the best in ourselves.",
            japanese = "他人の最高のものを見つけようとすると、私たちはどういうわけか自分自身の最高のものを引き出します。"
        ),
        Quote(
            english = "I am not bothered by the fact that I am unknown. I am bothered when I do not know others.",
            japanese = "私は無名であるという事実を気にしません。他人のことを知らないと不安になります。"
        ),
        Quote(
            english = "I am always doing that which I cannot do, in order that I may learn how to do it.",
            japanese = "私はいつも、自分にできないことを、やり方を学ぶためにやっているのです。"
        ),
        Quote(
            english = "The world is round and the place which may seem like the end may also be the beginning.",
            japanese = "世界は丸い、終わりに見える場所は始まりかもしれない。"
        ),
        Quote(
            english = "Give it all you've got because you never know if there's going to be a next time.",
            japanese = "次にあるかどうかは分からないので、全力を尽くしてください。"
        ),
        Quote(
            english = "You have to take it as it happens, but you should try to make it happen the way you want to take it.",
            japanese = "起こったことをそのまま受け止める必要がありますが、自分が受け止めたいようにそれが起こるように努めるべきです。"
        ),
        Quote(
            english = "I can't imagine a person becoming a success who doesn't give this game of life everything hes got.",
            japanese = "この人生ゲームに全力を注がない人が成功するとは思えません。"
        ),
        Quote(
            english = "The greatest way to live with honor in this world is to be what we pretend to be.",
            japanese = "この世界で名誉を持って生きるための最大の方法は、ありのままのふりをすることです。"
        ),
        Quote(
            english = "Do not go where the path may lead, go instead where there is no path and leave a trail.",
            japanese = "道が通じそうなところには行かず、道のないところに行き、道を残してください。"
        ),
        Quote(
            english = "You can only grow if you're willing to feel awkward and uncomfortable when you try something new.",
            japanese = "何か新しいことに挑戦するときに、ぎこちなく不快に感じることをいとわない場合にのみ、成長することができます。"
        ),
        Quote(
            english = "The thing always happens that you really believe in; and the belief in a thing makes it happen.",
            japanese = "あなたが本当に信じていることは常に起こります。そして、何かを信じることがそれを実現させます。"
        ),
        Quote(
            english = "Let your hook always be cast; in the pool where you least expect it, there will be a fish.",
            japanese = "フックは常にキャストしてください。思いがけないプールに魚がいるかもしれません。"
        ),
        Quote(
            english = "If you love someone, set them free. If they come back they're yours; if they don't they never were.",
            japanese = "誰かを愛しているなら、その人を自由にしてあげてください。もし彼らが戻ってきたら、それはあなたのものです。そうでなければ、彼らは決してそうではありませんでした。"
        ),
        Quote(
            english = "Wisdom is knowing what to do next; Skill is knowing how to do it, and Virtue is doing it.",
            japanese = "知恵とは、次に何をすべきかを知ることです。スキルとはそれを行う方法を知っていることであり、美徳とはそれを実行することです。"
        ),
        Quote(
            english = "No valid plans for the future can be made by those who have no capacity for living now.",
            japanese = "今を生きる能力のない人には、将来に対する有効な計画を立てることはできません。"
        ),
        Quote(
            english = "To accomplish great things, we must not only act, but also dream; not only plan, but also believe.",
            japanese = "偉大なことを達成するには、行動するだけでなく、夢を持たなければなりません。計画するだけでなく、信じます。"
        ),
        Quote(
            english = "If we could learn to like ourselves, even a little, maybe our cruelties and angers might melt away.",
            japanese = "少しでも自分を好きになれたら、残酷さや怒りも溶けるかもしれません。"
        ),
        Quote(
            english = "If we are facing in the right direction, all we have to do is keep on walking.",
            japanese = "正しい方向を向いているなら、私たちは歩き続けるだけでいいのです。"
        ),
        Quote(
            english = "We could never learn to be brave and patient if there were only joy in the world.",
            japanese = "世界に喜びだけがあれば、私たちは勇気と忍耐力を学ぶことはできません。"
        ),
        Quote(
            english = "If it is not right do not do it; if it is not true do not say it.",
            japanese = "それが正しくない場合は、それを行わないでください。それが真実でないなら、それを言わないでください。"
        ),
        Quote(
            english = "The truth of the matter is that you always know the right thing to do. The hard part is doing it.",
            japanese = "問題の真実は、あなたは常に正しいことを知っているということです。難しいのはそれを実行することです。"
        ),
        Quote(
            english = "If you are patient in one moment of anger, you will escape one hundred days of sorrow.",
            japanese = "一瞬の怒りを我慢すれば、百日間の悲しみから逃れることができる。"
        ),
        Quote(
            english = "The poor man is not he who is without a cent, but he who is without a dream.",
            japanese = "貧しい人とは、一円も持たない人ではなく、夢がない人です。"
        ),
        Quote(
            english = "Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment.",
            japanese = "過去に留まらず、未来を夢見ず、今この瞬間に心を集中してください。"
        ),
        Quote(
            english = "Peace of mind is not the absence of conflict from life, but the ability to cope with it.",
            japanese = "心の平安とは、人生から葛藤がないことではなく、それに対処する能力です。"
        ),
        Quote(
            english = "You have power over your mind, not outside events. Realize this, and you will find strength.",
            japanese = "あなたは外側の出来事ではなく、自分の心を支配する力を持っています。これに気づくと、力が湧いてきます。"
        ),
        Quote(
            english = "Let me tell you the secret that has led me to my goal: my strength lies solely in my tenacity.",
            japanese = "私を目標に導いた秘密をお話しましょう。私の強さはただ粘り強さだけです。"
        ),
        Quote(
            english = "He that respects himself is safe from others; he wears a coat of mail that none can pierce.",
            japanese = "自分自身を尊重する人は他人から安全です。彼は誰も貫通できない鎧のコートを着ています。"
        ),
        Quote(
            english = "I cannot always control what goes on outside. But I can always control what goes on inside.",
            japanese = "外で何が起こっているかを常にコントロールできるわけではありません。しかし、私はいつでも自分の中で何が起こっているかをコントロールすることができます。"
        ),
        Quote(
            english = "Take time to deliberate, but when the time for action has arrived, stop thinking and go in.",
            japanese = "時間をかけて熟考しますが、行動の時が来たら、考えるのをやめて行動に移してください。"
        ),
        Quote(
            english = "Try not to become a man of success, but rather try to become a man of value.",
            japanese = "成功者になろうとするのではなく、価値のある人間になろうと努めなさい。"
        ),
        Quote(
            english = "It is not enough to have a good mind; the main thing is to use it well.",
            japanese = "良い心を持っているだけでは十分ではありません。主なことはそれをうまく使うことです。"
        ),
        Quote(
            english = "Never do things others can do and will do, if there are things others cannot do or will not do.",
            japanese = "他の人ができないこと、またはやらないことがある場合、他の人ができること、そして行うであろうことを決してしないでください。"
        ),
        Quote(
            english = "I can't change the direction of the wind, but I can adjust my sails to always reach my destination.",
            japanese = "風向きを変えることはできませんが、いつでも目的地に到達できるように帆を調整することはできます。"
        ),
        Quote(
            english = "A fine quotation is a diamond on the finger of a man of wit, and a pebble in the hand of a fool.",
            japanese = "優れた名言とは、機知に富んだ人の指にあるダイヤモンドであり、愚か者の手にある小石のようなものです。"
        ),
        Quote(
            english = "The greatest way to live with honour in this world is to be what we pretend to be.",
            japanese = "この世界で名誉を持って生きるための最大の方法は、ありのままのふりをすることです。"
        ),
        Quote(
            english = "To exist is to change, to change is to mature, to mature is to go on creating oneself endlessly.",
            japanese = "存在することは変化することであり、変化することは成熟することであり、成熟することは無限に自分を創造し続けることです。"
        ),
        Quote(
            english = "Try not to become a man of success but rather try to become a man of value.",
            japanese = "成功者になるのではなく、価値のある人になるよう努めてください。"
        ),
        Quote(
            english = "You can't create in a vacuum. Life gives you the material and dreams can propel new beginnings.",
            japanese = "真空状態では創造することはできません。人生はあなたに材料を与え、夢は新しい始まりを推進します。"
        ),
        Quote(
            english = "Your work is to discover your world and then with all your heart give yourself to it.",
            japanese = "あなたの仕事は、自分の世界を発見し、心からその世界に身を捧げることです。"
        ),
        Quote(
            english = "Work while you have the light. You are responsible for the talent that has been entrusted to you.",
            japanese = "明るいうちに作業しましょう。あなたには、自分に託された才能に対して責任があります。"
        ),
        Quote(
            english = "How far that little candle throws its beams! So shines a good deed in a naughty world.",
            japanese = "あの小さなろうそくはなんと遠くまで光を飛ばすのでしょう！いたずらな世界で善行を輝かせます。"
        ),
        Quote(
            english = "It is in your moments of decision that your destiny is shaped.",
            japanese = "あなたの運命が形作られるのは、あなたの決断の瞬間です。"
        ),
        Quote(
            english = "An obstacle may be either a stepping stone or a stumbling block.",
            japanese = "障害物は、踏み台である場合もあれば、つまずきの石である場合もあります。"
        ),
        Quote(
            english = "The pain passes, but the beauty remains.",
            japanese = "痛みは消えますが、美しさは残ります。"
        ),
        Quote(
            english = "All I can say about life is, Oh God, enjoy it!",
            japanese = "人生について私が言えるのは、ああ、神様、楽しんでください！ということだけです。"
        ),
        Quote(
            english = "Creativity comes from trust. Trust your instincts. And never hope more than you work.",
            japanese = "創造性は信頼から生まれます。自分の直感を信じてください。そして、働くこと以上の期待を決してしないでください。"
        ),
        Quote(
            english = "Your outlook on life is a direct reflection on how much you like yourself.",
            japanese = "あなたの人生観は、あなたがどれだけ自分を好きかに直接反映されます。"
        ),
        Quote(
            english = "You won't skid if you stay in a rut.",
            japanese = "マンネリに留まると滑ることはありません。"
        ),
        Quote(
            english = "You block your dream when you allow your fear to grow bigger than your faith.",
            japanese = "恐怖が信仰よりも大きくなるのを許すと、夢が妨げられてしまいます。"
        ),
        Quote(
            english = "Happiness depends upon ourselves.",
            japanese = "幸福は私たち自身にかかっているのです。"
        ),
        Quote(
            english = "Wherever a man turns he can find someone who needs him.",
            japanese = "人はどこに行っても、自分を必要としてくれる人を見つけることができます。"
        ),
        Quote(
            english = "If one is lucky, a solitary fantasy can totally transform one million realities.",
            japanese = "運が良ければ、孤独な空想が百万の現実を完全に変えることができる。"
        ),
        Quote(
            english = "Never idealize others. They will never live up to your expectations.",
            japanese = "決して他人を理想化してはいけません。彼らは決してあなたの期待に応えることはできません。"
        ),
        Quote(
            english = "When you realize there is nothing lacking, the whole world belongs to you.",
            japanese = "何も欠けていないことに気づいたとき、世界全体があなたのものになります。"
        ),
        Quote(
            english = "Happiness is not something ready made. It comes from your own actions.",
            japanese = "幸福は既成のものではありません。それはあなた自身の行動から生まれます。"
        ),
        Quote(
            english = "Meaning is not what you start with but what you end up with.",
            japanese = "意味とは、何を始めるかではなく、何を終えるかです。"
        ),
        Quote(
            english = "No one has ever become poor by giving.",
            japanese = "寄付によって貧しくなった人は一人もいません。"
        ),
        Quote(
            english = "Be faithful in small things because it is in them that your strength lies.",
            japanese = "あなたの強さは小さなことにこそあるので、小さなことに忠実でありなさい。"
        ),
        Quote(
            english = "All is flux; nothing stays still.",
            japanese = "すべては流動です。何も静止しません。"
        ),
        Quote(
            english = "He who is fixed to a star does not change his mind.",
            japanese = "星に執着している人は考えを変えません。"
        ),
        Quote(
            english = "He who lives in harmony with himself lives in harmony with the universe.",
            japanese = "自分自身と調和して生きる人は、宇宙と調和して生きます。"
        ),
        Quote(
            english = "Ignorant men don't know what good they hold in their hands until they've flung it away.",
            japanese = "無知な人は、それを投げ飛ばすまで、自分が手に持っているものがどんな良いものであるか知りません。"
        ),
        Quote(
            english = "When the solution is simple, God is answering.",
            japanese = "解決策が簡単なとき、神は答えてくれます。"
        ),
        Quote(
            english = "All achievements, all earned riches, have their beginning in an idea.",
            japanese = "すべての成果、すべての獲得した富は、アイデアから始まります。"
        ),
        Quote(
            english = "Do not turn back when you are just at the goal.",
            japanese = "ゴール直前で後戻りしないでください。"
        ),
        Quote(
            english = "You can't trust without risk but neither can you live in a cocoon.",
            japanese = "リスクなしに信頼することはできませんが、繭の中で生きることもできません。"
        ),
        Quote(
            english = "All perceiving is also thinking, all reasoning is also intuition, all observation is also invention.",
            japanese = "すべての知覚は思考でもあり、すべての推論も直観であり、すべての観察も発明です。"
        ),
        Quote(
            english = "Error is discipline through which we advance.",
            japanese = "間違いは私たちが前進するための規律です。"
        ),
        Quote(
            english = "The truth is always exciting. Speak it, then. Life is dull without it.",
            japanese = "真実は常に刺激的です。それなら話してください。それなしでは人生は退屈です。"
        ),
        Quote(
            english = "The superior man is modest in his speech, but exceeds in his actions.",
            japanese = "優れた人は、言葉では謙虚ですが、行動では優れています。"
        ),
        Quote(
            english = "The longer we dwell on our misfortunes, the greater is their power to harm us.",
            japanese = "私たちが自分の不幸について長く考えれば考えるほど、私たちに害を及ぼす不幸の力は大きくなります。"
        ),
        Quote(
            english = "Those who will play with cats must expect to be scratched.",
            japanese = "猫と遊ぶ人は引っ掻かれることを覚悟しなければなりません。"
        ),
        Quote(
            english = "I've never seen a smiling face that was not beautiful.",
            japanese = "美しくない笑顔を見たことがありません。"
        ),
        Quote(
            english = "In all things of nature there is something of the marvellous.",
            japanese = "自然界のあらゆるものの中には、驚くべきものが存在します。"
        ),
        Quote(
            english = "The universe is transformation; our life is what our thoughts make it.",
            japanese = "宇宙は変容しています。私たちの人生は私たちの思考によって作られます。"
        ),
        Quote(
            english = "Memory is the mother of all wisdom.",
            japanese = "記憶はすべての知恵の母です。"
        ),
        Quote(
            english = "Silence is the true friend that never betrays.",
            japanese = "沈黙は決して裏切らない真の友人です。"
        ),
        Quote(
            english = "You might well remember that nothing can bring you success but yourself.",
            japanese = "あなた自身以外に成功をもたらすものはないということを覚えているかもしれません。"
        ),
        Quote(
            english = "Watch the little things; a small leak will sink a great ship.",
            japanese = "小さなことに注意してください。小さな水漏れが大きな船を沈没させます。"
        ),
        Quote(
            english = "God has given you one face, and you make yourself another.",
            japanese = "神はあなたに一つの顔を与え、あなたは自分自身を別の顔に変えます。"
        ),
        Quote(
            english = "To be wronged is nothing unless you continue to remember it.",
            japanese = "不当な扱いを受けても、それを思い出し続けなければ何も意味がありません。"
        ),
        Quote(
            english = "Kindness is the greatest wisdom.",
            japanese = "優しさは最大の知恵です。"
        ),
        Quote(
            english = "Action will remove the doubts that theory cannot solve.",
            japanese = "理論では解決できない疑問は行動によって解消されます。"
        ),
        Quote(
            english = "Don't miss all the beautiful colors of the rainbow looking for that pot of gold.",
            japanese = "美しい虹の色を見逃さず、金の壺を探してください。"
        ),
        Quote(
            english = "Your big opportunity may be right where you are now.",
            japanese = "あなたの大きなチャンスは、今あなたがいる場所にあるかもしれません。"
        ),
        Quote(
            english = "People who say it cannot be done should not interrupt those who are doing it.",
            japanese = "それはできないという人は、それをやっている人の邪魔をしてはいけません。"
        ),
        Quote(
            english = "The day you decide to do it is your lucky day.",
            japanese = "それをやると決めた日が幸運な日です。"
        ),
        Quote(
            english = "We must not say every mistake is a foolish one.",
            japanese = "すべての間違いが愚かだなどと言ってはいけません。"
        ),
        Quote(
            english = "Accept challenges, so that you may feel the exhilaration of victory.",
            japanese = "勝利の爽快感を味わうために、挑戦を受け入れてください。"
        ),
        Quote(
            english = "It is better to understand a little than to misunderstand a lot.",
            japanese = "たくさん誤解するよりも、少しだけ理解する方が良いです。"
        ),
        Quote(
            english = "You don't drown by falling in water. You drown by staying there.",
            japanese = "水に落ちても溺れることはありません。そこにいると溺れてしまいます。"
        ),
        Quote(
            english = "Never be afraid to try, remember... Amateurs built the ark, Professionals built the Titanic.",
            japanese = "挑戦することを決して恐れないでください、覚えておいてください...アマチュアが箱舟を作り、プロがタイタニックを作りました。"
        ),
        Quote(
            english = "Correction does much, but encouragement does more.",
            japanese = "矯正は多くの効果をもたらしますが、励ましはそれ以上の効果をもたらします。"
        ),
        Quote(
            english = "Know, first, who you are, and then adorn yourself accordingly.",
            japanese = "まず自分が何者であるかを知り、それに応じて自分を飾りましょう。"
        ),
        Quote(
            english = "The biggest adventure you can ever take is to live the life of your dreams.",
            japanese = "あなたができる最大の冒険は、夢のような人生を送ることです。"
        ),
        Quote(
            english = "Life is 10% what happens to you and 90% how you react to it.",
            japanese = "äººçã¯10%ãããªãã«èµ·ãããã¨ã§ã90%ãããªãã®åå¿ã ã"
        ),
        Quote(
            english = "To want to be what one can be is purpose in life.",
            japanese = "なりたい自分になりたいと願うことが人生の目的です。"
        ),
        Quote(
            english = "Remember that sometimes not getting what you want is a wonderful stroke of luck.",
            japanese = "時には欲しいものが手に入らないこともあるということを忘れないでください。"
        ),
        Quote(
            english = "History will be kind to me for I intend to write it.",
            japanese = "私がそれを書くつもりなら、歴史は私に親切にしてくれるでしょう。"
        ),
        Quote(
            english = "Our lives are a sum total of the choices we have made.",
            japanese = "私たちの人生は、私たちが行った選択の総和です。"
        ),
        Quote(
            english = "Time stays long enough for anyone who will use it.",
            japanese = "時間は誰が使っても十分に長く残ります。"
        ),
        Quote(
            english = "Never tell me the sky's the limit when there are footprints on the moon.",
            japanese = "月に足跡があるからといって、空が限界だなんて決して言わないでください。"
        ),
        Quote(
            english = "You must welcome change as the rule but not as your ruler.",
            japanese = "変化をルールとして歓迎する必要がありますが、支配者としては歓迎しません。"
        ),
        Quote(
            english = "Give whatever you are doing and whoever you are with the gift of your attention.",
            japanese = "あなたが何をしていても、あなたが誰であっても、注意を向けてください。"
        ),
        Quote(
            english = "Always be smarter than the people who hire you.",
            japanese = "常にあなたを雇う人々よりも賢くありましょう。"
        ),
        Quote(
            english = "Formula for success: under promise and over deliver.",
            japanese = "成功の方程式: 約束を守り、期待以上の成果を上げます。"
        ),
        Quote(
            english = "The eye sees only what the mind is prepared to comprehend.",
            japanese = "目は、心が理解しようとしているものだけを見ます。"
        ),
        Quote(
            english = "People seldom notice old clothes if you wear a big smile.",
            japanese = "満面の笑みを浮かべていれば、古着に気づかれることはほとんどありません。"
        ),
        Quote(
            english = "The more light you allow within you, the brighter the world you live in will be.",
            japanese = "あなたが自分の中にもっと多くの光を受け入れるほど、あなたの住む世界はより明るくなります。"
        ),
        Quote(
            english = "Nothing diminishes anxiety faster than action.",
            japanese = "行動より早く不安を軽減できるものはありません。"
        ),
        Quote(
            english = "Man cannot discover new oceans unless he has the courage to lose sight of the shore.",
            japanese = "岸を見失わない勇気がなければ、人は新しい海を発見することはできません。"
        ),
        Quote(
            english = "Everything that irritates us about others can lead us to an understanding about ourselves.",
            japanese = "他人について私たちがイライラすることはすべて、私たち自身についての理解につながる可能性があります。"
        ),
        Quote(
            english = "Can you imagine what I would do if I could do all I can?",
            japanese = "できる限りのことをできるとしたら、私が何をするか想像できますか?"
        ),
        Quote(
            english = "Ignorance never settle a question.",
            japanese = "無知では決して疑問が解決されません。"
        ),
        Quote(
            english = "The awareness of our own strength makes us modest.",
            japanese = "自分自身の強さを認識すると、私たちは謙虚になります。"
        ),
        Quote(
            english = "They must often change, who would be constant in happiness or wisdom.",
            japanese = "幸福や知恵が常に変わらない人は、頻繁に変化しなければなりません。"
        ),
        Quote(
            english = "There are no failures. Just experiences and your reactions to them.",
            japanese = "失敗はありません。ただの経験とそれに対するあなたの反応です。"
        ),
        Quote(
            english = "Your future depends on many things, but mostly on you.",
            japanese = "あなたの将来は多くのことに左右されますが、ほとんどはあなた次第です。"
        ),
        Quote(
            english = "Fear grows in darkness; if you think theres a bogeyman around, turn on the light.",
            japanese = "恐怖は暗闇の中で増大します。周りにボギーマンがいると思ったら、ライトをつけてください。"
        ),
        Quote(
            english = "The most important point is to accept yourself and stand on your two feet.",
            japanese = "一番大切なのは、自分を認めて自分の足で立つことです。"
        ),
        Quote(
            english = "Do not expect the world to look bright, if you habitually wear gray-brown glasses.",
            japanese = "灰茶色の眼鏡を習慣的に着用している人は、世界が明るく見えることを期待しないでください。"
        ),
        Quote(
            english = "As long as your going to be thinking anyway, think big.",
            japanese = "とにかく考えるつもりである限り、大きく考えてください。"
        ),
        Quote(
            english = "Without some goals and some efforts to reach it, no man can live.",
            japanese = "何らかの目標とそれに到達するための努力がなければ、人は生きていくことができません。"
        ),
        Quote(
            english = "He who obtains has little. He who scatters has much.",
            japanese = "得る者にはほとんど何もない。散らす人は多くのものを持っています。"
        ),
        Quote(
            english = "Myths which are believed in tend to become true.",
            japanese = "信じられている神話は真実になる傾向があります。"
        ),
        Quote(
            english = "The foot feels the foot when it feels the ground.",
            japanese = "足は地面を感じるときに足を感じます。"
        ),
        Quote(
            english = "Not what we have but what we enjoy constitutes our abundance.",
            japanese = "私たちが持っているものではなく、私たちが楽しんでいるものが私たちの豊かさを構成します。"
        ),
        Quote(
            english = "It is never too late to be what you might have been.",
            japanese = "なりたかった自分になるのに遅すぎるということはありません。"
        ),
        Quote(
            english = "The beginning is always today.",
            japanese = "始まりはいつも今日。"
        ),
        Quote(
            english = "In the long run we get no more than we have been willing to risk giving.",
            japanese = "長期的には、私たちが危険を冒して与えた以上のものは得られません。"
        ),
        Quote(
            english = "Self-trust is the first secret of success.",
            japanese = "自信が成功の第一の秘訣です。"
        ),
        Quote(
            english = "Don't look back. Something might be gaining on you.",
            japanese = "振り返るな。何かがあなたに利益をもたらしているかもしれません。"
        ),
        Quote(
            english = "Men are disturbed not by things, but by the view which they take of them.",
            japanese = "人間は物事によってではなく、物事についての見方によって動揺します。"
        ),
        Quote(
            english = "Happiness is a Swedish sunset, it is there for all, but most of us look the other way and lose it.",
            japanese = "幸福はスウェーデンの夕日であり、誰にとってもそこにありますが、私たちのほとんどは見て見ぬふりをしてそれを失います。"
        ),
        Quote(
            english = "A smile is a light in the window of your face to show your heart is at home.",
            japanese = "笑顔はあなたの顔の窓に差し込む光であり、心がくつろいでいることを示します。"
        ),
        Quote(
            english = "Look forward to spring as a time when you can start to see what nature has to offer once again.",
            japanese = "自然の魅力をもう一度実感できる春を楽しみにしていてください。"
        ),
        Quote(
            english = "Trust your own instinct. Your mistakes might as well be your own, instead of someone elses.",
            japanese = "自分自身の本能を信じてください。あなたの間違いは、他人ではなく、あなた自身のものかもしれません。"
        ),
        Quote(
            english = "The least movement is of importance to all nature. The entire ocean is affected by a pebble.",
            japanese = "あらゆる自然にとって、ほんのわずかな動きも重要です。海全体が小石の影響を受けます。"
        ),
        Quote(
            english = "I am always doing that which I can not do, in order that I may learn how to do it.",
            japanese = "私はいつもできないことを、やり方を学ぶためにやっているのです。"
        ),
        Quote(
            english = "You may only be someone in the world, but to someone else, you may be the world.",
            japanese = "あなたは世界の中の誰かにすぎないかもしれませんが、他の誰かにとってはあなたが世界なのかもしれません。"
        ),
        Quote(
            english = "Every artist dips his brush in his own soul, and paints his own nature into his pictures.",
            japanese = "すべての芸術家は自分自身の魂に筆を浸し、自分自身の性質を絵の中に描きます。"
        ),
        Quote(
            english = "Do not give your attention to what others do or fail to do; give it to what you do or fail to do.",
            japanese = "他人が何をしたか、何をしなかったかに注意を払わないでください。自分がやったことや失敗したことにそれを与えてください。"
        ),
        Quote(
            english = "There is one thing you have got to learn about our movement. Three people are better than no people.",
            japanese = "私たちの運動について学ばなければならないことが 1 つあります。 3人の方が、いないよりはマシです。"
        ),
        Quote(
            english = "Happiness is a perfume you cannot pour on others without getting a few drops on yourself.",
            japanese = "幸福とは、自分自身に数滴垂らさずには他人に注ぐことの​​できない香水です。"
        ),
        Quote(
            english = "The amount of happiness that you have depends on the amount of freedom you have in your heart.",
            japanese = "あなたが得られる幸福の量は、あなたの心の自由の量によって決まります。"
        ),
        Quote(
            english = "Sometimes it is better to lose and do the right thing than to win and do the wrong thing.",
            japanese = "勝って間違ったことをするよりも、負けて正しいことをする方が良い場合もあります。"
        ),
        Quote(
            english = "Let us always meet each other with smile, for the smile is the beginning of love.",
            japanese = "いつも笑顔で会いましょう、笑顔は恋の始まりですから。"
        ),
        Quote(
            english = "A bend in the road is not the end of the road...unless you fail to make the turn.",
            japanese = "道路の曲がり角は、曲がり損ねない限り、道の終わりではありません。"
        ),
        Quote(
            english = "We are what we repeatedly do. Excellence, then, is not an act, but a habit.",
            japanese = "私たちは繰り返し行っていることそのものなのです。つまり、卓越性とは行為ではなく習慣なのです。"
        ),
        Quote(
            english = "Living at risk is jumping off the cliff and building your wings on the way down.",
            japanese = "危険を冒して生きるということは、崖から飛び降り、降りる途中で翼を築くことです。"
        ),
        Quote(
            english = "In the depth of winter, I finally learned that there was within me an invincible summer.",
            japanese = "真冬になって、ようやく自分の中に無敵の夏があることを知りました。"
        ),
        Quote(
            english = "A failure is a man who has blundered but is not capable of cashing in on the experience.",
            japanese = "失敗者とは、失敗はしたものの、その経験を活かすことができない人のことです。"
        ),
        Quote(
            english = "The power of intuitive understanding will protect you from harm until the end of your days.",
            japanese = "直観的な理解の力は、人生の終わりまであなたを危害から守ってくれます。"
        ),
        Quote(
            english = "The best thing about the future is that it only comes one day at a time.",
            japanese = "未来について最も素晴らしいことは、それは一度に 1 日しか来ないということです。"
        ),
        Quote(
            english = "We have two ears and one mouth so that we can listen twice as much as we speak.",
            japanese = "私たちは耳が 2 つ、口が 1 つあるので、話す量の 2 倍聞くことができます。"
        ),
        Quote(
            english = "Fear of failure is one attitude that will keep you at the same point in your life.",
            japanese = "失敗への恐れは、人生の同じ時点であなたを留まらせる態度の 1 つです。"
        ),
        Quote(
            english = "Friends are those rare people who ask how we are and then wait to hear the answer.",
            japanese = "友人とは、私たちの様子を尋ねて、答えを聞くのを待っているまれな人々です。"
        ),
        Quote(
            english = "If we learn to open our hearts, anyone, including the people who drive us crazy, can be our teacher.",
            japanese = "私たちが心を開くことを学べば、私たちを狂わせる人も含め、誰でも私たちの教師になれるのです。"
        ),
        Quote(
            english = "A hero is no braver than an ordinary man, but he is braver five minutes longer.",
            japanese = "英雄は普通の人より勇敢ではありませんが、5分長く勇敢です。"
        ),
        Quote(
            english = "While we try to teach our children all about life, our children teach us what life is all about.",
            japanese = "私たちが子供たちに人生についてすべてを教えようとしている間、子供たちは私たちに人生とは何なのかを教えてくれます。"
        ),
        Quote(
            english = "The Creator has not given you a longing to do that which you have no ability to do.",
            japanese = "創造主は、あなたに能力のないことをやりたいという願望をあなたに与えたわけではありません。"
        ),
        Quote(
            english = "It's so simple to be wise. Just think of something stupid to say and then don't say it.",
            japanese = "賢くなることはとても簡単です。何か愚かなことを言うことを考えてから、それを言わないでください。"
        ),
        Quote(
            english = "Until you make peace with who you are, you will never be content with what you have.",
            japanese = "ありのままの自分と和解するまでは、自分が持っているものに決して満足することはできません。"
        ),
        Quote(
            english = "No one saves us but ourselves. No one can and no one may. We ourselves must walk the path.",
            japanese = "私たちを救ってくれるのは私たち自身だけです。誰もできませんし、できる人もいません。私たち自身がその道を歩まなければなりません。"
        ),
        Quote(
            english = "Happiness is when what you think, what you say, and what you do are in harmony.",
            japanese = "幸福とは、自分の考え、発言、行動が調和しているときです。"
        ),
        Quote(
            english = "Courage is the discovery that you may not win, and trying when you know you can lose.",
            japanese = "勇気とは、勝てないかもしれないという発見、そして負けるかもしれないとわかっているときに挑戦することです。"
        ),
        Quote(
            english = "When you realize how perfect everything is you will tilt your head back and laugh at the sky.",
            japanese = "すべてがどれほど完璧であるかを理解したとき、あなたは首を後ろに傾けて空を笑うでしょう。"
        ),
        Quote(
            english = "Nobody made a greater mistake than he who did nothing because he could do only a little.",
            japanese = "少ししかできなかったために何もしなかった人ほど大きな間違いを犯した人はいません。"
        ),
        Quote(
            english = "The greatest minds are capable of the greatest vices as well as of the greatest virtues.",
            japanese = "最も偉大な精神は、最も偉大な美徳だけでなく、最も偉大な悪徳も行うことができる。"
        ),
        Quote(
            english = "A man should look for what is, and not for what he thinks should be.",
            japanese = "人は、こうあるべきだと思うものを探すのではなく、今あるものを探すべきです。"
        ),
        Quote(
            english = "Difficulties are meant to rouse, not discourage. The human spirit is to grow strong by conflict.",
            japanese = "困難はやる気を起こさせるものであり、落胆させるものではありません。人間の精神は争いを通じて強くなるのです。"
        ),
        Quote(
            english = "If you have no respect for your own values how can you be worthy of respect from others.",
            japanese = "自分自身の価値観を尊重できなければ、どうやって他人から尊敬に値する人間になれるでしょうか。"
        ),
        Quote(
            english = "Some people are always grumbling because roses have thorns; I am thankful that thorns have roses.",
            japanese = "バラにはトゲがあるからといって、いつも不平を言っている人もいます。いばらにバラがあるのはありがたいことです。"
        ),
        Quote(
            english = "To choose what is difficult all ones days, as if it were easy, that is faith.",
            japanese = "日々難しいことを、あたかも簡単であるかのように選択すること、それが信仰です。"
        ),
        Quote(
            english = "He who conquers others is strong; He who conquers himself is mighty.",
            japanese = "他人を征服する者は強い。自分自身に打ち勝つ者は強い。"
        ),
        Quote(
            english = "Anything you really want, you can attain, if you really go after it.",
            japanese = "本当に欲しいものは、本気で追い求めれば必ず手に入る。"
        ),
        Quote(
            english = "Arriving at one point is the starting point to another.",
            japanese = "ある地点に到着すると、別の地点への出発点になります。"
        ),
        Quote(
            english = "The foolish man seeks happiness in the distance, the wise grows it under his feet.",
            japanese = "愚かな人は遠くに幸福を求めますが、賢者は自分の足の下で幸福を育てます。"
        ),
        Quote(
            english = "The greatest part of our happiness depends on our dispositions, not our circumstances.",
            japanese = "私たちの幸福の最大の部分は、状況ではなく、私たちの性質に依存します。"
        ),
        Quote(
            english = "It is only possible to live happily ever after on a day to day basis.",
            japanese = "毎日を幸せに生きていくしかありません。"
        ),
        Quote(
            english = "A man sees in the world what he carries in his heart.",
            japanese = "人は自分が心の中に抱えているものを世界に見る。"
        ),
        Quote(
            english = "Action may not always bring happiness, but there is no happiness without action.",
            japanese = "行動が常に幸福をもたらすとは限りませんが、行動のない幸福はありません。"
        ),
        Quote(
            english = "Believe deep down in your heart that you're destined to do great things.",
            japanese = "åå¤§ãªãã¨ãããéå½ã«ããã¨ãå¿ã®å¥¥åºããä¿¡ããã"
        ),
        Quote(
            english = "Sooner or later, those who win are those who think they can.",
            japanese = "遅かれ早かれ、勝者は自分にはできると思った人です。"
        ),
        Quote(
            english = "The only limit to your impact is your imagination and commitment.",
            japanese = "あなたの影響力を制限できるのはあなたの想像力と献身だけです。"
        ),
        Quote(
            english = "You are special, you are unique, you are the best!",
            japanese = "あなたは特別で、ユニークで、あなたは最高です!"
        ),
        Quote(
            english = "To know oneself is to study oneself in action with another person.",
            japanese = "自分自身を知ることは、他の人との行動の中で自分自身を学ぶことです。"
        ),
        Quote(
            english = "We must not allow ourselves to become like the system we oppose.",
            japanese = "私たちは自分たちが反対するシステムのようになることを許してはなりません。"
        ),
        Quote(
            english = "Smile, breathe and go slowly.",
            japanese = "笑って、呼吸して、ゆっくり進みましょう。"
        ),
        Quote(
            english = "Reality is merely an illusion, albeit a very persistent one.",
            japanese = "現実は、たとえ非常に永続的なものであっても、単なる幻想にすぎません。"
        ),
        Quote(
            english = "When you come to the end of your rope, tie a knot and hang on.",
            japanese = "ロープの端に来たら、結び目を作ってぶら下がります。"
        ),
        Quote(
            english = "Always be mindful of the kindness and not the faults of others.",
            japanese = "他人の欠点ではなく、優しさを常に心に留めてください。"
        ),
        Quote(
            english = "Everything that irritates us about others can lead us to an understanding of ourselves.",
            japanese = "他人に関して私たちをイライラさせるすべてのことは、私たち自身の理解につながる可能性があります。"
        ),
        Quote(
            english = "When fate hands us a lemon, lets try to make lemonade.",
            japanese = "運命が私たちにレモンをくれたら、レモネードを作ってみよう。"
        ),
        Quote(
            english = "The weak can never forgive. Forgiveness is the attribute of the strong.",
            japanese = "弱い者は絶対に許せない。許しは強者の特質である。"
        ),
        Quote(
            english = "A man is great by deeds, not by birth.",
            japanese = "人は生まれではなく、行いによって偉大になるのです。"
        ),
        Quote(
            english = "Success is getting what you want. Happiness is wanting what you get.",
            japanese = "成功とは欲しいものを手に入れることです。幸福とは、手に入れたものを望むことです。"
        ),
        Quote(
            english = "Put your future in good hands, your own.",
            japanese = "自分の未来を自分の手にしっかりと託しましょう。"
        ),
        Quote(
            english = "Truth isn't all about what actually happens but more about how what has happened is interpreted.",
            japanese = "真実とは、実際に起こったことのすべてではなく、起こったことがどのように解釈されるかということです。"
        ),
        Quote(
            english = "A good rest is half the work.",
            japanese = "十分な休息は仕事の半分です。"
        ),
        Quote(
            english = "Don't judge each day by the harvest you reap but by the seeds that you plant.",
            japanese = "毎日を刈り取った収穫によって判断するのではなく、自分が蒔いた種によって判断してください。"
        ),
        Quote(
            english = "Small opportunities are often the beginning of great enterprises.",
            japanese = "多くの場合、小さなチャンスが偉大な事業の始まりとなります。"
        ),
        Quote(
            english = "To be tested is good. The challenged life may be the best therapist.",
            japanese = "テストされるのはいいことだ。挑戦的な人生こそが最高のセラピストなのかもしれない。"
        ),
        Quote(
            english = "Take heed: you do not find what you do not seek.",
            japanese = "注意してください。求めていないものは見つかりません。"
        ),
        Quote(
            english = "Happiness is the reward we get for living to the highest right we know.",
            japanese = "幸福とは、私たちが知っている最高の権利に従って生きることで得られる報酬です。"
        ),
        Quote(
            english = "Be slow of tongue and quick of eye.",
            japanese = "舌は遅く、目は速くありなさい。"
        ),
        Quote(
            english = "Freedom is not worth having if it does not connote freedom to err.",
            japanese = "間違いを犯す自由を意味しない自由は、持つ価値がありません。"
        ),
        Quote(
            english = "I have always thought the actions of men the best interpreters of their thoughts.",
            japanese = "私はいつも、男性の行動が彼らの考えを最もよく理解するものであると考えてきました。"
        ),
        Quote(
            english = "To dare is to lose ones footing momentarily. To not dare is to lose oneself.",
            japanese = "あえてするということは、一時的に足場を失うことです。勇気を出さないということは自分自身を失うことだ。"
        ),
        Quote(
            english = "No day in which you learn something is a complete loss.",
            japanese = "何かを学んだ日が完全に無駄になることはありません。"
        ),
        Quote(
            english = "Peace cannot be kept by force. It can only be achieved by understanding.",
            japanese = "平和は力によって維持することはできません。それは理解することによってのみ達成できます。"
        ),
        Quote(
            english = "Real success is finding your lifework in the work that you love.",
            japanese = "本当の成功とは、自分の好きな仕事にライフワークを見つけることです。"
        ),
        Quote(
            english = "Better than a thousand hollow words, is one word that brings peace.",
            japanese = "1,000 の空虚な言葉よりも優れているのは、平和をもたらす 1 つの言葉です。"
        ),
        Quote(
            english = "All the flowers of all the tomorrows are in the seeds of today.",
            japanese = "すべての明日の花はすべて今日の種の中にあります。"
        ),
        Quote(
            english = "Your sacred space is where you can find yourself again and again.",
            japanese = "あなたの神聖な空間は、あなたが何度でも自分自身を見つけることができる場所です。"
        ),
        Quote(
            english = "As you think, so shall you become.",
            japanese = "あなたが思うように、あなたもそうなるでしょう。"
        ),
        Quote(
            english = "In seed time learn, in harvest teach, in winter enjoy.",
            japanese = "種まきの時期には学び、収穫の時期には教え、冬には楽しみます。"
        ),
        Quote(
            english = "Happiness does not come from having much, but from being attached to little.",
            japanese = "幸福は多くを持つことではなく、少ないものに執着することから生まれます。"
        ),
        Quote(
            english = "Every gift from a friend is a wish for your happiness.",
            japanese = "友人からの贈り物はすべてあなたの幸せを願っています。"
        ),
        Quote(
            english = "Go put your creed into the deed. Nor speak with double tongue.",
            japanese = "自分の信念を行動に表してみませんか。二枚舌で話すこともありません。"
        ),
        Quote(
            english = "The wisest men follow their own direction.",
            japanese = "最も賢い人は自分の方向に従う。"
        ),
        Quote(
            english = "Hope arouses, as nothing else can arouse, a passion for the possible.",
            japanese = "希望は、他のものでは呼び覚まされないように、可能性への情熱を呼び起こします。"
        ),
        Quote(
            english = "Everything has beauty, but not everyone sees it.",
            japanese = "すべてのものには美しさがありますが、誰もがそれを見るわけではありません。"
        ),
        Quote(
            english = "Nothing ever goes away until it has taught us what we need to know.",
            japanese = "私たちが知る必要があることを教えてくれるまで、何も消えることはありません。"
        ),
        Quote(
            english = "When you learn, teach. When you get, give.",
            japanese = "学ぶときは、教えてください。もらったら、あげてください。"
        ),
        Quote(
            english = "Only when we are no longer afraid do we begin to live.",
            japanese = "恐れがなくなったときに初めて、私たちは生き始めるのです。"
        ),
        Quote(
            english = "If you smile when no one else is around, you really mean it.",
            japanese = "周りに誰もいないときに微笑むなら、それは本気です。"
        ),
        Quote(
            english = "Love is the only force capable of transforming an enemy into friend.",
            japanese = "愛は敵を味方に変えることができる唯一の力です。"
        ),
        Quote(
            english = "In all chaos there is a cosmos, in all disorder a secret order.",
            japanese = "すべての混沌の中に宇宙があり、すべての無秩序の中に秘密の秩序がある。"
        ),
        Quote(
            english = "A man is not where he lives but where he loves.",
            japanese = "人は自分が住んでいる場所ではなく、愛する場所にいます。"
        ),
        Quote(
            english = "The price of greatness is responsibility.",
            japanese = "偉大さの代償には責任が伴います。"
        ),
        Quote(
            english = "Decision is a risk rooted in the courage of being free.",
            japanese = "決断は、自由であるという勇気に根ざしたリスクです。"
        ),
        Quote(
            english = "Your mind will answer most questions if you learn to relax and wait for the answer.",
            japanese = "リラックスして答えを待つことを学べば、ほとんどの質問に心は答えてくれます。"
        ),
        Quote(
            english = "The world doesn't happen to you it happens from you.",
            japanese = "世界はあなたに起こるのではなく、あなたから起こるのです。"
        ),
        Quote(
            english = "We cannot solve our problems with the same thinking we used when we created them.",
            japanese = "問題を生み出したときと同じ考え方では問題を解決することはできません。"
        ),
        Quote(
            english = "More powerful than the will to win is the courage to begin.",
            japanese = "勝ちたいという意志よりも強いのは、始める勇気です。"
        ),
        Quote(
            english = "Learning is finding out what you already know.",
            japanese = "学習とは、すでに知っていることを見つけることです。"
        ),
        Quote(
            english = "Saying thank you is more than good manners. It is good spirituality.",
            japanese = "ありがとうと言うのは単なるマナーではありません。それは良い精神性です。"
        ),
        Quote(
            english = "Silence is a source of great strength.",
            japanese = "æ²é»ã¯ãå¤§ããªåã®æºã ã"
        ),
        Quote(
            english = "Joy is the best makeup.",
            japanese = "ジョイは最高のメイクアップだ。"
        ),
        Quote(
            english = "There is no great genius without some touch of madness.",
            japanese = "偉大な天才であっても、多少の狂気の要素がなければ存在しません。"
        ),
        Quote(
            english = "A jug fills drop by drop.",
            japanese = "水差しに一滴ずつ満たされます。"
        ),
        Quote(
            english = "Until you make peace with who you are, you'll never be content with what you have.",
            japanese = "ありのままの自分と折り合いをつけるまでは、自分が持っているものに満足することは決してないでしょう。"
        ),
        Quote(
            english = "We aim above the mark to hit the mark.",
            japanese = "目標を達成するためにマークの上を目指します。"
        ),
        Quote(
            english = "Being angry never solves anything.",
            japanese = "怒っても何も解決しません。"
        ),
        Quote(
            english = "All men who have achieved great things have been great dreamers.",
            japanese = "偉大なことを達成した人は皆、偉大な夢想家でした。"
        ),
        Quote(
            english = "Mediocrity knows nothing higher than itself, but talent instantly recognizes genius.",
            japanese = "凡庸はそれ自体よりも優れたものを何も知りませんが、才能はすぐに天才を認識します。"
        ),
        Quote(
            english = "Where all think alike, no one thinks very much.",
            japanese = "皆が同じように考えるところでは、誰も深く考えません。"
        ),
        Quote(
            english = "Everything that exists is in a manner the seed of that which will be.",
            japanese = "存在するすべてのものは、ある意味、将来存在するものの種です。"
        ),
        Quote(
            english = "Be less curious about people and more curious about ideas.",
            japanese = "人に対する好奇心を減らし、アイデアに対してもっと好奇心を持ちましょう。"
        ),
        Quote(
            english = "The heart has eyes which the brain knows nothing of.",
            japanese = "心には目があり、脳はそれを知りません。"
        ),
        Quote(
            english = "Only those who dare to fail greatly can ever achieve greatly.",
            japanese = "å¤§ããå¤±æããåæ°ã®ããèã ãããå¤§ããéæã§ããã"
        ),
        Quote(
            english = "Lose an hour in the morning, and you will spend all day looking for it.",
            japanese = "朝一時間無駄にすると、一日中探してしまうことになります。"
        ),
        Quote(
            english = "Mistakes are always forgivable, if one has the courage to admit them.",
            japanese = "間違いを認める勇気があれば、間違いはいつでも許されます。"
        ),
        Quote(
            english = "Go to your bosom: Knock there, and ask your heart what it doth know.",
            japanese = "自分の胸に行きなさい。そこをノックして、自分の心に何を知っているか尋ねてください。"
        ),
        Quote(
            english = "Happiness mainly comes from our own attitude, rather than from external factors.",
            japanese = "幸福は主に、外的要因ではなく、私たち自身の態度からもたらされます。"
        ),
        Quote(
            english = "If you do not change direction, you may end up where you are heading.",
            japanese = "方向を変えないと、目的地に行き着いてしまう可能性があります。"
        ),
        Quote(
            english = "What we see is mainly what we look for.",
            japanese = "私たちが見ているものは主に私たちが探しているものです。"
        ),
        Quote(
            english = "Stay away from what might have been and look at what will be.",
            japanese = "かつてあったかもしれないことから離れて、これからどうなるかを見てください。"
        ),
        Quote(
            english = "Act as if what you do makes a difference. It does.",
            japanese = "ããªãã®ãããã¨ãéããçããã®ããã«è¡åãããå®éã«çãã"
        ),
        Quote(
            english = "Passion creates the desire for more and action fuelled by passion creates a future.",
            japanese = "情熱はさらなる欲求を生み、情熱に支えられた行動は未来を生み出します。"
        ),
        Quote(
            english = "Do good by stealth, and blush to find it fame.",
            japanese = "こっそりと善を行い、それが名声を得るために顔を赤らめます。"
        ),
        Quote(
            english = "Opportunity often comes disguised in the form of misfortune, or temporary defeat.",
            japanese = "チャンスは、不幸や一時的な敗北の形をとってやってくることがよくあります。"
        ),
        Quote(
            english = "Don't talk about what you have done or what you are going to do.",
            japanese = "自分が何をしてきたか、あるいはこれから何をしようとしているかについて話さないでください。"
        ),
        Quote(
            english = "Most powerful is he who has himself in his own power.",
            japanese = "最も強力なのは、自分自身の力を持っている人です。"
        ),
        Quote(
            english = "We don't stop playing because we grow old; we grow old because we stop playing.",
            japanese = "私たちは年をとったからといって遊ぶのをやめるわけではありません。遊ぶのをやめるから私たちは老いてしまうのです。"
        ),
        Quote(
            english = "Experience can only be gained by doing not by thinking or dreaming.",
            japanese = "経験は考えたり夢見たりすることではなく、実行することによってのみ得られます。"
        ),
        Quote(
            english = "Always tell the truth. That way, you don't have to remember what you said.",
            japanese = "常に真実を語ってください。そうすれば、自分が言ったことを覚えておく必要はありません。"
        ),
        Quote(
            english = "From wonder into wonder existence opens.",
            japanese = "驚異から驚異への存在が開かれます。"
        ),
        Quote(
            english = "He who fears being conquered is sure of defeat.",
            japanese = "征服されることを恐れる者は必ず敗北する。"
        ),
        Quote(
            english = "Life is what happens while you are making other plans.",
            japanese = "人生とは、他の計画を立てている間に起こるものです。"
        ),
        Quote(
            english = "Doing what you love is the cornerstone of having abundance in your life.",
            japanese = "好きなことをすることは、人生を豊かにするための基礎です。"
        ),
        Quote(
            english = "Kindness is the golden chain by which society is bound together.",
            japanese = "優しさは社会を結びつける黄金の鎖です。"
        ),
        Quote(
            english = "You need chaos in your soul to give birth to a dancing star.",
            japanese = "踊るスターを生み出すには、魂に混沌が必要です。"
        ),
        Quote(
            english = "It can't be spring if your heart is filled with past failures.",
            japanese = "過去の失敗で心がいっぱいでは春は来れません。"
        ),
        Quote(
            english = "No yesterdays are ever wasted for those who give themselves to today.",
            japanese = "今日に身を捧げる人にとって、どんな昨日も無駄になることはありません。"
        ),
        Quote(
            english = "There are no failures, just experiences and your reactions to them.",
            japanese = "失敗はなく、ただ経験とそれに対する自分の反応があるだけです。"
        ),
        Quote(
            english = "Action is the foundational key to all success.",
            japanese = "行動はすべての成功の基本的な鍵です。"
        ),
        Quote(
            english = "What is necessary to change a person is to change his awareness of himself.",
            japanese = "人を変えるために必要なのは、自分自身に対する意識を変えることです。"
        ),
        Quote(
            english = "Positive thinking will let you do everything better than negative thinking will.",
            japanese = "ãã¸ãã£ãæèã¯ããã¬ãã£ãæèãããããããã¨ããã¾ãããã¦ãããã"
        ),
        Quote(
            english = "We shall never know all the good that a simple smile can do.",
            japanese = "シンプルな笑顔がもたらす良いことを私たちは決して知ることはできません。"
        ),
        Quote(
            english = "Nothing is so strong as gentleness. Nothing is so gentle as real strength.",
            japanese = "優しさほど強いものはありません。本当の強さほど優しいものはありません。"
        ),
        Quote(
            english = "Imagination is not a talent of some men but is the health of every man.",
            japanese = "想像力は一部の人の才能ではなく、すべての人の健康です。"
        ),
        Quote(
            english = "We must embrace pain and burn it as fuel for our journey.",
            japanese = "私たちは痛みを受け入れ、それを旅の燃料として燃やさなければなりません。"
        ),
        Quote(
            english = "Don't wait for people to be friendly. Show them how.",
            japanese = "人々が友好的になるのを待ってはいけません。その方法を教えてください。"
        ),
        Quote(
            english = "A gem cannot be polished without friction, nor a man perfected without trials.",
            japanese = "宝石は摩擦なしには磨かれませんし、人間も試練なしには完成されません。"
        ),
        Quote(
            english = "Each day can be one of triumph if you keep up your interests.",
            japanese = "興味を持ち続ければ、毎日が勝利の日になるでしょう。"
        ),
        Quote(
            english = "The place to improve the world is first in one's own heart and head and hands.",
            japanese = "世界を良くする場所は、まず自分の心と頭と手の中にあります。"
        ),
        Quote(
            english = "Winners have simply formed the habit of doing things losers don't like to do.",
            japanese = "勝者は、敗者がやりたくないことをする習慣を身に着けているだけです。"
        ),
        Quote(
            english = "Nature is a mutable cloud which is always and never the same.",
            japanese = "自然は変化する雲であり、常に同じではありません。"
        ),
        Quote(
            english = "Life is what you make of it. Always has been, always will be.",
            japanese = "人生は自分で作るものです。これまでもそうだったし、これからもそうだろう。"
        ),
        Quote(
            english = "Worry often gives a small thing a big shadow.",
            japanese = "心配は小さなことに大きな影を与えることがよくあります。"
        ),
        Quote(
            english = "I want you to be everything that's you, deep at the center of your being.",
            japanese = "私はあなたが、あなたの存在の中心にあるすべてがあなたであることを望んでいます。"
        ),
        Quote(
            english = "We know what we are, but know not what we may be.",
            japanese = "私たちは自分が何であるかを知っていますが、自分が何であるかは知りません。"
        ),
        Quote(
            english = "Freedom is what you do with what's been done to you.",
            japanese = "自由とは、自分にされたことをどうするかです。"
        ),
        Quote(
            english = "The truth which has made us free will in the end make us glad also.",
            japanese = "私たちを自由意志にしてくれた真実は、最終的には私たちを喜ばせてくれます。"
        ),
        Quote(
            english = "He who has imagination without learning has wings but no feet.",
            japanese = "学ばずに想像力を持つ人には翼があっても足がありません。"
        ),
        Quote(
            english = "Whoso loves, believes the impossible.",
            japanese = "愛する人は不可能を信じます。"
        ),
        Quote(
            english = "It isn't where you come from, it's where you're going that counts.",
            japanese = "どこから来たかではなく、どこへ行くかが重要なのです。"
        ),
        Quote(
            english = "The greatest obstacle to connecting with our joy is resentment.",
            japanese = "私たちの喜びとつながるための最大の障害は、憤りです。"
        ),
        Quote(
            english = "When anger use your energy to do something productive.",
            japanese = "怒りがあるときは、何か生産的なことをするためにエネルギーを使います。"
        ),
        Quote(
            english = "We are all something, but none of us are everything.",
            japanese = "私たちは皆何かを持っていますが、私たちの誰もがすべてではありません。"
        ),
        Quote(
            english = "If you can't explain it simply, you don't understand it well enough.",
            japanese = "簡単に説明できない場合は、十分に理解していないことになります。"
        ),
        Quote(
            english = "He who lives in harmony with himself lives in harmony with the world.",
            japanese = "自分自身と調和して生きる人は、世界と調和して生きます。"
        ),
        Quote(
            english = "He who knows himself is enlightened.",
            japanese = "自分自身を知っている人は啓発されています。"
        ),
        Quote(
            english = "Build a better mousetrap and the world will beat a path to your door.",
            japanese = "より良いネズミ捕りを作れば、世界があなたのドアに近づくでしょう。"
        ),
        Quote(
            english = "As our case is new, we must think and act anew.",
            japanese = "私たちの事件は新しいので、新たに考えて行動しなければなりません。"
        ),
        Quote(
            english = "If you can't feed a hundred people, then feed just one.",
            japanese = "100人を養うことができないなら、たった1人を養えばいいのです。"
        ),
        Quote(
            english = "In three words I can sum up everything Ive learned about life: it goes on.",
            japanese = "私が人生について学んだすべてを 3 つの言葉で要約できます。それは続いていくということです。"
        ),
        Quote(
            english = "Don't let today's disappointments cast a shadow on tomorrow's dreams.",
            japanese = "今日の失望が明日の夢に影を落とさないようにしてください。"
        ),
        Quote(
            english = "You always succeed in producing a result.",
            japanese = "必ず結果を出すことに成功します。"
        ),
        Quote(
            english = "Everything you are against weakens you. Everything you are for empowers you.",
            japanese = "あなたが反対するものはすべてあなたを弱体化させます。あなたのためのすべてがあなたに力を与えます。"
        ),
        Quote(
            english = "As we risk ourselves, we grow. Each new experience is a risk.",
            japanese = "自分自身を危険にさらすことで、私たちは成長します。新しい経験にはそれぞれリスクが伴います。"
        ),
        Quote(
            english = "Who we are never changes. Who we think we are does.",
            japanese = "私たちが誰であるかは決して変わりません。私たちが自分だと思っている人はそうなります。"
        ),
        Quote(
            english = "The final proof of greatness lies in being able to endure criticism without resentment.",
            japanese = "偉大さの最後の証拠は、憤慨することなく批判に耐えられるかどうかにあります。"
        ),
        Quote(
            english = "An invasion of armies can be resisted, but not an idea whose time has come.",
            japanese = "軍隊の侵略に抵抗することはできますが、それは時代が来たという考えではありません。"
        ),
        Quote(
            english = "Never let lack of money interfere with having fun.",
            japanese = "お金がないからといって、決して楽しむことを妨げないでください。"
        ),
        Quote(
            english = "Excellence is not a skill. It is an attitude.",
            japanese = "優秀さはスキルではありません。それは態度です。"
        ),
        Quote(
            english = "People may doubt what you say, but they will believe what you do.",
            japanese = "人々はあなたの言うことを疑うかもしれませんが、あなたの行動を信じるでしょう。"
        ),
        Quote(
            english = "The most formidable weapon against errors of every kind is reason.",
            japanese = "あらゆる種類のエラーに対する最も強力な武器は理性です。"
        ),
        Quote(
            english = "It's important to know that words don't move mountains. Work, exacting work moves mountains.",
            japanese = "言葉は山を動かすものではないことを知ることが重要です。仕事、厳しい仕事は山をも動かします。"
        ),
        Quote(
            english = "Beware of missing chances; otherwise it may be altogether too late some day.",
            japanese = "チャンスを逃さないように注意してください。そうしないと、いつか完全に手遅れになる可能性があります。"
        ),
        Quote(
            english = "You only lose what you cling to.",
            japanese = "しがみついているものを失うだけです。"
        ),
        Quote(
            english = "Life is a succession of moments. To live each one is to succeed.",
            japanese = "人生は瞬間の連続です。それぞれを生きることが成功することです。"
        ),
        Quote(
            english = "Most of the shadows of life are caused by standing in our own sunshine.",
            japanese = "人生の影のほとんどは、私たちが太陽の光を浴びることによって引き起こされます。"
        ),
        Quote(
            english = "Good actions give strength to ourselves and inspire good actions in others.",
            japanese = "良い行動は自分自身に力を与え、他の人に良い行動を促します。"
        ),
        Quote(
            english = "I know but one freedom and that is the freedom of the mind.",
            japanese = "私が知っている自由はただ一つ、それは心の自由です。"
        ),
        Quote(
            english = "In the middle of every difficulty lies opportunity.",
            japanese = "ãã¹ã¦ã®å°é£ã®ä¸­ã«ããã£ã³ã¹ãããã"
        ),
        Quote(
            english = "Every human being is the author of his own health or disease.",
            japanese = "すべての人間は、自分自身の健康や病気の作者です。"
        ),
        Quote(
            english = "When in doubt, tell the truth.",
            japanese = "疑わしいときは、真実を伝えてください。"
        ),
        Quote(
            english = "Every great advance in science has issued from a new audacity of the imagination.",
            japanese = "科学におけるあらゆる大きな進歩は、想像力の新たな大胆さから生まれました。"
        ),
        Quote(
            english = "The ladder of success is never crowded at the top.",
            japanese = "成功のはしごの頂上に人が集まることはありません。"
        ),
        Quote(
            english = "He who has health has hope, and he who has hope has everything.",
            japanese = "健康を持つ人は希望を持ち、希望を持つ人はすべてを持っています。"
        ),
        Quote(
            english = "All great achievements require time.",
            japanese = "すべての偉大な成果には時間がかかります。"
        ),
        Quote(
            english = "No person is your friend who demands your silence, or denies your right to grow.",
            japanese = "沈黙を要求したり、成長する権利を否定したりする人はあなたの友人ではありません。"
        ),
        Quote(
            english = "Impossibilities are merely things which we have not yet learned.",
            japanese = "不可能とは、私たちがまだ学んでいないことにすぎません。"
        ),
        Quote(
            english = "Vision without action is a daydream. Action without vision is a nightmare.",
            japanese = "行動の伴わないビジョンは白昼夢に過ぎません。ビジョンのない行動は悪夢です。"
        ),
        Quote(
            english = "The Superior Man is aware of Righteousness, the inferior man is aware of advantage.",
            japanese = "優れた人は正義を認識し、劣った人は優位性を認識します。"
        ),
        Quote(
            english = "He who angers you conquers you.",
            japanese = "あなたを怒らせる者はあなたを征服します。"
        ),
        Quote(
            english = "I never worry about action, but only inaction.",
            japanese = "私は行動については決して心配せず、行動しないことだけを心配します。"
        ),
        Quote(
            english = "No man is free who is not master of himself.",
            japanese = "自分自身の主人でない人は自由ではありません。"
        ),
        Quote(
            english = "Those that know, do. Those that understand, teach.",
            japanese = "知っている人は知っています。分かる人は教える。"
        ),
        Quote(
            english = "If we are not fully ourselves, truly in the present moment, we miss everything.",
            japanese = "もし私たちが、本当に今この瞬間において、完全に自分自身になっていないと、すべてを見逃してしまいます。"
        ),
        Quote(
            english = "No act of kindness, no matter how small, is ever wasted.",
            japanese = "どんなに小さな親切な行為でも、決して無駄にはなりません。"
        ),
        Quote(
            english = "Every man is a volume if you know how to read him.",
            japanese = "彼の読み方を知っていれば、どんな人でも一冊の本になります。"
        ),
        Quote(
            english = "The difficulties of life are intended to make us better, not bitter.",
            japanese = "人生の困難は私たちを良くするためのものであり、苦いものではありません。"
        ),
        Quote(
            english = "Quality means doing it right when no one is looking.",
            japanese = "品質とは、誰も見ていないところで正しく行うことです。"
        ),
        Quote(
            english = "Change your words. Change your world.",
            japanese = "言葉を変えてください。あなたの世界を変えましょう。"
        ),
        Quote(
            english = "Great acts are made up of small deeds.",
            japanese = "偉大な行為は小さな行為から成り立ちます。"
        ),
        Quote(
            english = "The odds of hitting your target go up dramatically when you aim at it.",
            japanese = "ターゲットを狙うと命中率が大幅に上がります。"
        ),
        Quote(
            english = "Open minds lead to open doors.",
            japanese = "心を開くことは扉を開くことにつながります。"
        ),
        Quote(
            english = "They can do all because they think they can.",
            japanese = "彼らはできると思っているので、すべてを行うことができます。"
        ),
        Quote(
            english = "You have to think anyway, so why not think big?",
            japanese = "とにかく考えなければならないので、大きく考えてみませんか?"
        ),
        Quote(
            english = "On every thorn, delightful wisdom grows, In every rill a sweet instruction flows.",
            japanese = "あらゆる棘には楽しい知恵が芽生え、あらゆる小川には甘美な教えが流れる。"
        ),
        Quote(
            english = "Your body is precious. It is our vehicle for awakening. Treat it with care.",
            japanese = "あなたの体は貴重です。それは私たちが目覚めるための手段です。大切に扱ってください。"
        ),
        Quote(
            english = "The one who always loses, is the only person who gets the reward.",
            japanese = "常に負けている人が、報酬を得る唯一の人です。"
        ),
        Quote(
            english = "The future is completely open, and we are writing it moment to moment.",
            japanese = "未来は完全に開かれており、私たちはそれを瞬間瞬間に書いています。"
        ),
        Quote(
            english = "Each time we face a fear, we gain strength, courage, and confidence in the doing.",
            japanese = "恐怖に直面するたびに、私たちは強さ、勇気、そして実行する自信を獲得します。"
        ),
        Quote(
            english = "Ask yourself the secret of your success. Listen to your answer, and practice it.",
            japanese = "成功の秘訣を自分自身に問いかけてください。答えを聞いて、練習してください。"
        ),
        Quote(
            english = "Don't frown because you never know who is falling in love with your smile.",
            japanese = "誰があなたの笑顔に恋をするかわからないからといって、眉をひそめないでください。"
        ),
        Quote(
            english = "Trust your hunches. They're usually based on facts filed away just below the conscious level.",
            japanese = "自分の直感を信じてください。それらは通常、意識レベルのすぐ下にファイルされた事実に基づいています。"
        ),
        Quote(
            english = "Nothing is at last sacred but the integrity of your own mind.",
            japanese = "結局のところ、あなた自身の心の誠実さ以外に神聖なものはありません。"
        ),
        Quote(
            english = "Listen to your intuition. It will tell you everything you need to know.",
            japanese = "自分の直感に耳を傾けてください。知っておくべきことはすべて教えてくれます。"
        ),
        Quote(
            english = "The personal life deeply lived always expands into truths beyond itself.",
            japanese = "深く生きた個人的な人生は、常にそれ自体を超えた真実へと広がります。"
        ),
        Quote(
            english = "Whenever something negative happens to you, there is a deep lesson concealed within it.",
            japanese = "何かネガティブなことがあなたに起こるたびに、その中には深い教訓が隠されています。"
        ),
        Quote(
            english = "What is not started today is never finished tomorrow.",
            japanese = "今日始められないことは、明日も終わることはありません。"
        ),
        Quote(
            english = "Our kindness may be the most persuasive argument for that which we believe.",
            japanese = "私たちの優しさは、私たちが信じていることに対する最も説得力のある議論かもしれません。"
        ),
        Quote(
            english = "Chaos is inherent in all compounded things. Strive on with diligence.",
            japanese = "カオスはあらゆる複合的なものに内在しています。コツコツと努力してください。"
        ),
        Quote(
            english = "Be sure you put your feet in the right place, then stand firm.",
            japanese = "足を正しい位置に置き、しっかりと立っていることを確認してください。"
        ),
        Quote(
            english = "Nothing great was ever achieved without enthusiasm.",
            japanese = "熱意がなければ偉大なことは何も達成されませんでした。"
        ),
        Quote(
            english = "The meaning I picked, the one that changed my life: Overcome fear, behold wonder.",
            japanese = "私が選んだ意味、私の人生を変えたもの：恐怖を克服し、驚きを見てください。"
        ),
        Quote(
            english = "Know how to listen, and you will profit even from those who talk badly.",
            japanese = "聞き方を知っていれば、たとえ悪く言う人からも利益を得ることができます。"
        ),
        Quote(
            english = "A man is not old as long as he is seeking something.",
            japanese = "人は何かを求めている限り年を取らない。"
        ),
        Quote(
            english = "The time you think you're missing, misses you too.",
            japanese = "あなたが恋しいと思う時間は、あなたも恋しいのです。"
        ),
        Quote(
            english = "Life is not measured by the breaths you take, but by its breathtaking moments.",
            japanese = "人生は呼吸によって測られるのではなく、息を呑むような瞬間によって測られます。"
        ),
        Quote(
            english = "Much wisdom often goes with fewer words.",
            japanese = "多くの知恵は、多くの場合、少ない言葉で実現されます。"
        ),
        Quote(
            english = "If you love life, don't waste time, for time is what life is made up of.",
            japanese = "人生を愛しているなら、時間を無駄にしないでください。人生は時間で構成されているからです。"
        ),
        Quote(
            english = "Imagination is the living power and prime agent of all human perception.",
            japanese = "想像力は生きた力であり、人間のすべての認識の主要なエージェントです。"
        ),
        Quote(
            english = "It is impossible to feel grateful and depressed in the same moment.",
            japanese = "感謝と憂鬱を同時に感じることは不可能です。"
        ),
        Quote(
            english = "Progress always involves risks. You can't steal second base and keep your foot on first.",
            japanese = "進歩には常にリスクが伴います。一塁に足を置いて二塁を盗むことはできません。"
        ),
        Quote(
            english = "Liberty, taking the word in its concrete sense, consists in the ability to choose.",
            japanese = "自由とは、この言葉を具体的な意味で解釈すると、選択する能力にあります。"
        ),
        Quote(
            english = "A thing well said will be wit in all languages.",
            japanese = "よく言われたことは、どの言語でも機知に富んだものになります。"
        ),
        Quote(
            english = "Always do your best. What you plant now, you will harvest later.",
            japanese = "常に最善を尽くしてください。今植えるものは、後で収穫することになります。"
        ),
        Quote(
            english = "My mama always said: life's like a box of chocolate, you never know what you gonna get.",
            japanese = "私のママはいつもこう言っていました、「人生はチョコレートの箱のようなもの、何が手に入るか分からない。」"
        ),
        Quote(
            english = "We are the leaves of one branch, the drops of one sea, the flowers of one garden.",
            japanese = "私たちは一つの枝の葉、一つの海のしずく、一つの庭の花です。"
        ),
        Quote(
            english = "If you come to a fork in the road, take it.",
            japanese = "道の分岐点に来たら、道を進んでください。"
        ),
        Quote(
            english = "It is not only for what we do that we are held responsible, but also for what we do not do.",
            japanese = "私たちが責任を負うのは、私たちが行ったことに対してだけでなく、私たちが行わなかったことに対しても責任を負うということです。"
        ),
        Quote(
            english = "Nobody can do everything, but everybody can do something.",
            japanese = "すべてをできる人はいませんが、誰もが何かをすることはできます。"
        ),
        Quote(
            english = "You cannot step twice into the same river, for other waters are continually flowing in.",
            japanese = "他の水が絶えず流れ込んでいるので、同じ川に二度入ることはできません。"
        ),
        Quote(
            english = "Excellence is to do a common thing in an uncommon way.",
            japanese = "卓越性とは、一般的なことを珍しい方法で行うことです。"
        ),
        Quote(
            english = "No matter how hard the past, you can always begin again.",
            japanese = "éå»ããããè¾ãã¦ãããã¤ã§ãããç´ããã"
        ),
        Quote(
            english = "I begin with an idea and then it becomes something else.",
            japanese = "私はアイデアから始めますが、その後、それは別のものになります。"
        ),
        Quote(
            english = "Whoever is happy will make others happy, too.",
            japanese = "幸せな人は他の人も幸せにします。"
        ),
        Quote(
            english = "Your work is to discover your work and then with all your heart to give yourself to it.",
            japanese = "あなたの仕事は、自分の仕事を発見し、心からそれに身を捧げることです。"
        ),
        Quote(
            english = "It's not what happens to you, but how you react to it that matters.",
            japanese = "あなたに何が起こるかではなく、それに対してあなたがどのように反応するかが重要です。"
        ),
        Quote(
            english = "Take it easy, but take it.",
            japanese = "ゆっくりしてください。"
        ),
        Quote(
            english = "Never apologize for showing feeling. When you do so, you apologize for truth.",
            japanese = "感情を表したことを決して謝らないでください。そうするとき、あなたは真実を謝罪します。"
        ),
        Quote(
            english = "Take rest; a field that has rested gives a bountiful crop.",
            japanese = "休んでください。休んだ畑は豊かな実りをもたらします。"
        ),
        Quote(
            english = "Age does not protect you from love. But love, to some extent, protects you from age.",
            japanese = "年齢はあなたを愛から守ってくれません。しかし、愛はある程度、年齢からあなたを守ってくれます。"
        ),
        Quote(
            english = "Do what you can. Want what you have. Be who you are.",
            japanese = "できることはやってください。持っているものを欲しがります。ありのままの自分でいてください。"
        ),
        Quote(
            english = "There are people who have money and people who are rich.",
            japanese = "お金を持っている人もいれば、裕福な人もいます。"
        ),
        Quote(
            english = "Why worry about tomorrow, when today is all we have?",
            japanese = "今日しかないのに、なぜ明日のことを心配するのでしょうか？"
        ),
        Quote(
            english = "Speak when you are angry and you will make the best speech you will ever regret.",
            japanese = "怒っているときに話せば、後悔しない最高のスピーチができるでしょう。"
        ),
        Quote(
            english = "Things do not change, we change.",
            japanese = "物事が変わるのではなく、私たちが変わるのです。"
        ),
        Quote(
            english = "The exercise of an extraordinary gift is the supremest pleasure in life.",
            japanese = "並外れた才能を発揮することは、人生における最高の喜びです。"
        ),
        Quote(
            english = "Sometimes the most important thing in a whole day is the rest we take between two deep breaths.",
            japanese = "場合によっては、1 日の中で最も重要なことは、2 回の深呼吸の間に取る休息です。"
        ),
        Quote(
            english = "Forgiveness is choosing to love. It is the first skill of self-giving love.",
            japanese = "許しとは愛することを選択することです。それは自己を与える愛の最初のスキルです。"
        ),
        Quote(
            english = "Most smiles are started by another smile.",
            japanese = "ほとんどの笑顔は、別の笑顔によって始まります。"
        ),
        Quote(
            english = "Nothing is softer or more flexible than water, yet nothing can resist it.",
            japanese = "水ほど柔らかく柔軟なものはありませんが、水に抵抗できるものはありません。"
        ),
        Quote(
            english = "Experience keeps a dear school, but fools will learn in no other.",
            japanese = "経験は貴重な学校を維持しますが、愚か者は他の学校では学びません。"
        ),
        Quote(
            english = "We can only be said to be alive in those moments when our hearts are conscious of our treasures.",
            japanese = "私たちの心が自分の宝物を意識している瞬間にのみ、私たちは生きていると言えます。"
        ),
        Quote(
            english = "Fine words and an insinuating appearance are seldom associated with true virtue",
            japanese = "立派な言葉やほのめかした態度が真の美徳と結びつくことはほとんどありません"
        ),
        Quote(
            english = "We do not quit playing because we grow old, we grow old because we quit playing.",
            japanese = "私たちは年をとったから遊びをやめるのではなく、遊びをやめたから年をとるのです。"
        ),
        Quote(
            english = "You can't choose up sides on a round world.",
            japanese = "丸い世界ではどちら側を選ぶこともできません。"
        ),
        Quote(
            english = "Kindness is the language which the deaf can hear and the blind can see.",
            japanese = "優しさは、耳の聞こえない人には聞こえ、目の見えない人には見える言葉です。"
        ),
        Quote(
            english = "I may not know everything, but everything is not known yet anyway.",
            japanese = "すべてを知っているわけではないかもしれないが、とにかくまだすべてがわかっていない。"
        ),
        Quote(
            english = "If we could see the miracle of a single flower clearly, our whole life would change.",
            japanese = "一輪の花の奇跡をはっきりと見ることができたら、私たちの人生は変わるでしょう。"
        ),
        Quote(
            english = "You cannot travel the path until you have become the path itself.",
            japanese = "あなたが道そのものになるまで、道を進むことはできません。"
        ),
        Quote(
            english = "Keep your eyes on the stars and your feet on the ground.",
            japanese = "æã«ç®ãåããå°ã«è¶³ãã¤ããã"
        ),
        Quote(
            english = "I am not afraid of tomorrow, for I have seen yesterday and I love today.",
            japanese = "私は明日を恐れていません、なぜなら私は昨日を見て今日を愛しているからです。"
        ),
        Quote(
            english = "When you lose, don't lose the lesson.",
            japanese = "負けても教訓を失わないでください。"
        ),
        Quote(
            english = "If you want a thing done well, do it yourself.",
            japanese = "何かをうまくやり遂げたいなら、自分でやりなさい。"
        ),
        Quote(
            english = "The greatest barrier to success is the fear of failure.",
            japanese = "成功への最大の障壁は失敗への恐れです。"
        ),
        Quote(
            english = "If you aren't going all the way, why go at all?",
            japanese = "ずっと行かないなら、そもそもなぜ行くのでしょうか？"
        ),
        Quote(
            english = "Our greatest glory is not in never falling, but in rising every time we fall.",
            japanese = "私たちの最大の栄光は、決して倒れないことではなく、倒れるたびに立ち上がることにあります。"
        ),
        Quote(
            english = "Stay committed to your decisions, but stay flexible in your approach.",
            japanese = "自分の決定には忠実であり続けますが、アプローチは柔軟であり続けてください。"
        ),
        Quote(
            english = "What separates the winners from the losers is how a person reacts to each new twist of fate.",
            japanese = "勝者と敗者を分けるのは、運命の新たな展開に対して人がどのように反応するかです。"
        ),
        Quote(
            english = "To change ones life, start immediately, do it flamboyantly, no exceptions.",
            japanese = "人生を変えるには、例外なく、すぐに始めて、派手に実行してください。"
        ),
        Quote(
            english = "The steeper the mountain the harder the climb the better the view from the finishing line",
            japanese = "山の傾斜が急であればあるほど、登りが難しくなり、ゴールラインからの眺めは良くなります"
        ),
        Quote(
            english = "When I let go of what I am, I become what I might be.",
            japanese = "ありのままの自分を手放すとき、私はあり得る自分になるのです。"
        ),
        Quote(
            english = "When there is no enemy within, the enemies outside cannot hurt you.",
            japanese = "内側に敵がいなければ、外側の敵はあなたを傷つけることはできません。"
        ),
        Quote(
            english = "He who controls others may be powerful, but he who has mastered himself is mightier still.",
            japanese = "他者をコントロールする者は強力かもしれないが、自分自身を制御する者はさらに強力である。"
        ),
        Quote(
            english = "As an organizer I start from where the world is, as it is, not as I would like it to be.",
            japanese = "オーガナイザーとして、私は世界が私が望むものではなく、現状であるところからスタートします。"
        ),
        Quote(
            english = "You are the only person on Earth who can use your ability.",
            japanese = "あなたの能力を使用できるのは、地球上であなただけです。"
        ),
        Quote(
            english = "Don't let what you can't do stop you from doing what you can do.",
            japanese = "できないことを理由に、できることをやめないようにしてください。"
        ),
        Quote(
            english = "Complaining doesn't change a thing only taking action does.",
            japanese = "不平を言っても何も変わらないのは、行動を起こすだけです。"
        ),
        Quote(
            english = "Enjoy the little things, for one day you may look back and realize they were the big things.",
            japanese = "小さなことを楽しんでください。ある日、振り返ってみると、それが大きなことであったことに気づくかもしれません。"
        ),
        Quote(
            english = "With every experience, you alone are painting your own canvas, thought by thought, choice by choice.",
            japanese = "あらゆる経験において、あなただけが、考えに考え、選択に選択を繰り返しながら、自分自身のキャンバスを描いています。"
        ),
        Quote(
            english = "Let the beauty of what you love be what you do.",
            japanese = "自分の好きなことの美しさを自分の仕事にしましょう。"
        ),
        Quote(
            english = "The world turns aside to let any man pass who knows where he is going.",
            japanese = "世界は、自分がどこに行くのかを知っている人を追い越すために脇を向きます。"
        ),
        Quote(
            english = "Beauty is not in the face; beauty is a light in the heart.",
            japanese = "美しさは顔にあるのではありません。美は心の光です。"
        ),
        Quote(
            english = "A day of worry is more exhausting than a day of work.",
            japanese = "心配する一日は、仕事をする一日よりも疲れます。"
        ),
        Quote(
            english = "Truth, and goodness, and beauty are but different faces of the same all.",
            japanese = "真実、善、美は、すべて同じものの異なる側面にすぎません。"
        ),
        Quote(
            english = "To be great is to be misunderstood.",
            japanese = "偉大であるということは誤解されるということだ。"
        ),
        Quote(
            english = "Trust only movement. Life happens at the level of events, not of words. Trust movement.",
            japanese = "動きだけを信じてください。人生は言葉ではなく出来事のレベルで起こります。信頼の動き。"
        ),
        Quote(
            english = "Never, never, never give up.",
            japanese = "決して、決して、決して諦めないでください。"
        ),
        Quote(
            english = "The most decisive actions of our life... are most often unconsidered actions.",
            japanese = "私たちの人生で最も決定的な行動は、ほとんどの場合、考慮されていない行動です。"
        ),
        Quote(
            english = "As we grow as unique persons, we learn to respect the uniqueness of others.",
            japanese = "私たちはユニークな人間として成長するにつれて、他の人のユニークさを尊重することを学びます。"
        ),
        Quote(
            english = "Failure doesn't mean you are a failure it just means you haven't succeeded yet.",
            japanese = "失敗はあなたが失敗者であることを意味するのではなく、あなたがまだ成功していないことを意味します。"
        ),
        Quote(
            english = "It is the quality of our work which will please God, not the quantity.",
            japanese = "神を喜ばせるのは私たちの仕事の量ではなく、その質です。"
        ),
        Quote(
            english = "Try and fail, but don't fail to try.",
            japanese = "試して失敗することもありますが、挑戦することを忘れないでください。"
        ),
        Quote(
            english = "First say to yourself what you would be; and then do what you have to do.",
            japanese = "まず自分がどうなるかを自分に言い聞かせてください。そして、やるべきことをやります。"
        ),
        Quote(
            english = "Keep silence for the most part, and speak only when you must, and then briefly.",
            japanese = "ほとんどの場合は沈黙を保ち、必要な場合にのみ、短く話してください。"
        ),
        Quote(
            english = "Fear not for the future, weep not for the past.",
            japanese = "未来を恐れるな、過去を泣くな。"
        ),
        Quote(
            english = "We are Divine enough to ask and we are important enough to receive.",
            japanese = "私たちは求めることができるほど神聖であり、受け取ることができるほど重要な存在でもあります。"
        ),
        Quote(
            english = "If you kick a stone in anger, you'll hurt your own foot.",
            japanese = "怒りに任せて石を蹴ると自分の足を痛めてしまいます。"
        ),
        Quote(
            english = "To see things in the seed, that is genius.",
            japanese = "物事を種の中に見るというのは、天才です。"
        ),
        Quote(
            english = "Make the most of yourself, for that is all there is of you.",
            japanese = "自分を最大限に活用しましょう。それがあなたのすべてなのですから。"
        ),
        Quote(
            english = "The universe is made of stories, not atoms.",
            japanese = "宇宙は原子ではなく物語でできています。"
        ),
        Quote(
            english = "Respect should be earned by actions, and not acquired by years.",
            japanese = "尊敬は行動によって獲得されるべきであり、年月によって獲得されるものではありません。"
        ),
        Quote(
            english = "I hear and I forget. I see and I remember. I do and I understand.",
            japanese = "聞いても忘れてしまいます。見て覚えています。私もそう思いますし、理解しています。"
        ),
        Quote(
            english = "Sometimes the biggest act of courage is a small one.",
            japanese = "時には、最大の勇気ある行動が小さな行動であることもあります。"
        ),
        Quote(
            english = "People are not lazy. They simply have impotent goals, that is, goals that do not inspire them.",
            japanese = "人々は怠け者ではありません。彼らは単に無力な目標、つまりやる気を起こさない目標を持っているだけです。"
        ),
        Quote(
            english = "You do not become good by trying to be good, but by finding the goodness that is already within you.",
            japanese = "善人になろうとすることで善人になるのではなく、すでに自分の中にある善性を見つけることによって人は善人になれるのです。"
        ),
        Quote(
            english = "Waste no more time arguing about what a good man should be. Be one.",
            japanese = "善良な人間とはどうあるべきかについて議論して時間を無駄にする必要はありません。一つになってください。"
        ),
        Quote(
            english = "Happiness often sneaks in through a door you didn't know you left open.",
            japanese = "幸せは、開けっ放しにしていた知らないドアから忍び込んでくることがよくあります。"
        ),
        Quote(
            english = "The things that one most wants to do are the things that are probably most worth doing.",
            japanese = "人が最もやりたいことは、おそらく最も価値のあることです。"
        ),
        Quote(
            english = "Always bear in mind that your own resolution to succeed is more important than any one thing.",
            japanese = "成功するという自分自身の決意が何よりも重要であることを常に心に留めておいてください。"
        ),
        Quote(
            english = "Setting an example is not the main means of influencing another, it is the only means.",
            japanese = "模範を示すことは、他の人に影響を与える主な手段ではなく、唯一の手段です。"
        ),
        Quote(
            english = "Chaos and Order are not enemies, only opposites.",
            japanese = "混沌と秩序は敵ではなく、対立するものにすぎません。"
        ),
        Quote(
            english = "He who deliberates fully before taking a step will spend his entire life on one leg.",
            japanese = "一歩を踏み出す前に十分に熟慮する人は、一生を片足で過ごすことになるでしょう。"
        ),
        Quote(
            english = "Peace begins with a smile.",
            japanese = "平和は笑顔から始まります。"
        ),
        Quote(
            english = "Be your own hero, it's cheaper than a movie ticket.",
            japanese = "映画のチケットより安いので、自分だけのヒーローになりましょう。"
        ),
        Quote(
            english = "Turn your face toward the sun and the shadows will fall behind you.",
            japanese = "顔を太陽に向けると、影が後ろに落ちます。"
        ),
        Quote(
            english = "Things turn out best for those who make the best of the way things turn out.",
            japanese = "物事がうまくいくように最善を尽くした人にとって、物事は最善の結果をもたらします。"
        ),
        Quote(
            english = "Ability will never catch up with the demand for it.",
            japanese = "需要に能力が追いつくことはありません。"
        ),
        Quote(
            english = "Setting goals is the first step in turning the invisible into the visible.",
            japanese = "目標を設定することは、目に見えないものを目に見えるものに変える第一歩です。"
        ),
        Quote(
            english = "Courage is not the absence of fear, but simply moving on with dignity despite that fear.",
            japanese = "勇気とは、恐れがないことではなく、恐れにもかかわらず、単に尊厳を持って前進することです。"
        ),
        Quote(
            english = "All truths are easy to understand once they are discovered; the point is to discover them.",
            japanese = "すべての真実は、一度発見されれば容易に理解できます。重要なのはそれらを発見することです。"
        ),
        Quote(
            english = "The smallest act of kindness is worth more than the grandest intention.",
            japanese = "最も小さな親切な行為は、最も壮大な意図よりも価値があります。"
        ),
        Quote(
            english = "We know from science that nothing in the universe exists as an isolated or independent entity.",
            japanese = "私たちは科学から、宇宙には孤立した、または独立した存在として存在するものは何もないことを知っています。"
        ),
        Quote(
            english = "Everything in the universe goes by indirection. There are no straight lines.",
            japanese = "宇宙のすべては間接的に進行します。直線はありません。"
        ),
        Quote(
            english = "What do we live for, if it is not to make life less difficult for each other?",
            japanese = "お互いの人生の困難を軽減するためでなければ、私たちは何のために生きているのでしょうか？"
        ),
        Quote(
            english = "We may encounter many defeats but we must not be defeated.",
            japanese = "私たちは多くの敗北に遭遇するかもしれませんが、負けてはなりません。"
        ),
        Quote(
            english = "Logic will get you from A to B. Imagination will take you everywhere.",
            japanese = "論理があなたを A から B に連れて行きます。想像力があなたをどこにでも連れて行ってくれます。"
        ),
        Quote(
            english = "The world is but a canvas to the imagination.",
            japanese = "世界は想像力のキャンバスにすぎません。"
        ),
        Quote(
            english = "To be happy is to be able to become aware of oneself without fright.",
            japanese = "幸せであるということは、恐れることなく自分自身を認識できることです。"
        ),
        Quote(
            english = "Strength to carry on despite the odds means you have faith in your own abilities and know how.",
            japanese = "困難にもかかわらずやり続ける強さは、自分の能力とノウハウを信じていることを意味します。"
        ),
        Quote(
            english = "Make the most of yourself for that is all there is of you.",
            japanese = "それがあなたのすべてなのですから、自分を最大限に活用してください。"
        ),
        Quote(
            english = "Be gentle first with yourself if you wish to be gentle with others.",
            japanese = "他人に対して優しくなりたいなら、まず自分に対して優しくなりましょう。"
        ),
        Quote(
            english = "A man who doesn't trust himself can never really trust anyone else.",
            japanese = "自分自身を信頼できない人は、他人を心から信頼することはできません。"
        ),
        Quote(
            english = "We make our own fortunes and we call them fate.",
            japanese = "私たちは自分の財産を自分で作り、それを運命と呼びます。"
        ),
        Quote(
            english = "It takes courage to grow up and become who you really are.",
            japanese = "大人になって本当の自分になるには勇気が必要です。"
        ),
        Quote(
            english = "Always seek out the seed of triumph in every adversity.",
            japanese = "あらゆる逆境の中でも常に勝利の種を探し求めてください。"
        ),
        Quote(
            english = "Rather than wishing for change, you first must be prepared to change.",
            japanese = "変化を望むのではなく、まず変化する準備をしなければなりません。"
        ),
        Quote(
            english = "Fame usually comes to those who are thinking about something else.",
            japanese = "名声は通常、別のことを考えている人に与えられます。"
        ),
        Quote(
            english = "The superior man acts before he speaks, and afterwards speaks according to his action.",
            japanese = "目上の人は話す前に行動し、その後はその行動に応じて話す。"
        ),
        Quote(
            english = "A single conversation across the table with a wise person is worth a months study of books.",
            japanese = "テーブルを挟んで賢い人とたった一回会話するだけでも、一ヶ月かけて本を読むのに匹敵します。"
        ),
        Quote(
            english = "You can never cross the ocean unless you have the courage to lose sight of the shore.",
            japanese = "岸を見失わない勇気がなければ、海を渡ることはできません。"
        ),
        Quote(
            english = "Work for something because it is good, not just because it stands a chance to succeed.",
            japanese = "単に成功するチャンスがあるからではなく、それが良いから何かのために働くのです。"
        ),
        Quote(
            english = "Knowledge rests not upon truth alone, but upon error also.",
            japanese = "知識は真実のみに基づいているのではなく、誤りにも基づいています。"
        ),
        Quote(
            english = "Never regret. If it's good, it's wonderful. If it's bad, it's experience.",
            japanese = "決して後悔しないでください。それが良いものであれば、それは素晴らしいことです。悪くてもそれは経験です。"
        ),
        Quote(
            english = "When deeds and words are in accord, the whole world is transformed.",
            japanese = "行為と言葉が一致すると、世界全体が変わります。"
        ),
        Quote(
            english = "Kind words can be short and easy to speak but their echoes are truly endless.",
            japanese = "優しい言葉は短くて簡単に言えますが、その反響は本当に無限です。"
        ),
        Quote(
            english = "For everything that lives is holy, life delights in life.",
            japanese = "生きるものはすべて神聖であり、人生は人生を喜びます。"
        ),
        Quote(
            english = "Our passion is our strength.",
            japanese = "私たちの情熱は私たちの強みです。"
        ),
        Quote(
            english = "Spring is a time for rebirth and the fulfilment of new life.",
            japanese = "春は生まれ変わり、新しい人生が充実する季節です。"
        ),
        Quote(
            english = "There is nothing happens to any person but what was in his power to go through with.",
            japanese = "どんな人にも、その人の力で乗り越えられること以外には何も起こりません。"
        ),
        Quote(
            english = "The art of progress is to preserve order amid change, and to preserve change amid order.",
            japanese = "進歩の芸術とは、変化の中でも秩序を維持し、秩序の中でも変化を維持することです。"
        ),
        Quote(
            english = "We make a living by what we get, but we make a life by what we give.",
            japanese = "私たちは得るものによって生計を立てていますが、与えるものによって人生を作ります。"
        ),
        Quote(
            english = "Maxim for life: You get treated in life the way you teach people to treat you.",
            japanese = "人生の格言: 人生において、あなたは人々にあなたへの扱い方を教えたように扱われます。"
        ),
        Quote(
            english = "The key to growth is the introduction of higher dimensions of consciousness into our awareness.",
            japanese = "成長の鍵は、より高い次元の意識を私たちの意識に導入することです。"
        ),
        Quote(
            english = "Thought is the blossom; language the bud; action the fruit behind it.",
            japanese = "思考は花です。言語は芽。その背後にある果物をアクションさせます。"
        ),
        Quote(
            english = "There is nothing so useless as doing efficiently that which should not be done at all.",
            japanese = "本来やるべきではないことを効率的に行うことほど無駄なことはありません。"
        ),
        Quote(
            english = "As we are liberated from our own fear, our presence automatically liberates others.",
            japanese = "私たちが自分自身の恐怖から解放されると、私たちの存在は自動的に他の人を解放します。"
        ),
        Quote(
            english = "The most successful people are those who are good at plan B.",
            japanese = "最も成功している人は、プラン B に優れている人です。"
        ),
        Quote(
            english = "Criticism is something you can easily avoid by saying nothing, doing nothing, and being nothing.",
            japanese = "批判は、何も言わず、何もせず、何もしないことで簡単に回避できるものです。"
        ),
        Quote(
            english = "To fly as fast as thought, you must begin by knowing that you have already arrived.",
            japanese = "思ったほど速く飛ぶには、自分がすでに到着していることを知ることから始めなければなりません。"
        ),
        Quote(
            english = "Obstacles are those things you see when you take your eyes off the goal.",
            japanese = "障害物とは、ゴールから目を離したときに目に入るもののことです。"
        ),
        Quote(
            english = "Great ideas often receive violent opposition from mediocre minds.",
            japanese = "優れたアイデアは、凡庸な人々から激しい反対を受けることがよくあります。"
        ),
        Quote(
            english = "We can change our lives. We can do, have, and be exactly what we wish.",
            japanese = "私たちは人生を変えることができます。私たちはまさに自分が望んでいることを行い、持つことができ、そしてそうなることができます。"
        ),
        Quote(
            english = "You are the only person on earth who can use your ability.",
            japanese = "あなたの能力を使用できるのは、地球上であなただけです。"
        ),
        Quote(
            english = "Neither genius, fame, nor love show the greatness of the soul. Only kindness can do that.",
            japanese = "天才も名声も愛も魂の偉大さを表わすものではない。それができるのは優しさだけです。"
        ),
        Quote(
            english = "The least of things with a meaning is worth more in life than the greatest of things without it.",
            japanese = "人生においては、意味のある最も小さなもののほうが、意味のない最も偉大なものよりも価値があります。"
        ),
        Quote(
            english = "The noblest worship is to make yourself as good and as just as you can.",
            japanese = "最も崇高な崇拝とは、自分自身をできる限り善く正しくすることです。"
        ),
        Quote(
            english = "Don't think of it as failure. Think of it as time-released success.",
            japanese = "それを失敗だと考えないでください。それは時間をかけて解放された成功だと考えてください。"
        ),
        Quote(
            english = "We are what we repeatedly do. Excellence, then, is not an act but a habit.",
            japanese = "私たちは繰り返し行っていることそのものなのです。つまり、卓越性とは行為ではなく習慣なのです。"
        ),
        Quote(
            english = "I walk slowly, but I never walk backward.",
            japanese = "私はゆっくり歩きますが、決して後ろ向きには歩きません。"
        ),
        Quote(
            english = "Divide each difficulty into as many parts as is feasible and necessary to resolve it.",
            japanese = "それぞれの困難を、解決するために実行可能かつ必要なだけ多くの部分に分割します。"
        ),
        Quote(
            english = "The best place to find a helping hand is at the end of your own arm.",
            japanese = "救いの手を見つけるのに最適な場所は、自分の腕の先です。"
        ),
        Quote(
            english = "We know the truth, not only by the reason, but by the heart.",
            japanese = "私たちは真実を理由だけでなく心でも知っています。"
        ),
        Quote(
            english = "We choose our joys and sorrows long before we experience them.",
            japanese = "私たちは喜びや悲しみを経験するずっと前にそれを選択します。"
        ),
        Quote(
            english = "Anybody can make history. Only a great man can write it.",
            japanese = "誰でも歴史を作ることができます。偉大な人だけがそれを書くことができます。"
        ),
        Quote(
            english = "If I know what love is, it is because of you.",
            japanese = "もし私が愛が何であるかを知っているとしたら、それはあなたのおかげです。"
        ),
        Quote(
            english = "Allow the world to live as it chooses, and allow yourself to live as you choose.",
            japanese = "世界が望むように生きることを許可し、あなた自身が選択するように生きることを許可してください。"
        ),
        Quote(
            english = "Compassion and happiness are not a sign of weakness but a sign of strength.",
            japanese = "思いやりと幸福は弱さのしるしではなく、強さのしるしです。"
        ),
        Quote(
            english = "Be here now. Be someplace else later. Is that so complicated?",
            japanese = "今ここに来てください。後で別の場所に行ってください。それはそんなに複雑ですか？"
        ),
        Quote(
            english = "Learning without reflection is a waste, reflection without learning is dangerous.",
            japanese = "反省のない学習は無駄であり、学習のない反省は危険です。"
        ),
        Quote(
            english = "I don't believe in failure. It's not failure if you enjoyed the process.",
            japanese = "私は失敗を信じません。その過程を楽しめたなら、それは失敗ではありません。"
        ),
        Quote(
            english = "The man who trusts men will make fewer mistakes than he who distrusts them.",
            japanese = "男性を信頼する人は、男性を信頼しない人よりも間違いを犯しません。"
        ),
        Quote(
            english = "The less effort, the faster and more powerful you will be.",
            japanese = "努力が減れば減るほど、より速く、より強力になります。"
        ),
        Quote(
            english = "The cure for boredom is curiosity. There is no cure for curiosity.",
            japanese = "退屈を治すのは好奇心です。好奇心を治す方法はありません。"
        ),
        Quote(
            english = "We can do no great things, only small things with great love.",
            japanese = "私たちにできるのは大きなことではなく、大きな愛を持って小さなことだけです。"
        ),
        Quote(
            english = "Be like the flower, turn your face to the sun.",
            japanese = "花のように、太陽に顔を向けてください。"
        ),
        Quote(
            english = "Remembering a wrong is like carrying a burden on the mind.",
            japanese = "間違いを思い出すことは心に重荷を負っているようなものです。"
        ),
        Quote(
            english = "The foolish man seeks happiness in the distance; the wise grows it under his feet.",
            japanese = "愚かな人は遠くに幸福を求めます。賢い人はそれを自分の足元で育てます。"
        ),
        Quote(
            english = "Gratitude is the fairest blossom which springs from the soul.",
            japanese = "感謝は魂から湧き出る最も美しい花です。"
        ),
        Quote(
            english = "You cannot have what you do not want.",
            japanese = "欲しくないものを手に入れることはできません。"
        ),
        Quote(
            english = "Do not follow where the path may lead. Go, instead, where there is no path and leave a trail.",
            japanese = "道が続く可能性がある場所には従わないでください。代わりに、道のない場所に行き、跡を残してください。"
        ),
        Quote(
            english = "It is not fair to ask of others what you are unwilling to do yourself.",
            japanese = "自分がやりたくないことを他人に求めるのは不公平です。"
        ),
        Quote(
            english = "Knowing your own darkness is the best method for dealing with the darknesses of other people.",
            japanese = "自分の闇を知ることは、他人の闇に対処する最良の方法です。"
        ),
        Quote(
            english = "The best thing in every noble dream is the dreamer...",
            japanese = "すべての崇高な夢の中で最も優れているのは夢想家です..."
        ),
        Quote(
            english = "Weve got to have a dream if we are going to make a dream come true.",
            japanese = "夢を実現するには、夢を持つ必要があります。"
        ),
        Quote(
            english = "If you want things to be different, perhaps the answer is to become different yourself.",
            japanese = "物事を違うものにしたいなら、おそらく答えは自分自身が違うことだろう。"
        ),
        Quote(
            english = "There is nothing impossible to him who will try.",
            japanese = "挑戦する彼に不可能なことは何もありません。"
        ),
        Quote(
            english = "Kindness is more important than wisdom, and the recognition of this is the beginning of wisdom.",
            japanese = "優しさは知恵よりも重要であり、これを認識することが知恵の始まりです。"
        ),
        Quote(
            english = "The only real failure in life is not to be true to the best one knows.",
            japanese = "人生における唯一の本当の失敗は、自分が知っている最善のことに忠実ではないことです。"
        ),
        Quote(
            english = "Anyone who doesn't take truth seriously in small matters cannot be trusted in large ones either.",
            japanese = "小さな事柄において真実を真剣に受け止めない人は、大きな事柄においても信頼することができません。"
        ),
        Quote(
            english = "Those who cannot learn from history are doomed to repeat it.",
            japanese = "歴史から学べない者は歴史を繰り返す運命にある。"
        ),
        Quote(
            english = "Things that were hard to bear are sweet to remember.",
            japanese = "耐え難かったことも思い出すと楽しいものです。"
        ),
        Quote(
            english = "They can conquer who believe they can.",
            japanese = "彼らは自分ができると信じる者を征服できる。"
        ),
        Quote(
            english = "Learn to listen. Opportunity could be knocking at your door very softly.",
            japanese = "聞くことを学びましょう。チャンスがあなたのドアをそっとノックしているかもしれません。"
        ),
        Quote(
            english = "All action results from thought, so it is thoughts that matter.",
            japanese = "すべての行動は思考から生じるので、重要なのは思考です。"
        ),
        Quote(
            english = "Just as much as we see in others we have in ourselves.",
            japanese = "私たちが他人の中に見るのと同じくらい、私たち自身の中にもあります。"
        ),
        Quote(
            english = "Prosperity depends more on wanting what you have than having what you want.",
            japanese = "繁栄は、欲しいものを手に入れることよりも、持っているものを欲しがることに大きく依存します。"
        ),
        Quote(
            english = "How many cares one loses when one decides not to be something but to be someone.",
            japanese = "何かではなく何者かになることを決意したとき、人はどれほどの心配を失うことだろう。"
        ),
        Quote(
            english = "He who knows, does not speak. He who speaks, does not know.",
            japanese = "知っている者は語らない。語る者は知らない。"
        ),
        Quote(
            english = "We cannot direct the wind but we can adjust the sails.",
            japanese = "風を変えることはできませんが、帆を調整することはできます。"
        ),
        Quote(
            english = "One may say the eternal mystery of the world is its comprehensibility.",
            japanese = "世界の永遠の謎はそのわかりやすさにあると言えるかもしれない。"
        ),
        Quote(
            english = "Our greatness lies not so much in being able to remake the world as being able to remake ourselves.",
            japanese = "私たちの偉大さは、世界を作り変えることができるというよりも、自分自身を作り変えることができることにあります。"
        ),
        Quote(
            english = "Moments of complete apathy are the best for new creations.",
            japanese = "新しい創作には、完全に無関心な瞬間が最適です。"
        ),
        Quote(
            english = "The only real mistake is the one from which we learn nothing.",
            japanese = "唯一の本当の間違いは、そこから何も学べないものです。"
        ),
        Quote(
            english = "To dream of the person you would like to be is to waste the person you are.",
            japanese = "なりたい自分を夢見ることは、今の自分を無駄にすることです。"
        ),
        Quote(
            english = "Gratitude is not only the greatest of virtues, but the paren't of all the others.",
            japanese = "感謝は美徳の中で最大のものであるだけでなく、他のすべての美徳の親でもありません。"
        ),
        Quote(
            english = "Things do not change; we change.",
            japanese = "物事は変わりません。私たちは変わります。"
        ),
        Quote(
            english = "We must learn our limits. We are all something, but none of us are everything.",
            japanese = "私たちは自分の限界を学ばなければなりません。私たちは皆何かを持っていますが、私たちの誰もがすべてではありません。"
        ),
        Quote(
            english = "Be a good listener. Your ears will never get you in trouble.",
            japanese = "良い聞き手になってください。耳がトラブルになることはありません。"
        ),
        Quote(
            english = "To listen well is as powerful a means of communication and influence as to talk well.",
            japanese = "よく聞くことは、上手に話すことと同じくらい強力なコミュニケーションと影響力の手段です。"
        ),
        Quote(
            english = "There is only one happiness in life, to love and be loved.",
            japanese = "人生の幸福はただ一つ、愛し愛されることです。"
        ),
        Quote(
            english = "Reason and free inquiry are the only effectual agents against error.",
            japanese = "理由と自由な問い合わせだけがエラーに対して効果的な手段です。"
        ),
        Quote(
            english = "The best cure for the body is a quiet mind.",
            japanese = "体にとって最良の治療法は心を静かにすることです。"
        ),
        Quote(
            english = "See the positive side, the potential, and make an effort.",
            japanese = "ポジティブな面、可能性を見て、努力してください。"
        ),
        Quote(
            english = "By accepting yourself and being fully what you are, your presence can make others happy.",
            japanese = "自分自身を受け入れ、ありのままの自分でいることで、あなたの存在は他の人を幸せにすることができます。"
        ),
        Quote(
            english = "Never deny a diagnosis, but do deny the negative verdict that may go with it.",
            japanese = "診断を決して否定しないでください。しかし、診断に伴う否定的な評決は否定してください。"
        ),
        Quote(
            english = "You cannot be lonely if you like the person you're alone with.",
            japanese = "一人でいる相手が好きであれば、孤独になることはありません。"
        ),
        Quote(
            english = "If you propose to speak, always ask yourself, is it true, is it necessary, is it kind.",
            japanese = "話をしようと思ったら、それは本当なのか、必要なのか、親切なのか、常に自問してください。"
        ),
        Quote(
            english = "Failure will never overtake me if my determination to succeed is strong enough.",
            japanese = "成功するという強い決意があれば、失敗は決して私を襲うことはありません。"
        ),
        Quote(
            english = "Wrinkles should merely indicate where smiles have been.",
            japanese = "しわは単に笑顔があった場所を示すものでなければなりません。"
        ),
        Quote(
            english = "Your attitude, not your aptitude, will determine your altitude.",
            japanese = "あなたの高度を決めるのは、適性ではなくあなたの態度です。"
        ),
        Quote(
            english = "Let yourself be silently drawn by the stronger pull of what you really love.",
            japanese = "あなたが本当に愛するものの強い引力に静かに引き寄せられてください。"
        ),
        Quote(
            english = "I gave my life to become the person I am right now. Was it worth it?",
            japanese = "私は今の自分になるために命を捧げました。それだけの価値はありましたか?"
        ),
        Quote(
            english = "Give thanks for a little and you will find a lot.",
            japanese = "少し感謝すれば、たくさんのことが見つかるでしょう。"
        ),
        Quote(
            english = "Your ability to learn faster than your competition is your only sustainable competitive advantage.",
            japanese = "競合他社よりも早く学習できる能力が、持続可能な唯一の競争上の優位性です。"
        ),
        Quote(
            english = "Forgiveness does not change the past, but it does enlarge the future.",
            japanese = "許しは過去を変えるものではありませんが、未来を拡大します。"
        ),
        Quote(
            english = "The deepest craving of human nature is the need to be appreciated.",
            japanese = "人間の本性の最も深い欲求は、感謝されることです。"
        ),
        Quote(
            english = "Love does not consist of gazing at each other, but in looking together in the same direction.",
            japanese = "愛はお互いを見つめ合うことではなく、一緒に同じ方向を見つめることで成り立ちます。"
        ),
        Quote(
            english = "We have committed the Golden Rule to memory; let us now commit it to life.",
            japanese = "私たちは黄金律を記憶にとどめています。さあ、それを人生にコミットしてみましょう。"
        ),
        Quote(
            english = "It is with words as with sunbeams. The more they are condensed, the deeper they burn.",
            japanese = "それは太陽光線と同じように言葉についても同様です。凝縮すればするほど、より深く燃えます。"
        ),
        Quote(
            english = "When people are like each other they tend to like each other.",
            japanese = "人はお互いに似ていると、お互いを好きになる傾向があります。"
        ),
        Quote(
            english = "Sincerity is the way of Heaven. The attainment of sincerity is the way of men.",
            japanese = "誠実は天の道なり。誠実さを達成するのが人間の道である。"
        ),
        Quote(
            english = "Be the change that you want to see in the world.",
            japanese = "あなたが世界に見たい変化になってください。"
        ),
        Quote(
            english = "The more you care, the stronger you can be.",
            japanese = "大切に思えば思うほど、あなたは強くなれるのです。"
        ),
        Quote(
            english = "Just trust yourself, then you will know how to live.",
            japanese = "ただ自分を信じてください、そうすればどう生きるべきかがわかります。"
        ),
        Quote(
            english = "To be fully alive, fully human, and completely awake is to be continually thrown out of the nest.",
            japanese = "完全に生き、完全に人間であり、完全に目覚めているということは、絶えず巣から放り出されるということだ。"
        ),
        Quote(
            english = "It all depends on how we look at things, and not how they are in themselves.",
            japanese = "それはすべて、物事そのものがどのようなものであるかではなく、私たちが物事をどのように見るかによって決まります。"
        ),
        Quote(
            english = "Giving up doesn't always mean you are weak; sometimes it means that you are strong enough to let go.",
            japanese = "諦めることは必ずしもあなたが弱いことを意味するわけではありません。時にはそれは、あなたが手放すのに十分な強さを持っていることを意味します。"
        ),
        Quote(
            english = "To climb steep hills requires a slow pace at first.",
            japanese = "急な坂を登るには、最初はゆっくりとしたペースで登る必要があります。"
        ),
        Quote(
            english = "Truth is generally the best vindication against slander.",
            japanese = "一般に、真実は中傷に対する最良の証拠となります。"
        ),
        Quote(
            english = "To follow, without halt, one aim: There is the secret of success.",
            japanese = "立ち止まることなく、ただ一つの目標を貫くこと、それが成功の秘訣です。"
        ),
        Quote(
            english = "And as we let our own light shine, we unconsciously give other people permission to do the same.",
            japanese = "そして、自分自身の光を輝かせるとき、私たちは無意識のうちに他の人にも同じようにする許可を与えます。"
        ),
        Quote(
            english = "What is a weed? A plant whose virtues have not yet been discovered.",
            japanese = "雑草とは何ですか？その長所がまだ発見されていない植物。"
        ),
        Quote(
            english = "Belief consists in accepting the affirmations of the soul; Unbelief, in denying them.",
            japanese = "信念は魂の肯定を受け入れることにあります。それらを否定することの不信仰。"
        ),
        Quote(
            english = "Many people have gone further than they thought they could because someone else thought they could.",
            japanese = "多くの人は、誰かができると思ったために、自分ができると思っていた以上のことを成し遂げてきました。"
        ),
        Quote(
            english = "We read the world wrong and say that it deceives us.",
            japanese = "私たちは世界を間違って読み、世界が私たちを欺いていると言います。"
        ),
        Quote(
            english = "If only wed stop trying to be happy wed have a pretty good time.",
            japanese = "幸せになろうとするのをやめれば、とても楽しい時間が過ごせるでしょう。"
        ),
        Quote(
            english = "You must do the things you think you cannot do.",
            japanese = "自分にはできないと思うことはやらなければなりません。"
        ),
        Quote(
            english = "Be yourself; everyone else is already taken.",
            japanese = "èªåèªèº«ã§ãããä»ã®äººã¯å¨å¡ãã§ã«åããã¦ããã"
        ),
        Quote(
            english = "Most folks are as happy as they make up their minds to be.",
            japanese = "ほとんどの人は、自分で決めたとおりに幸せです。"
        ),
        Quote(
            english = "Love is the master key that opens the gates of happiness.",
            japanese = "愛は幸福の扉を開けるマスターキーです。"
        ),
        Quote(
            english = "My reputation grows with every failure.",
            japanese = "私の評判は失敗するたびに高まります。"
        ),
        Quote(
            english = "Good thoughts are no better than good dreams, unless they be executed.",
            japanese = "良い考えは、それが実行されない限り、良い夢と同じです。"
        ),
        Quote(
            english = "For success, attitude is equally as important as ability.",
            japanese = "成功するには、能力と同じくらい態度が重要です。"
        ),
        Quote(
            english = "A person who never made a mistake never tried anything new.",
            japanese = "決して間違いを犯さなかった人は、何も新しいことに挑戦したことがありません。"
        ),
        Quote(
            english = "Better than a thousand hollow words is one word that brings peace.",
            japanese = "空虚な言葉 1,000 よりも優れているのは、平和をもたらす 1 つの言葉です。"
        ),
        Quote(
            english = "The possibilities are numerous once we decide to act and not react.",
            japanese = "反応せずに行動すると決めたら、可能性はたくさんあります。"
        ),
        Quote(
            english = "Almost everything comes from nothing.",
            japanese = "ほとんどすべては何もないところから生まれます。"
        ),
        Quote(
            english = "Sometimes by losing a battle you find a new way to win the war.",
            japanese = "戦いに負けることで、戦争に勝つための新しい方法が見つかることもあります。"
        ),
        Quote(
            english = "Listen to what you know instead of what you fear.",
            japanese = "あなたが恐れていることの代わりに、あなたが知っていることに耳を傾けてください。"
        ),
        Quote(
            english = "It is easier to live through someone else than to become complete yourself.",
            japanese = "自分自身を完全にするよりも、他人を通して生きるほうが簡単です。"
        ),
        Quote(
            english = "Remember that failure is an event, not a person.",
            japanese = "失敗は個人的なものではなく、出来事であることを忘れないでください。"
        ),
        Quote(
            english = "Don't settle for a relationship that won't let you be yourself.",
            japanese = "自分らしくいられなくなるような関係に甘んじないでください。"
        ),
        Quote(
            english = "What the caterpillar calls the end of the world, the master calls a butterfly.",
            japanese = "芋虫が世界の終わりと呼ぶものを、主人は蝶と呼ぶ。"
        ),
        Quote(
            english = "If you do what you've always done, you'll get what youve always gotten.",
            japanese = "いつもやってきたことをやれば、いつも得ていたものを手に入れることができます。"
        ),
        Quote(
            english = "Do not wait for leaders; do it alone, person to person.",
            japanese = "リーダーを待ってはいけません。一人で、人から人へ。"
        ),
        Quote(
            english = "Love vanquishes time. To lovers, a moment can be eternity, eternity can be the tick of a clock.",
            japanese = "愛は時間を打ち破ります。恋人たちにとって、一瞬は永遠にもなり得ますし、永遠は時計の針のようにもなります。"
        ),
        Quote(
            english = "We never live; we are always in the expectation of living.",
            japanese = "私たちは決して生きていません。私たちは常に生きることを期待しています。"
        ),
        Quote(
            english = "Think like a man of action; act like a man of thought.",
            japanese = "行動する人のように考えてください。思想家のように行動する。"
        ),
        Quote(
            english = "You can complain because roses have thorns, or you can rejoice because thorns have roses.",
            japanese = "バラにはトゲがあるから文句を言うこともできるし、バラにはトゲがあるから喜ぶこともできる。"
        ),
        Quote(
            english = "There is not one big cosmic meaning for all, there is only the meaning we each give to our life.",
            japanese = "すべての人にとって大きな宇宙的な意味があるわけではありません。あるのは、私たち一人ひとりが自分の人生に与える意味だけです。"
        ),
        Quote(
            english = "Time you enjoyed wasting was not wasted.",
            japanese = "無駄にして楽しんでいた時間は無駄ではありませんでした。"
        ),
        Quote(
            english = "Genuine sincerity opens people's hearts, while manipulation causes them to close.",
            japanese = "本物の誠実さは人の心を開きますが、操作は心を閉ざします。"
        ),
        Quote(
            english = "A man's dreams are an index to his greatness.",
            japanese = "人の夢はその人の偉大さの指標です。"
        ),
        Quote(
            english = "This is the final test of a gentleman: his respect for those who can be of no possible value to him.",
            japanese = "これは紳士の最後の試練です。彼にとって価値のない人々に対する敬意です。"
        ),
        Quote(
            english = "You teach best what you most need to learn.",
            japanese = "最も学ぶ必要があることを最もよく教えます。"
        ),
        Quote(
            english = "Continuous effort, not strength or intelligence, is the key to unlocking our potential.",
            japanese = "私たちの可能性を解き放つ鍵となるのは、強さや知性ではなく、継続的な努力です。"
        ),
        Quote(
            english = "Obstacles are those frightful things you see when you take your eyes off your goal.",
            japanese = "障害物とは、目標から目を離したときに目に入る恐ろしいものです。"
        ),
        Quote(
            english = "Go for it now. The future is promised to no one.",
            japanese = "今すぐに行ってください。未来は誰にも約束されていません。"
        ),
        Quote(
            english = "If we look at the world with a love of life, the world will reveal its beauty to us.",
            japanese = "私たちが人生への愛を持って世界を見れば、世界はその美しさを私たちに明らかにしてくれるでしょう。"
        ),
        Quote(
            english = "In skating over thin ice our safety is in our speed.",
            japanese = "薄氷の上でスケートをする場合、私たちの安全はスピードにあります。"
        ),
        Quote(
            english = "Never promise more than you can perform.",
            japanese = "自分の能力以上のことを約束しないでください。"
        ),
        Quote(
            english = "I can't believe that God put us on this earth to be ordinary.",
            japanese = "神が私たちを平凡であるためにこの地上に置いたということが信じられません。"
        ),
        Quote(
            english = "There are no limitations to the mind except those we acknowledge.",
            japanese = "私たちが認めているものを除いて、心に制限はありません。"
        ),
        Quote(
            english = "It is through science that we prove, but through intuition that we discover.",
            japanese = "私たちが証明するのは科学ですが、発見するのは直感です。"
        ),
        Quote(
            english = "If you lose today, win tomorrow. In this never-ending spirit of challenge is the heart of a victor.",
            japanese = "今日負けても明日は勝てる。この終わりのないチャレンジ精神に勝者の心がある。"
        ),
        Quote(
            english = "Blessed is the man who expects nothing, for he shall never be disappointed.",
            japanese = "何も期待しない人は幸いです、決して失望しないからです。"
        ),
        Quote(
            english = "He who knows others is wise. He who knows himself is enlightened.",
            japanese = "他人を知る人は賢い。自分自身を知っている人は啓発されています。"
        ),
        Quote(
            english = "The best way to predict your future is to create it.",
            japanese = "自分の未来を予測する最良の方法は、それを創造することです。"
        ),
        Quote(
            english = "A garden is always a series of losses set against a few triumphs, like life itself.",
            japanese = "庭園は、人生そのものと同じように、常にいくつかの勝利に対して敗北の連続です。"
        ),
        Quote(
            english = "Never mistake motion for action.",
            japanese = "動きと動作を決して間違えないでください。"
        ),
        Quote(
            english = "One who is too insistent on his own views, finds few to agree with him.",
            japanese = "自分の意見にあまりにも固執する人は、自分に同意する人がほとんどいません。"
        ),
        Quote(
            english = "Good instincts usually tell you what to do long before your head has figured it out.",
            japanese = "通常、優れた直感は、頭がそれを理解するずっと前に、何をすべきかを教えてくれます。"
        ),
        Quote(
            english = "Those who dream by day are cognizant of many things which escape those who dream only by night.",
            japanese = "昼に夢を見る人は、夜だけ夢を見る人には分からない多くのことを知っています。"
        ),
        Quote(
            english = "We cannot hold a torch to light another's path without brightening our own.",
            japanese = "私たちは、自分自身の道を照らすことなく、他人の道を照らすためにたいまつを手にすることはできません。"
        ),
        Quote(
            english = "Kind words can be short and easy to speak, but their echoes are truly endless.",
            japanese = "優しい言葉は短くて簡単に言えますが、その反響は本当に無限です。"
        ),
        Quote(
            english = "Count your joys instead of your woes. Count your friends instead of your foes.",
            japanese = "苦しみの代わりに喜びを数えてください。敵ではなく友達を数えましょう。"
        ),
        Quote(
            english = "Dreams come true. Without that possibility, nature would not incite us to have them.",
            japanese = "夢が叶います。その可能性がなければ、自然は私たちにそれらを手に入れようとはしません。"
        ),
        Quote(
            english = "Staying in one place is the best path to be taken over and surpassed by many.",
            japanese = "一つの場所に留まることが、多くの人に引き継がれ、超えられる最善の道です。"
        ),
        Quote(
            english = "Imagination will often carry us to worlds that never were. But without it we go nowhere.",
            japanese = "想像力は、私たちを決して存在しなかった世界に連れて行ってくれることがあります。しかし、それがなければ私たちはどこにも行けません。"
        ),
        Quote(
            english = "I have no special talent. I am only passionately curious.",
            japanese = "私には特別な才能はありません。私はただ情熱的に好奇心を持っているだけです。"
        ),
        Quote(
            english = "The height of your accomplishments will equal the depth of your convictions.",
            japanese = "あなたの業績の高さは、あなたの信念の深さに等しいでしょう。"
        ),
        Quote(
            english = "If I am not for myself, who will be for me? If I am not for others, what am I? And if not now, when?",
            japanese = "もし私が自分のためではないとしたら、誰が私のためになるでしょうか？もし私が他の人のためではないとしたら、私は何でしょうか？そして、今ではないとしたら、いつですか？"
        ),
        Quote(
            english = "To get the full value of joy you must have someone to divide it with.",
            japanese = "喜びの価値を最大限に得るには、それを分かち合う人が必要です。"
        ),
        Quote(
            english = "We lost because we told ourselves we lost.",
            japanese = "私たちが負けたのは、自分たちに負けたと言い聞かせたからです。"
        ),
        Quote(
            english = "Success is determined by those whom prove the impossible, possible.",
            japanese = "成功は、不可能を可能にすることを証明した人によって決まります。"
        ),
        Quote(
            english = "Good advice is always certain to be ignored, but that's no reason not to give it.",
            japanese = "良いアドバイスは必ず無視されますが、だからといってアドバイスしない理由にはなりません。"
        ),
        Quote(
            english = "The winner ain't the one with the fastest car it's the one who refuses to lose.",
            japanese = "勝者は最も速い車を持っている人ではなく、負けることを拒否した人です。"
        ),
        Quote(
            english = "No one has a finer command of language than the person who keeps his mouth shut.",
            japanese = "口を閉ざす人ほど言葉を巧みに操れる人はいない。"
        ),
        Quote(
            english = "The only person who never makes mistakes is the person who never does anything.",
            japanese = "決して間違いを犯さない唯一の人は、何もしない人です。"
        ),
        Quote(
            english = "Life is what happens to you while you're busy making other plans.",
            japanese = "人生とは、あなたが他の計画を立てるのに忙しい間に起こるものです。"
        ),
        Quote(
            english = "Discovery consists of seeing what everybody has seen and thinking what nobody else has thought.",
            japanese = "発見とは、誰もが見たことを見て、誰も考えなかったことを考えることです。"
        ),
        Quote(
            english = "If you have knowledge, let others light their candles in it.",
            japanese = "あなたに知識があるなら、他の人にその知識でろうそくを灯してもらいましょう。"
        ),
        Quote(
            english = "It is impossible for a man to learn what he thinks he already knows.",
            japanese = "人間にとって、すでに知っていると思っていることを学ぶことは不可能です。"
        ),
        Quote(
            english = "If you find yourself in a hole, the first thing to do is stop digging.",
            japanese = "穴にはまったことに気づいたら、まずやるべきことは穴を掘るのをやめることです。"
        ),
        Quote(
            english = "Everything we hear is an opinion, not a fact. Everything we see is a perspective, not the truth.",
            japanese = "私たちが聞くことはすべて意見であり、事実ではありません。私たちが見ているものはすべて視点であり、真実ではありません。"
        ),
        Quote(
            english = "The energy of the mind is the essence of life.",
            japanese = "心のエネルギーは生命の本質です。"
        ),
        Quote(
            english = "Begin, be bold, and venture to be wise.",
            japanese = "始めて、大胆になって、賢く冒険してみましょう。"
        ),
        Quote(
            english = "Give a man a fish and you feed him for a day. Teach him how to fish and you feed him for a lifetime.",
            japanese = "人に魚を一匹与えれば、一日養うことができます。彼に釣り方を教えれば、あなたは彼を一生養うことができます。"
        ),
        Quote(
            english = "A wise man will make more opportunities than he finds.",
            japanese = "賢い人は、自分が見つけるよりも多くのチャンスを作ります。"
        ),
        Quote(
            english = "Miracles come in moments. Be ready and willing.",
            japanese = "奇跡は一瞬で起こります。準備を整えて進んでください。"
        ),
        Quote(
            english = "Numberless are the worlds wonders, but none more wonderful than man.",
            japanese = "世界の驚異は数え切れないほどありますが、人間ほど素晴らしいものはありません。"
        ),
        Quote(
            english = "So is cheerfulness, or a good temper, the more it is spent, the more remains.",
            japanese = "陽気さや気立ても同様で、費やせば費やすほど、より多く残ります。"
        ),
        Quote(
            english = "The true way to render ourselves happy is to love our work and find in it our pleasure.",
            japanese = "自分自身を幸せにする本当の方法は、自分の仕事を愛し、そこに喜びを見出すことです。"
        ),
        Quote(
            english = "When you judge another, you do not define them, you define yourself.",
            japanese = "他人を判断するとき、あなたは相手を定義するのではなく、自分自身を定義するのです。"
        ),
        Quote(
            english = "Argue for your limitations, and sure enough they're yours.",
            japanese = "自分の限界について議論すれば、確かにそれはあなたのものです。"
        ),
        Quote(
            english = "He who wishes to secure the good of others, has already secured his own.",
            japanese = "他人の利益を確保したいと願う人は、すでに自分の利益を確保しています。"
        ),
        Quote(
            english = "Wise men talk because they have something to say; fools, because they have to say something.",
            japanese = "賢者は言いたいことがあるから話す。愚か者、彼らは何かを言わなければならないからです。"
        ),
        Quote(
            english = "Life is really simple, but we insist on making it complicated.",
            japanese = "人生は本当にシンプルですが、私たちはそれを複雑にしようとします。"
        ),
        Quote(
            english = "Everything that irritates us about others can lead us to a better understanding of ourselves.",
            japanese = "他人に関して私たちがイライラすることはすべて、私たち自身のより良い理解につながる可能性があります。"
        ),
        Quote(
            english = "Beware of the half truth. You may have gotten hold of the wrong half.",
            japanese = "半分真実なので注意してください。間違った半分を手に入れた可能性があります。"
        ),
        Quote(
            english = "The greatest mistake you can make in life is to be continually fearing you will make one.",
            japanese = "人生で犯し得る最大の間違いは、間違いを犯してしまうのではないかと絶えず恐れ続けることです。"
        ),
        Quote(
            english = "I have never been hurt by anything I didn't say.",
            japanese = "私は言わなかったことで傷ついたことは一度もありません。"
        ),
        Quote(
            english = "Adversity causes some men to break, others to break records.",
            japanese = "逆境によって、ブレイクする人もいるし、記録を破る人もいる。"
        ),
        Quote(
            english = "Example has more followers than reason.",
            japanese = "例には理由よりも多くのフォロワーがいます。"
        ),
        Quote(
            english = "One that desires to excel should endeavour in those things that are in themselves most excellent.",
            japanese = "優れていることを望む人は、それ自体が最も優れていることに努めるべきです。"
        ),
        Quote(
            english = "The only Zen you find on the tops of mountains is the Zen you bring up there.",
            japanese = "山の頂上で見つけられる唯一の禅は、そこで育てられる禅です。"
        ),
        Quote(
            english = "Gratitude is riches. Complaint is poverty.",
            japanese = "感謝は富です。不平不満は貧困です。"
        ),
        Quote(
            english = "You can stand tall without standing on someone. You can be a victor without having victims.",
            japanese = "人の上に乗らなくても、堂々と立つことができます。犠牲者がいなくても勝者になれる。"
        ),
        Quote(
            english = "Bad times have a scientific value. These are occasions a good learner would not miss.",
            japanese = "悪い時期には科学的な価値があります。これらは、優れた学習者にとって見逃せない機会です。"
        ),
        Quote(
            english = "It's not who you are that holds you back, it's who you think you're not.",
            japanese = "あなたを引き留めているのは、あなたが誰であるかではなく、あなたが自分を誰ではないと思っているかです。"
        ),
        Quote(
            english = "All children are artists. The problem is how to remain an artist once he grows up.",
            japanese = "すべての子供たちは芸術家です。問題は、彼が大人になった後、どうやって芸術家であり続けるかだ。"
        ),
        Quote(
            english = "Either I will find a way, or I will make one.",
            japanese = "道を見つけるか、自分で作るかのどちらかです。"
        ),
        Quote(
            english = "He who knows that enough is enough will always have enough.",
            japanese = "十分であることを知っている人は、常に十分なものを持っています。"
        ),
        Quote(
            english = "The only way to have a friend is to be one.",
            japanese = "友達を持つ唯一の方法は、友達になることです。"
        ),
        Quote(
            english = "Joy is what happens to us when we allow ourselves to recognize how good things really are.",
            japanese = "喜びは、物事が本当にどれほど素晴らしいかを自分自身に認識させたときに私たちに起こります。"
        ),
        Quote(
            english = "You really can change the world if you care enough.",
            japanese = "あなたが十分に関心を持っていれば、本当に世界を変えることができます。"
        ),
        Quote(
            english = "What you are is what you have been. What you'll be is what you do now.",
            japanese = "あなたは今までのあなたそのものです。あなたがどうなるかは、あなたが今何をするかによって決まります。"
        ),
        Quote(
            english = "There surely is in human nature an inherent propensity to extract all the good out of all the evil.",
            japanese = "人間の本性には、すべての悪の中からすべての善を抽出するという生得的な傾向が確実に存在します。"
        ),
        Quote(
            english = "Music in the soul can be heard by the universe.",
            japanese = "魂の音楽は宇宙に聞こえます。"
        ),
        Quote(
            english = "What we see depends mainly on what we look for.",
            japanese = "私たちが何を見るかは、主に私たちが何を探しているかによって決まります。"
        ),
        Quote(
            english = "To hell with circumstances; I create opportunities.",
            japanese = "状況は地獄だ。私は機会を作ります。"
        ),
        Quote(
            english = "The truest greatness lies in being kind, the truest wisdom in a happy mind.",
            japanese = "本当の偉大さは親切であることにあり、本当の知恵は幸せな心の中にあります。"
        ),
        Quote(
            english = "An ounce of emotion is equal to a ton of facts.",
            japanese = "1 オンスの感情は 1 トンの事実に相当します。"
        ),
        Quote(
            english = "Great is the art of beginning, but greater is the art of ending.",
            japanese = "始まりの芸術は偉大ですが、終わりの芸術も偉大です。"
        ),
        Quote(
            english = "Nothing will work unless you do.",
            japanese = "ããªããåããªããã°ãä½ãåããªãã"
        ),
        Quote(
            english = "Our ability to achieve happiness and success depends on the strength of our wings.",
            japanese = "私たちが幸福と成功を達成できるかどうかは、翼の強さにかかっています。"
        ),
        Quote(
            english = "Gratitude makes sense of our past, brings peace for today, and creates a vision for tomorrow.",
            japanese = "感謝は私たちの過去を理解し、今日に平和をもたらし、明日へのビジョンを生み出します。"
        ),
        Quote(
            english = "We are all inclined to judge ourselves by our ideals; others, by their acts.",
            japanese = "私たちは皆、自分の理想によって自分自身を判断する傾向があります。他の人は彼らの行為によって。"
        ),
        Quote(
            english = "Nothing is a waste of time if you use the experience wisely.",
            japanese = "この経験を賢く活用すれば、何も無駄なことはありません。"
        ),
        Quote(
            english = "If one way be better than another, that you may be sure is natures way.",
            japanese = "ある方法が別の方法よりも優れている場合、それが自然の方法であると確信するかもしれません。"
        ),
        Quote(
            english = "It is not in the stars to hold our destiny but in ourselves.",
            japanese = "私たちの運命を握るのは星ではなく、私たち自身の中にあります。"
        ),
        Quote(
            english = "I will prepare and some day my chance will come.",
            japanese = "準備をしておけば、いつかチャンスが来るでしょう。"
        ),
        Quote(
            english = "Sometimes the cards we are dealt are not always fair. However you must keep smiling and moving on.",
            japanese = "配られるカードが常に公平であるとは限りません。しかし、あなたは笑顔を絶やさず、前に進んでいかなければなりません。"
        ),
        Quote(
            english = "I saw a woman wearing a sweatshirt with Guess on it. I said, Thyroid problem?",
            japanese = "ゲスと書かれたスウェットシャツを着ている女性を見かけました。私は言いました、甲状腺の問題ですか？"
        ),
        Quote(
            english = "It's simple, if it jiggles, it's fat.",
            japanese = "シンプルです、プルプルしていたら太いです。"
        ),
        Quote(
            english = "The future is green energy, sustainability, renewable energy.",
            japanese = "未来はグリーン エネルギー、持続可能性、再生可能エネルギーです。"
        ),
        Quote(
            english = "The worst thing I can be is the same as everybody else. I hate that.",
            japanese = "私にとって最悪なことは、他のみんなと同じであることです。それは嫌だ。"
        ),
        Quote(
            english = "Failure is not an option. Everyone has to succeed.",
            japanese = "失敗は許されません。誰もが成功しなければなりません。"
        ),
        Quote(
            english = "Start wide, expand further, and never look back.",
            japanese = "広い範囲から始めて、さらに拡大し、決して後ろを振り向かないでください。"
        ),
        Quote(
            english = "My body is like breakfast, lunch, and dinner. I don't think about it, I just have it.",
            japanese = "私の体は朝食、昼食、夕食のようなものです。何も考えていない、ただ持っているだけだ。"
        ),
        Quote(
            english = "I just use my muscles as a conversation piece, like someone walking a cheetah down 42nd Street.",
            japanese = "42番街をチーターと歩いている人のように、自分の筋肉を会話のネタとして使っているだけです。"
        ),
        Quote(
            english = "In our society, the women who break down barriers are those who ignore limits.",
            japanese = "私たちの社会では、壁を打ち破る女性は限界を無視する女性です。"
        ),
        Quote(
            english = "If it's hard to remember, it'll be difficult to forget.",
            japanese = "思い出すのが難しければ、忘れるのも難しくなります。"
        )
    )

    /** Get a random quote. */
    fun random(): Quote = all.random()

    /** Get a quote by cycling through the list based on an index (e.g. hour of day). */
    fun byIndex(index: Int): Quote = all[index % all.size]
}
