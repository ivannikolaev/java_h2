#https://docs.docker.com/engine/reference/commandline/docker/
#https://hub.docker.com/_/hello-world
#https://hub.docker.com/_/ubuntu

#basic commands
docker run hello-world #run hello-world image
docker run --rm -it ubuntu #--rm removes the container after the execution
                           #-it runs the container in interactive mode
docker run --name mycontainer -d -i -t ubuntu /bin/sh #--name sets the name of the container
                                                      #-d sets the container to run in the background
                                                      #-t attaches a pseudo-TTY to the container
                                                      #-i prevents the sh process from exiting immediately
docker exec mycontainer echo "hello world" #executes a command in a running container
docker ps -a #list all containers
docker logs <id> #show logs of container
docker inspect <id> #show details of container
docker stop <id> #stop container
docker rm <id> #remove container
docker container prune -f #removes all stopped containers

docker image ls #list images
docker image rm <id> #remove image