insert into role set id=1, name='admin';
insert into role set id=2, name='user';

insert into gender set id=1, name='male';
insert into gender set id=2, name='female';

insert into user set id=1, first_name='Shadhin', last_name='Chowdhury', email='shadhin.aust@gmail.com', password='$2a$10$2AmljACRhADqe.xhdB1NmuqTLC1DkAT3lwJs9fcvtZqVYme3/v/WS', role_id=1;
insert into user set id=2, first_name='Karim', last_name='Rahman', email='karim@gmail.com', password='$2a$10$2AmljACRhADqe.xhdB1NmuqTLC1DkAT3lwJs9fcvtZqVYme3/v/WS', role_id=2;
insert into user set id=3, first_name='A K M', last_name='Bayezid', email='bayezid@gmail.com', password='$2a$10$2AmljACRhADqe.xhdB1NmuqTLC1DkAT3lwJs9fcvtZqVYme3/v/WS', role_id=2;
insert into user set id=4, first_name='Ariful', last_name='Islam', email='ariful@gmail.com', password='$2a$10$2AmljACRhADqe.xhdB1NmuqTLC1DkAT3lwJs9fcvtZqVYme3/v/WS', role_id=2;

insert into animal set id=1, name='Dog', gender_id=1, price=1000, description='Ever wonder why your dog does the things he does? Scientists have only recently begun studying dog behavior seriously. Dog owners all know that their dogs are intelligent and can understand them pretty well. Researchers are studying how much of the anecdotal stories can be proven scientifically and whether dogs’ behavior has been modified as a result of living closely with humans. They claim that humans may misinterpret dog behavior, seeing the dog’s actions through the prism of human behavior. Understanding a dog’s behavior opens up the possibilities of modifying it or simply reacting appropriately.';
insert into animal set id=2, name='Dog', gender_id=2, price=1100, description='Ever wonder why your dog does the things he does? Scientists have only recently begun studying dog behavior seriously. Dog owners all know that their dogs are intelligent and can understand them pretty well. Researchers are studying how much of the anecdotal stories can be proven scientifically and whether dogs’ behavior has been modified as a result of living closely with humans. They claim that humans may misinterpret dog behavior, seeing the dog’s actions through the prism of human behavior. Understanding a dog’s behavior opens up the possibilities of modifying it or simply reacting appropriately.';
insert into animal set id=3, name='Cat', gender_id=1, price=430, description='Have you ever wondered why your cat behaves a certain way? In recent decades, scientific research has shed light on many aspects of cat behavior. This information can be used to improve our relationships with our cats as well as to improve their quality of life. Although some behaviors are common to all cats, each cat has an individual personality which influences his behavior. Cat behavior includes body language, sounds, eating patterns and social behavior.';
insert into animal set id=4, name='Cat', gender_id=2, price=400, description='Have you ever wondered why your cat behaves a certain way? In recent decades, scientific research has shed light on many aspects of cat behavior. This information can be used to improve our relationships with our cats as well as to improve their quality of life. Although some behaviors are common to all cats, each cat has an individual personality which influences his behavior. Cat behavior includes body language, sounds, eating patterns and social behavior.';


