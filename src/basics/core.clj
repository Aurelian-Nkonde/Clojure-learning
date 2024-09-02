(ns basics.core)
;(require 'clojure.string)
(require 'clojure.data)
(require '[clojure.string :as string])

(def asym-body-parts [
                      {:name "left-hear" :size 90}
                      {:name "left-leg" :size 54}
                      {:name "left-hand" :size 23}
                      ])
(defn matching-part [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})
(defn symmetrize-body-parts [asym-body-parts]
  (loop [remaining-body-parts asym-body-parts final-body-parts []]
    (if (empty? remaining-body-parts)
      final-body-parts
      (let [[part & remaining] remaining-body-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)]))))
      )
    ))

(defn test-membership [x]
  (when (not (:title x))
    (println "title does not exist!")))

(defn greet
  ([name] greet name "how are you?")
  ([name message] (println (str name " " message))))
(defn print-all-args [& rest] (println rest))
(defn multi-arity-function
  ([x] (multi-arity-function x "how are you?"))
  ([x y] (println (str y " " x))))

(defn dispatch-book-format
  [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map
    (contains? book :book) :alternative-map))

(defmulti normalize-book dispatch-book-format)
(defmethod normalize-book :vector-book [book]
  {:title (first book) :author (second book)})
(defmethod normalize-book :standard-map [book]
  book)
(defmethod normalize-book :alternative-map [book]
  {:title (:book book) :author (:by book)})
;(println (normalize-book {:title "the fire" :author "thousand"}))
;(println (normalize-book ["road to where?" "james"]))


(defmulti book-description :genre)
(defmethod book-description :action [book]
  (str "the fight to death by " (:author book)))
(defmethod book-description :romance [book]
  (str "my love by " (:author book)))
;(println (book-description {:genre :romance :author "thomas"}))
(defn average-function
  "this function returns the average number"
  [a b c]
  (/ (+ a b c) 3.0))
;(println (average-function 10 7 5))

;when we want to check a condition before doing anything meaningful
(defn publish-book
  [book]
  {:pre [(:title book)]}
  (println "book was ordered"))
;(publish-book {:author "thousand" :title "cows and dogs"})
(defn publish-book-second [book]
  {:pre [(:title book) (:pages book)]}
  (println "the book is ready for ordering")
  (println "book ordered!"))
(publish-book-second {:title "money" :author "john" :pages 900})

;(defn checking-the-value-returned-from-func
;  []
;  {:post [(boolean? %)]}
;  (println "check to see"))
;(checking-the-value-returned-from-func)
(defn returns-map [] {})
(defn the-returned-value-is-map
  [x]
  {:post [(map? %)]}
  x)

(defmulti user-with-languages-1 :language)
(defmethod user-with-languages-1 :shona [user]
  (str (:name user) " loves " (:language user) " zvekuti"))
(defmethod user-with-languages-1 :english [user]
  (str (:name user) " loves " (:language user) " too much!"))
;(println (user-with-languages-1 {:name "thomas" :language :shona}))
;(println (user-with-languages-1 {:name "anashe" :language :english}))

(def anonymouse-book
  {:title "gods must be crazy"})
(def book-with-authour
  {:title "the rainy season" :author "james johns"})
(defn uppercase-author [book]
  (let [author (:author book)]
    (if author
      (.toUpperCase author))))
(println (uppercase-author book-with-authour))
(defn uppercase-author-2 [book]
  (if-let [author (:author book)]
    (.toUpperCase author)))
(println (uppercase-author-2 book-with-authour))


(defn -main
  []
  (println *ns*)
  )

;list
;peek => return the head of the list, (peek list)
;pop => return the tail of the list, (pop list)
;count => count items of an list (count (list 1 2))

;creating vectors, [] or (vector 1 2 3)
;nth, get => to get vector elements, (nth [1 2] 0), (get [1 2 3] 0)
;assoc => to modify the vector, (assoc vector_name v_index new_value)
;conj => to modify the vector, add to the end
;peek and pop also works, at the end

;Map => a sequence of key-value pairs
;(hash-map :a 1 :b 2 ), ({a: 1 :b 2 })
;looking up values, (map-data :keyword)
;assoc-in => modify nested maps
;get-in => reads nested maps
;update-in => update nested maps

;Sequence
;isn't a collection type, it's an interface(ISeq)
;first => returns first element
;rest => returns the rest elements except the first
;cons(construct) => creates new sequence

;conditionals
;if, if-not, when, cond

;while (while test & body)
;doseq, (doseq [user all-users])

;unary function => a function that accepts only one argument
;map
;filter => what to keep,
;remove => what to drop
;range => returns a list of numbers
;reduce

;sequences
;sequence functions
;first(returns the value of the requested node)
;rest(remaining values after the requested node)
;cons(add new node with a given value to the beginning of the list)

;multi-methods let you have a single function with multiple implementation
;multi-arity pick the implementation based on number of arguments
;multi-methods pick the implementation based on characteristics of the arguments(any)
;Writing a multimethod is an exercise in splitting the problem apart

;:pre condition, checks on the value passed to the function
;:post condition, checks on the value returned by the function

;if-let, is an if and let combined together
;get the current namespace *ns*
;look up existing namespaces with (find-sn name)

;create a namespace with (ns new-namespace)

;(type concrete-data) to the type of the data type

;create a sequence from collection data types
;(seq []) (seq {}) (seq '())
;(first (seq [1 2])) to get the first value
;(last (seq [1 2])) to get the last value**
;(rest (seq [1 2])) to get all elements except the first
;(next (seq [1 2])) to get all elements except the first
;add new element to front with seq, (cons "john" (seq '("anna")))
;Things you can only do with sequences, create(seq []), add values (cons "value" (seq [])), (rest (seq[]))
;(first (seq [])), (next (seq []))

;(sort (seq []))
;(reverse (seq [])), (reverse ["one" "red"])
;(partition 2 (seq [])), (partition 2 ["one" "two" "three" "four"])
;(interleave [] []), (interleave [1 2] ["anna" "blue"])
;(interpose "value" []), (interpose "und" ["horse" "water" "food"])