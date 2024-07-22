(ns basics.core
  (:gen-class))

(defn greet-user [user]
  (println (str "hello " user)))
(defn greet
  ([user] (println "hello " user))
  ([user rank] (println "Sir " rank " how are you, " user))
  )
(defn greet-arity
  ([name] (greet-arity name "Hello sir,"))
  ([name message] (println message name))
  )
(defn number-of-args [& args] (println args))
(defn all-args [& args] (println args))

(defn multi-arity
  ([name] (multi-arity name 0))
  ([name age] (multi-arity name age "none@gmail.com"))
  ([name age email] (println (str "Name: " name " Age: " age " Email: " email)))
  )

(defn dispatch-book-format [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map
    (contains? book :book) :alternative-map
    ))

(defn my-first
  [[first-thing]]
  first-thing
  )
(defn hello-first [[thing]] thing)
(defn choosing
  [[first-choice & many-more]]
  (println (str "first is => " first-choice))
  (println (str "Many more.. " many-more))
  )

(defn destructure-vector [[name]] name)
(defn destructure-map [{hei :hei}] hei)
;;breaking keywords of out a map
(defn get-keywords
  [{:keys [height width]}]
  height width
  )

(def x-func (fn [x] (* x 2)))
;;anonymous functions
(map (fn [name] (println "Hello " name)) ["darth vader" "mr camera"])
(defn greet-heroes [heroes] (map #(str "Hello, " %) heroes))

(def no-name-1 (#(str "hello " %1) "john"))
(def no-name-2 ((fn [x] (str "hello " x)) "thomas"))
(defn destructure-vector-1 [[first second & rest]]
  (println "first: " first)
  (println "second: " second)
  (println "rest: " rest)
  )


(def heroes-list ["john" "jack" "patrik" "tom"])
(def top-h (let [top-heroes (take 2 heroes-list)] top-heroes))
(defn -main
  []
  (println top-h))


;;Vector => Ordered collection of items
;;[100 300 true] or vector 100 300 false
;;count, first, last, rest, nth, conj[back], cons[front]
;;conj names "new names", cons "new name" names


;;List => Ordered collection of items
;; '(100 450 900) or list

;;Map => Key valued pairs
;;{"name" "james" "age" 900} or (hash-map "name" "same" "email" "same@g.com")
;;get, assoc(addss), dissoc(removes), keys, vals

;;Sets
;;#{:name :lastName}
;;contains; conj, disj


;;if, = [check if things are equal] ,not= [check if 2 things are not equal]

;;number? string? keyword? map? vector?

;;cond, case

;;Arity => a number of arguments a function takes
;;multi arity functions, 3/4 normal number
;;(defn greet
;;  ([name, age](println "hello " name age))
;;  ([name] (println "hello " name))
;; )
;; The idea of this filling in the defaults technique is that you have one
;; arity, usually the one with the most arguments—that really does something. All the
;; other arities, the ones that take fewer arguments, call that main version, filling
;; in the missing parameters as they go

;;any number of argument (defn all [& args] (println args))
;;Varargs Or Variadic functions (defn all [x & args])

;;Multimethods let you have a single function with many implementations
;;picks the implementation based on any[like any] characteristic of its arguments
;;(defmulti multimethodFunc)
;;implementations for possible returned values (defmethod

;;anonymous functions
;; (fn [param-list] (function body))
;; (#(* % 2) 10)
;;function call => (* 1 2)
;;anonymous function => #(* % 2), & is the argument passed
;;many arguments %1, %2, %3 #(str %1 "has" %2 "cars")

;;Functions can return other func, The returned functions are called "closures"

;;let [let something be, binds names to values]
