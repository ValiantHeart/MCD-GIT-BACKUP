function main()
success, data = turtle.inspectDown() 
  if data.name == "minecraft:stone" then
    monorail_place_arm("left")
    return_to_center()
    turtle.down()
    monorail_place_arm("right")
    return_to_center()
    turtle.placeDown(findItem("Railcraft:track"))
    turtle.turnRight()
    turtle.forward()
    turtle.down()
    return true
  else
    return false  
  end
end

function monorail_place_arm(direction)
  if direction == "left" then
    turtle.turnLeft()
    build_arm()
    turtle.turnLeft()
    turtle.turnLeft()
    return true
  elseif direction == "right" then
    build_arm()
    turtle.turnRight()
    turtle.turnRight()
    return true
  else
    return false
  end
end

function return_to_center()
  turtle.forward()
  turtle.forward()
end

function build_arm()
   turtle.forward()
   turtle.placeDown(findItem("minecraft:stone"))
   turtle.forward()
   turtle.placeDown(findItem("minecraft:stone_slab"))
   turtle.up()
   turtle.placeDown(findItem("minecraft:fence"))
   return true
end

function findItem(itemName)
  for i = 1,16 do 
   turtle.select(i)
    if turtle.getItemCount()>0 then
      if turtle.getItemDetail().name ==itemName then
        return i, true
      end
    end
  end
end

function checkCount(item1,item2,item3,item4)
  itemCount1=0
  itemCount2=0
  itemCount3=0
  itemCount4=0
  for i=1,16 do
    turtle.select(i)
    if turtle.getItemCount()>0 then   
      if turtle.getItemDetail().name == item1 then
        itemCount1 = itemCount1 + turtle.getItemCount()
      end
      if turtle.getItemDetail().name == item2 then
        itemCount2 = itemCount2 + turtle.getItemCount()
      end
      if turtle.getItemDetail().name == item3 then
        itemCount3 = itemCount3 + turtle.getItemCount() 
      end
      if turtle.getItemDetail().name == item4 then
        itemCount4 = itemCount4 + turtle.getItemCount()
      end
    end  
  end

  if itemCount1>4 and itemCount2>4 and itemCount3>4 and itemCount4>4 then 
    return true
  else
    return false
  end
end


while turtle.detectDown() do
  if checkCount("minecraft:stone", "minecraft:stone_slab", "minecraft:fence", "Railcraft:track") and turtle.getFuelLevel()>13 then
    main()
  else
    break
  end
end