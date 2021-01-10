# Exercises on recursive algorithm in Clojure

This project collects exercises and experimentations on recursive algorithms in Clojure. It's a sort of a workbench I used to study recursion and backtracking.

In particular, I tried to find common abstraction under the following computations: *permutation*, *permutation with repetition*, *combination*, and *combination with repetition*. I defined all of these computations in terms of trees. So the *abstraction* is a function that generates a generic tree. Then I provided four functions that provide different ways of selecting items out of the list.

## Permutation and combination

## Permutation

When the order does matter it is a Permutation. A Permutation is an ordered Combination.

### Permutation without repetition

*K* = 1 |  2 |  3
*r* = 3

|   |   |   |
|---|---|---|
| 1 | 2 | 3 |
| 1 | 3 | 2 |
| 2 | 1 | 3 |
| 2 | 3 | 1 |
| 3 | 1 | 2 |
| 3 | 2 | 1 |

```txt
(123)()
-> (1)(23)
   -> (12)(3)
      -> (123)()
   -> (13)(2)
     -> (132)()
-> (2)(13)
   -> (21)(3)
      -> (213)()
   -> (23)(1)
      -> (231)()
-> (3)(12)
   -> (31)(2)
      -> (312)()
   -> (32)(1)
      -> (321)()
```

### Permutation with repetition

*K* = 1 |  2 |  3
*r* = 3

|   |   |   |
|---|---|---|
| 1 | 1 | 1 |
| 1 | 1 | 2 |
| 1 | 1 | 3 |
| 1 | 2 | 1 |
| 1 | 2 | 2 |
| 1 | 2 | 3 |
| 1 | 3 | 1 |
| 1 | 3 | 2 |
| 1 | 3 | 3 |
| 2 | 1 | 1 |
| 2 | 1 | 2 |
| 2 | 1 | 3 |
| 2 | 2 | 1 |
| 2 | 2 | 2 |
| 2 | 2 | 3 |
| 2 | 3 | 1 |
| 2 | 3 | 2 |
| 2 | 3 | 3 |
| 3 | 1 | 1 |
| 3 | 1 | 2 |
| 3 | 1 | 3 |
| 3 | 2 | 1 |
| 3 | 2 | 2 |
| 3 | 2 | 3 |
| 3 | 3 | 1 |
| 3 | 3 | 2 |
| 3 | 3 | 3 |

```txt
(123)()
-> (1)(123)
   -> (11)(123)
      -> (111)(123)
      -> (112)(123)
      -> (113)(123)
   -> (12)(123)
   -> (13)(123)
-> (2)(123)
   -> (21)(123)
   -> (22)(123)
   -> (23(123))
-> (3)(123)
   -> (31)(123)
   -> (32)(123)
   -> (22)(123)
```

## Combination

When the order doesn't matter it is a Combination.

### Combination without repetition

*K* = 1 |  2 |  3 |  4
*r* = 3

|   |   |   |
|---|---|---|
| 1 | 2 | 3 |
| 1 | 2 | 4 |
| 1 | 3 | 4 |
| 2 | 3 | 4 |

```txt
()(1234)
-> (1)(234)
   -> (12)(34)
      -> (123)(4)
      -> (124)(3)
   -> (13)(4)
      -> (134)(nil) 
-> (2)(34)
   -> (234)()
-> (3)(4)
   -> nil
-> (4)(0)
   -> nil
```

### Combination with repetition

*K* = 1 |  2 |  3 |  4  
*r* = 3

|   |   |   |
|---|---|---|
| 1 | 1 | 1 |
| 1 | 1 | 2 |
| 1 | 1 | 3 |
| 1 | 1 | 4 |
| 1 | 2 | 2 |
| 1 | 2 | 3 |
| 1 | 2 | 4 |
| 1 | 3 | 3 |
| 1 | 3 | 4 |
| 1 | 4 | 4 |
| 2 | 2 | 2 |
| 2 | 2 | 3 |
| 2 | 2 | 4 |
| 2 | 3 | 3 |
| 2 | 3 | 4 |
| 2 | 4 | 4 |
| 3 | 3 | 3 |
| 3 | 3 | 4 |
| 3 | 4 | 4 |
| 4 | 4 | 4 |

```txt
()(1234)
  -> (1)(1234)
     -> (11)(1234)
        -> (111)(1234)
        -> (112)(234)
        -> (113)(34)
        -> (114)(4)
     -> (12)(234)
        -> (122)(234)
        -> (123)(34)
        -> (124)(4)
     -> (13)(34)
        -> (133)(34)
        -> (134)(4)
     -> (14)(4)
        -> (144)(4)
  -> (2)(234) 
     -> (22)(234)
        -> (222)(234)
        -> (223)(34)
        -> (224)(4)
     -> (23)(34)
        -> (233)(34)
        -> (234)(4)
     -> (24)(4)
        -> (244)(4)
  -> (3)(34)
     -> (33)(34)
        -> (333)(34)
        -> (334)(4)
     -> (34)(4)
        -> (344)(4)
  -> (4)(4)
     -> (44)(4)
        -> (444)(4)
```

## Resources

- [Math is Fun](https://www.mathsisfun.com/combinatorics/combinations-permutations.html)
- [DCode](https://www.dcode.fr/permutations-with-repetitions)

## License

[Attribution 4.0 International (CC BY 4.0)](http://creativecommons.org/licenses/by/4.0/)
