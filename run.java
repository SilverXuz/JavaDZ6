// using System;
// using System.Collections.Generic;
// using System.Linq;
// using System.Text;

// namespace AStarInMatrix
// {
//     class AStar
//     {
//         int[,] Map;
//         int MapWidht;
//         int MapHeight;

//         int[,] WayMap;
//         /// <summary>
//         /// Конструктор
//         /// </summary>
//         public void ReadMap()
//         {
//             MapWidht = 16;
//             MapHeight = 9;
//             Map = new int[,]{
//                 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                 {1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
//                 {1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
//                 {1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
//                 {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
//                 {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
//                 {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
//                 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
//             WayMap = new int[10, 10];
//         }
//         /// <summary>
//         /// Отображение карты
//         /// </summary>
//         public void DrawMap()
//         {
//             for (int y = 0; y < MapHeight; y++)
//             {
//                 Console.WriteLine();
//                 for (int x = 0; x < MapWidht; x++)
//                     if (Map[y,x]==1)
//                         Console.Write("+");
//                     else
//                         Console.Write(" ");
//             }
//             Console.ReadKey();
//             FindWave(1, 1, 3, 4);
//         }
//         /// <summary>
//         /// Поиск пути
//         /// </summary>
//         /// <param name="startX">Координата старта X</param>
//         /// <param name="startY">Координата старта Y</param>
//         /// <param name="targetX">Координата финиша X</param>
//         /// <param name="targetY">Координата финиша Y</param>
//         public void FindWave(int startX, int startY, int targetX, int targetY)
//         {
//             bool add=true;
//             int[,] cMap = new int[MapHeight,MapWidht];
//             int x, y,step=0;
//             for (y = 0; y < MapHeight; y++)
//                 for (x = 0; x < MapWidht; x++)
//                 {
//                     if (Map[y, x] == 1)
//                         cMap[y, x] = -2;//индикатор стены
//                     else
//                         cMap[y, x] = -1;//индикатор еще не ступали сюда
//                 }
//             cMap[targetY,targetX]=0;//Начинаем с финиша
//             while (add==true)
//             {
//                 add = false;
//                 for (y = 0; y < MapWidht; y++)
//                     for (x = 0; x < MapHeight; x++)
//                     {
//                         if (cMap[x, y] == step)
//                         {
//                             //Ставим значение шага+1 в соседние ячейки (если они проходимы)
//                             if (y - 1 >= 0 && cMap[x - 1, y] != -2 && cMap[x - 1, y] == -1)
//                                 cMap[x - 1, y] = step + 1;
//                             if (x - 1 >= 0 && cMap[x, y - 1] != -2 && cMap[x, y - 1] == -1)
//                                 cMap[x, y - 1] = step + 1;
//                             if (y + 1 < MapWidht && cMap[x + 1, y] != -2 && cMap[x + 1, y] == -1)
//                                 cMap[x + 1, y] = step + 1;
//                             if (x + 1 < MapHeight && cMap[x, y + 1] != -2 && cMap[x, y + 1] == -1)
//                                 cMap[x, y + 1] = step + 1;
//                         }
//                      }
//                 step++;
//                 add = true;
//                 if (cMap[startY,startX] != -1)//решение найдено
//                     add = false;
//                 if (step > MapWidht * MapHeight)//решение не найдено
//                     add = false;
//             }
//             //Отрисовываем карты
//             for (y = 0; y < MapHeight; y++)
//             {
//                 Console.WriteLine();
//                 for (x = 0; x < MapWidht; x++)
//                     if (cMap[y, x] == -1)
//                         Console.Write(" ");
//                     else
//                     if (cMap[y, x] == -2)
//                             Console.Write("#");
//                     else
//                     if (y == startY && x == startX)
//                             Console.Write("S");
//                     else
//                     if (y == targetY && x == targetX)
//                             Console.Write("F");
//                     else
//                     if (cMap[y, x] > -1)
//                         Console.Write("{0}", cMap[y, x]);

//             }
//             Console.ReadKey();
//         }
//     }
// }