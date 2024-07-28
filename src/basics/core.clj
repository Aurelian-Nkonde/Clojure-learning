(ns basics.core
  (:gen-class))

; It employs a uniform syntax!
; Is written in a uniform structure, 2 kinds of structures
; literal representantion [numbers, strings, vectors] and Operations 
; literal + operators => Operations (code that does something!)
; Clojure evaluates every code/form to produce a value!

; Operations[How you do things] take the form (operator operand operand) 
(+ 10 100)
(str "hello" "world")

; CONTROL FLOW!
; basic flow operators if, do, when
(if true
	"this is true"
	"this is false"
)

(if false
	"this will not print out!"
	"this will print out!"
)

(if false
	"this will not print" ; clojures returns nil
)

; each branch can have one form/operation/code
; to get around this, we use DO! [do many things!]
; (if true 
; 	(do (println "hello world")
; 					(println "this is true")
; 					true)
; 	(do (println "this is false")
; 					false
; 	)
; )

; WHEN is like a combination of IF and DO but with no else branch
; (when true
; 		(println "hello")
; 		(println 100)
; 		"this is true"
; )

; nil means no value
(nil? 100)
(nil? nil)

; clojure equality operator is =
(= 100 200)
(if (= 100 100)
	"the numbers are euqal!"
	"the numbers are not equal!"
)


; Clojure has boolean operators  [or & and]
; or, returns first truthy value or last value
; and, returns first falsey value or last true value
; (println (or 100 false nil "hello")) ;100
; (println (or nil false nil 20)) ;20
; (println (or nil nil false)) ;false

; (println (and 100 "true" false "hello")) ;false
; (println (and nil false "hello")) ;nil
; (println (and 100 200 "hi")) ;hi

; NAMING VALUES WITH DEF
; use def to bind a name to a value!
(def mein-name "thousand")
(def best-cars ["bmw" "vw"])

; DATA STRUCTURES
; all data structures are immutable[can't change them in place]
; NUMBERS 100 {int} 20.50 {float} 1/3 {ratio}
; Only allow double quotes for strings
(str "ola " "mundo")
; str, concatenation

; MAPS, similar to dictionaries or hashes
; 2 kinds of maps in clojure, hash-maps[basic] and sorted maps
; get, get-in

(println {})
(println {:name "thomas" :age 90})
; can be nested
(println {:name "thousnd" :address { :street "guns" :city "oslo" }})
(def nurse-1 (hash-map :name "lucia" :age 23))
; look up values with get
(def nurse-2 {:name "chido" :height 300 :age 29 :adress {:street-name "fires" :city "london" :country "japan"}})
(def nurse-2-name (get nurse-2 :name))
; give get a default value
(def nurse-2-age (get nurse-2 :ages "no age!"))
; get-in, lets you look inside nested maps
(def nurse-2-street-name (get-in nurse-2 [:adress :street-name]))
; another way of looking up a value
(def nurse-2-height (nurse-2 :height))

; KEYWORDS, :
; primarily used as keys in maps
(println :first-keyword)
; can be used as function that look up corresponding value in data structure
(println (:color {:name "bmw" :color "red"})) ;;many do this

; VECTORS
; similar to an array, 0-indexed collection
(println [100 400 false])
; getting value at 3thd position
(println (get [100 true "hi"] 2))
(println (get ["something" "soweto" 100 false] 0))
; elements can be of any type
; create vector with function 
(def vector-names (vector "this" "that" "here"))
(def vector-names-is-vector (vector? vector-names))
; conj, adding elements to the vector
; (conj [1 2] 3)
(def adding-with-conj (conj [1 2 3] 4))
 
; LIST, similar to vectors but not entirely
; diff, can't retrieve elements with get
; '(1 2 3)
(def list-of-names '("john" "mike" "peter"))
; nth function to retrieve an element
(nth '(1 3 4) 0)
(nth '("hello" false 900) 2)
; create using function
(def create-list (list "first" true 35))
; elements can be added at the beginning
(def add-to-list (conj '("hello" 100) "new string"))


; SETS
; collections of unique values
; clojure has 2 kind of sets, hash sets & sorted sets
; creating hash set
(def new-set #{"hello" 100})
(def new-set-1 (hash-set 100 300 true))
; multiple instances of a value become one unique value
(def add-to-set (conj #{100 true} "hello"))
; create sets from existing lists and vectors by set function
(def set-from-a-list (set '(100 true 300 100)))
(def set-from-a-vector (set ["hello" "hi" "ola" "hello"]))
; check for membership using contains? function, or get , or keyword as function
(println (contains? #{100 true} true))
(println (:name #{:age :height :name}))
(println (get #{"ops" "naine" "no"} "no"))

; FUNCTIONS
(println ((or + -) 1 4 5))
(println ((and (= 1 1) + ) 1 2 3))
(println ((first [+ 0]) 1 2 3))
; functions that take other functions as arguments or returns a function are called [higher order functions]

; function calls => expressions that have a function expression as the operator
; 2 other kinds of expressions are macro calls and special forms

; special forms don't always evaluate all of their operands
; also, you cant use them as function arguments

(defn sample-function 
	"this is a docstring"
	[paramater-1]
	(println (str "this is the body!, " paramater-1))
)
; DOC-STRING
; is useful way to describe and document your code
; PARAMETER AND ARITY
; function arity => the number of function parameter
; ARITY OVERLOADING => a function with different function bodies that run according to the number of arity[parameters]
(defn multi-arity-1
	([x]
		(str "1 arity function"))
	([x y]
		(str "2 arity function"))
	([x y z]
	(str "3 arity function"))
)
;arity overloading is one way to provide default values for args 
(defn best-languages
	"this function prints out the best 2 languages"
	([one two]
	(str "Best languages are " one " and " two))
	([one]
	(best-languages one "clojure"))
)
; clojure also allows you to define a variable-arity functions by, rest parameter[put the rest in here as list]
; indicated by an ampersand(&)
(defn function-with-rest-parameter-1
	[x & rest]
	(println rest))
; you can mix rest parameter with normal parameters but the rest has to come last

; DESTRUCTERING 
; the basic idea behind it that it lets you consisly bind names to values within a collection
(defn first-destructured-function
		[[first-item]]
		(println first-item)
)
(defn first-destructured-function-2 
	[[first second third & rest]]
	(println (str "first: " first))
	(println (str "second: " second))
	(println (str "third: " third))
	(println (str "rest: " rest))
)
(defn destructured-map
	[{x :one y :two}]
	(println (str "one: " x))
	(println (str "two: " y))
)
; we often want to break keywords out of a map, here's the shorter syntax
(defn destructured-map-short-syntax
	[{:keys [height width]}]
	(println (str "height: " height))
	(println (str "width: " width))
)
; can retain access to the original map by using :as
(defn destructured-map-short-syntax-retain-origin-map
	[{:keys [weight height] :as original-map}]
	(println (str "height: " height))
	(println (str "weight: " weight))
	(println (str "original map: " original-map))
)

(defn -main 
	[]
	(destructured-map-short-syntax-retain-origin-map {:height "200m" :weight "300m"}))