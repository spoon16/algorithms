(ns integer-inversion-count.core)

;; count the number of inversions in an array containing integers 1...n
;; [1,3,5,2,4,6] -> 3 inversions
;; => ((3,2),(5,2),(5,4))

(defn ^:private gather-split-inversions
  [left right]
  (loop [inversions 0
         merged []
         [fl & rl :as l] left
         [fr & rr :as r] right]
    (cond
     (or (nil? fl) (nil? fr)) (let [rest (or (seq l) (seq r))
                                    rest-merged (into merged rest)]
                                [inversions rest-merged])
     (> fl fr) (recur (+ inversions (count l))
                      (conj merged fr)
                      l 
                      rr)
     :else (recur inversions
                  (conj merged fl)
                  rl
                  r))))

(defn ^:private gather-inversions
  [in c]
  (if (<= c 1)
    [0 in]
    (let [ls (Math/ceil (/ c 2))
          rs (- c ls)
          [l r] (split-at ls in)
          [il sl] (gather-inversions l ls)
          [ir sr] (gather-inversions r rs)
          [im m] (gather-split-inversions sl sr)]
      [(+ il ir im) m])))

(defn inversion-count
  [in]
  (first (gather-inversions in (count in))))

;; in an n element array the largest number of inversions is "n choose
;; 2" or (n(n-1))/2

;; inversion defined as (i, j) where i < j

;; left if both array indices are at most n/2 ---- i,j <= n/2
;; right if both array indices are strictly greather than n/2 ---- i,j
;; > n/2
;; split if i <= n/2 < j

