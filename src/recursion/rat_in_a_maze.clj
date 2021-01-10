(ns recursion.rat-in-a-maze)

; Rat in a Maze is a problem that can be solved using Backtracking. A Maze is 
; given as N*N binary matrix of blocks where source block is the upper left 
; most block i.e., maze[0][0] and destination block is lower rightmost block 
; i.e., maze[N-1][N-1]. A rat starts from source and has to reach the 
; destination. The rat can move only in two directions: forward and down. 

; In the maze matrix, 0 means the block is a dead end and 1 means the block 
; can be used in the path from source to destination. Note that this is a simple 
; version of the typical Maze problem. For example, a more complex version can 
; be that the rat can move in 4 directions and a more complex version can be 
; with a limited number of moves.

(def maze [[1 0 0 0]
           [1 1 0 1]
           [0 1 0 0]
           [1 1 1 1]])

(defn status [maze [x y]]
  (if (and (< y (count maze)) (< x (count (nth maze 1))))
    (nth (nth maze y) x)
    nil))

(defn ->dx [maze [x y]]
  (let [new-status (status maze [(inc x) y])]
    (if (or (nil? new-status) (= 0 new-status))
      nil
      [(inc x) y])))

(defn ->dw [maze [x y]]
  (let [new-status (status maze [x (inc y)])]
    (if (or (nil? new-status) (= 0 new-status))
      nil
      [x (inc y)])))

(defn moves [maze [_ _ :as xy]]
  (filter (comp not nil?) 
          (list (->dx maze xy)
                (->dw maze xy))))

(defn solve [maze [x y :as xy] [target-x target-y :as target] path]
  (cond (and (= target-x x) (= target-y y)) (list  path)
        :else (mapcat (fn [[_ _ :as xy']] (solve maze xy' target (conj path xy'))) (moves maze xy))))

;;;

(def start [0 0])
(def end [3 3])

(solve maze start end [])