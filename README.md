 [<img src="https://api.travis-ci.org/niesfisch/tokenreplacer.png"/>](http://travis-ci.org/niesfisch/tokenreplacer/builds)

# What's that for? 

Token Replacer is a simple and small Java Library that helps replacing tokens in strings.

You can replace tokens with <b>simple static strings</b>:
	
	String toReplace = "i can count to {number}";
    String result = new Toky().register("number", "123").substitute(toReplace);
    System.out.println(result); // i can count to 123

or strings generated <b>"on-the-fly"</b>: 
	
	String toReplace = "i can count to {number}";
    String result = new Toky().register(new Token("number").replacedBy(new Generator() {
    
        @Override
        public void inject(String[] args) {
            // store the arguments
        }
        
        @Override
        public String generate() {
            return "123"; // some very sophisticated stuff happens here :), we just return 123 to keep it simple^
        }
     })).substitute(toReplace);
     System.out.println(result); // i can count to 123

You can even <b>pass arguments</b> to the generator which makes it pretty powerful:
	
	String toReplace = "i can count to {number(1,2,3)}";
    String result = new Toky().register(new Token("number").replacedBy(new Generator() {
    
        @Override
        public void inject(String[] args) {
            // store the arguments
        }
        
        @Override
        public String generate() {
            return args[0] + args[1] + args[2]; // some very sophisticated stuff happens here :)
        }
     })).substitute(toReplace);
     System.out.println(result); // i can count to 123

If you prefer to use <b>index based tokens</b>, you can also use this:
 
<pre>
toky.register(new String[] { &quot;one&quot;, &quot;two&quot;, &quot;three&quot; });
toky.substitute(&quot;abc {0} {1} {2} def&quot;)); // will produce &quot;abc one two three def&quot;
</pre>
   
## Getting the Jar File

via Maven:

    <dependency>
        <groupId>de.marcelsauer</groupId>
        <artifactId>tokenreplacer</artifactId>
        <version>1.3.2</version>
    </dependency>

or just take the latest "tokenreplacer-x.y.jar" from the [downloads](http://github.com/niesfisch/tokenreplacer/downloads) section and put it in your classpath.
if you also need the sources and javadoc download the "tokenreplacer-x.y-sources.jar" / "tokenreplacer-x.y-javadoc.jar".

## Licence

Version >= 1.2 -> Apache 2.0 http://www.apache.org/licenses/LICENSE-2.0.txt

Version <= 1.1 -> GPL 3

## Release Notes

[Release Notes](http://github.com/niesfisch/tokenreplacer/blob/master/releasenotes.txt)
        
## Usage

<p>
simplest use case, only <b>static values</b>
</p>

<pre>
TokenReplacer toky = new Toky().register(&quot;number&quot;, &quot;123&quot;);
toky.substitute(&quot;i can count to {number}&quot;);
</pre>

<p>
is same as registering an <b>explicit {@link Token}</b>
</p>

<pre>
toky = new Toky().register(new Token(&quot;number&quot;).replacedBy(&quot;123&quot;));
toky.substitute(&quot;i can count to {number}&quot;);
</pre>

<p>
we can also use a <b>{@link Generator}</b> to <b>dynamically</b> get the
value (which here does not really make sense ;-)
</p>

<pre>
toky = new Toky().register(new Token(&quot;number&quot;).replacedBy(new Generator() {

 &#064;Override
 public void inject(String[] args) {
     // not relevant here
 }

 &#064;Override
 public String generate() {
     return &quot;123&quot;;
 }
}));
</pre>
<p>
here we use a generator and <b>pass the arguments</b> "a,b,c" to it, they
will be injected via {@link Generator#inject(String[] args)} before the call
to {@link Generator#generate()} is done. it is up to the generator to decide
what to do with them. this feature makes handling tokens pretty powerful
because you can write very dynamic generators.
</p>

<pre>
toky.substitute(&quot;i can count to {number(a,b,c)}&quot;);
</pre>

if you prefer to use <b>index based tokens</b>, you can also use this:
 
<pre>
toky.register(new String[] { &quot;one&quot;, &quot;two&quot;, &quot;three&quot; });
toky.substitute(&quot;abc {0} {1} {2} def&quot;)); // will produce &quot;abc one two three def&quot;
</pre>
 
<p>
of course you can replace all default <b>delimiters</b> with your preferred
ones, just make sure start and end are different.
</p>

<pre>
toky.withTokenStart(&quot;*&quot;); // default is '{'
toky.withTokenEnd(&quot;#&quot;); // default is '}'
toky.withArgumentDelimiter(&quot;;&quot;); // default is ','
toky.withArgumentStart(&quot;[&quot;); // default is '('
toky.withArgumentEnd(&quot;]&quot;); // default is ')'
</pre>

<p>
by default Toky will throw IllegalStateExceptions if there was no matching
value or generator found for a token. you can <b>enable/disable generating
exceptions</b>.
</p>

<pre>
toky.doNotIgnoreMissingValues(); // which is the DEFAULT
</pre>

<p>
will turn error reporting for missing values <b>OFF</b>
</p>

<pre>
toky.ignoreMissingValues();
</pre>

<p>
you can <b>enable/disable generator caching</b>. if you enable caching once a
generator for a token returned a value this value will be used for all
subsequent tokens with the same name
</p>

<pre>
toky.enableGeneratorCaching();
toky.disableGeneratorCaching();
</pre>


## More Samples

Have a look at [the unit test of Toky](http://github.com/niesfisch/tokenreplacer/blob/master/src/test/java/de/marcelsauer/tokenreplacer/TokyTest.java) to see some more samples

## peeking into the source code and building from scratch

    $ git clone http://github.com/niesfisch/tokenreplacer.git tokenreplacer
    $ cd tokenreplacer
    $ mvn clean install
    
